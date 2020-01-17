

/*测试html模板字符串*/
let processStatusUl=$(".left .echartWrap>.processList");
let processTestUl=$(".testStatus>.list");
let testData=function(dataName){
    let html="";
    let itemArr=[];
    let topLi=dataName[0];
    let bottomLi=dataName[1];
    dataName.forEach(function(item,index) {
        itemArr=item;
        html+= `<li></li>`;
        processTestUl.html(html);// [{title: "T01~T08", list: Array(7)}, {title: "T08~T14", list: Array(7)}];[{},{}]
    });
    /*每个li 中的div 循环的模板字符串*/
    let valueList1=[],valueList2=[];
    let divTemplate0="",divTemplate1="";
    let divTemplateFn=function(liPosition,valueList,index,template){
        liPosition.forEach(function(value,key){
            valueList.push( value.list);
            template+=`<div class="">
                                        <p class="lineName">${value.title}</p>
                                        <p class="iconList flexRowBetweenCenter">
                                        </p>
                                    </div>`;
            $(".testStatus>.list>li:eq("+index+")").html(template);
        });
    };
    divTemplateFn(topLi,valueList1,0,divTemplate0);
    divTemplateFn(bottomLi,valueList2,1,divTemplate1);

    /*小钟的样式模板字符串*/
    let iTemplate="";
    let iFn=function(valueList,i,liKey,divKey,iTemplate){
        valueList[i].forEach(function(v,k){
            iTemplate+=`<i data-status=${v.status}></i>`;
        });
        $(".testStatus>.list>li:eq("+liKey+")>div:eq("+divKey+")>.iconList").html(iTemplate);
    };
    iFn(valueList1,0,0,0,iTemplate);/*01-08的小钟*/
    iFn(valueList1,1,0,1,iTemplate);
    iFn(valueList2,0,1,0,iTemplate);
    iFn(valueList2,1,1,1,iTemplate);
    var liElement=$(".testStatus>.list>li");
    /*标签的循环*/
    for(let itemNew of liElement){
        let divElement=$(itemNew).children("div");
        for(let valueNew of divElement){
            let iArr=$(valueNew).children(".iconList").find("i");
            for(let self of iArr){
                let linghtStatus=$(self).attr("data-status");
                if(linghtStatus=="FAIL"){
                    $(self).addClass("yellow")
                }else if(linghtStatus=="STOP"){
                    $(self).addClass("red")
                }else if(linghtStatus=="OK"){
                    $(self).removeClass("red").removeClass("yellow")
                }
            }
        }
    }
};

/*工序状态监控*/
let data=function(dataName){
    let html="";
    dataName.forEach(function(item,index){
        html+=` <li>
                                  
                                    <i class="light" data-status=${item.status}></i>
                                    <span class="processName">${item.euipmentName}</span>
                                </li>`;
        processStatusUl.html(html);
    });
    let liElement=$(".left .echartWrap>.processList>li");
    for(var value of liElement){
        let linghtStatus=$(value).find("i").attr("data-status");
        if(linghtStatus=="FAIL"){
            $(value).find("i").addClass("yellow")
        }else if(linghtStatus=="STOP"){

            $(value).find("i").addClass("red")
        }
        else if(linghtStatus=="OK"){
            $(value).find("i").removeClass("red").removeClass("yellow")
        }
    }
};

var timer=null;
/*定时器中的函数*/
let changeEvent=function(e){
    $(e).css("display","block").siblings().css("display","none");
};

// let timer=setInterval(function(){changeEvent(user)},500);


$(function(){
    /*右侧layout 图上的指示灯*/
    let lightWrapper=$(".bgLayout>.wrapper>.processStatusLight>.wrapper");
    let randomNum=Math.floor(Math.random()*13 + 1);/*生成随机数*/
    let layoutData=function(dataName,machineInfomation,utilization){
        let html='',divElement='',utilizationDiv='';
        dataName.forEach(function(item,index){
            html+=`<i data-status=${item.status}></i>`;
        });
        lightWrapper.html(html);
        let layOutI=$(".bgLayout>.wrapper>.processStatusLight>.wrapper>i");
        for(let value of layOutI){
            let status=$(value).attr("data-status");
            if(status=="FAIL"){
                $(value).addClass("yellow")
            }else if(status=="STOP"){
                $(value).addClass("red")
            }else if(status=="OK"){
                $(value).removeClass("red").removeClass("yellow")
            }
        }
        if(machineInfomation.length>0){
            /*机器的弹框信息*/
            machineInfomation.forEach(function(item,index){
                divElement+=` <div class="label${index+1} label">
                                <div class="positionRelative">
                                    <div class="introduce">
                                        <p class="name">${item.name}</p>
                                        <ul>
                                            <li> ${item.detail.size}</li>
                                            <li>${item.detail.speed}</li>
                                            <li>${item.detail.productIng}</li>
                                            <li style="display:${item.detail.pcbaNum?'block':'none'}">${item.detail.pcbaNum}</li>
                                        </ul>
                                    </div>
                                </div>  
                    </div>`;
            });
            $(".bgLayout>.wrapper>.machineInt>.wrapper").html(divElement);
            timer=setInterval(function(){
                var utilizationDivItem= $(".bgLayout>.wrapper>.machineInt>.wrapper>.label:eq("+Math.floor(Math.random()*3 )+")");
                return changeEvent(utilizationDivItem)
            },5000);
        }
        if(utilization.length>0){
            /*每条线的稼动率*/
            utilization.forEach(function(item,key){
                utilizationDiv+=`<div class="text text${key+1} clear">
                                <p class="title">稼动率</p>
                                <p class="rate">${item.rate}%</p>
                                <div class="arrow">
                                    <div class="icon"></div>
                                </div>
                            </div>`;
            });
            $(".bgLayout>.wrapper>.utilization>.wrapper").html(utilizationDiv);
            timer=setInterval(function(){
                var utilizationDivItem= $(".bgLayout>.wrapper>.utilization>.wrapper>.text:eq("+Math.floor(Math.random()*13)+")");
                return changeEvent(utilizationDivItem)
            },3000);
        }
    };



     // 异步加载数据-产线状态
    let refreshTime=0;/*刷新时间*/
    var successfn=function(res){
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
        // 工序状态监控数据
        let processStatusData=res.data.euipmentsStateListData.filter(function(item,index){
            // return item.euipmentType.search("FBT")==-1;
            return item.euipmentName&&item.euipmentName.slice(0,3)!='FBT'
        });
        // FBT测试状态数据
        let  processTestData=res.data.euipmentsStateListData.filter(function(item,index){
            // return item.euipmentType.search("FBT")!=-1;
            return item.euipmentName&&item.euipmentName.slice(0,3)=='FBT'
        });
        let from1To7=processTestData.slice(0,7);
        let from8To14=processTestData.slice(7,14);
        let from15To21=processTestData.slice(14,21);
        let from22To28=processTestData.slice(21,28);
        /*重新组装的FBT测试状态数组*/
        let processTestNewData=[
            [
                {title:"T01~T08",list:from1To7},
                {title:"T08~T14",list:from8To14}
            ],
            [
                {title:"T15~T21",list:from15To21},
                {title:"T22~T28",list:from22To28}
            ]
        ];
        //有修改:
        //let utilization=res.data.utilization.length>0?res.data.utilization:[]; //原本
        let utilization=res.data.utilization.length>0?res.data.utilization:[
            {
                "lineName": "1",
                "rate": 20
            },
            {
                "lineName": "2",
                "rate": 20.5
            },
            {
                "lineName": "3",
                "rate": 35.5
            },
            {
                "lineName": "4",
                "rate": 28.5
            },
            {
                "lineName": "5",
                "rate": 25.5
            },
            {
                "lineName": "6",
                "rate": 30.0
            },
            {
                "lineName": "7",
                "rate": 25
            },
            {
                "lineName": "8",
                "rate": 50
            },
            {
                "lineName": "9",
                "rate": 25
            },
            {
                "lineName": "10",
                "rate": 55
            },
            {
                "lineName": "11",
                "rate": 82
            },
            {
                "lineName": "12",
                "rate": 99
            },
            {
                "lineName": "13",
                "rate": 80
            }
        ];
        //这块修改了，增加了固定值
        // let machineInfomation=res.data.machineInfomation.length>0?res.data.machineInfomation:[];//原本是这样
        let machineInfomation=res.data.machineInfomation?res.data.machineInfomation:[
            {
                "name": "回流焊",
                "detail": {
                "size": "设备型号：XPM2  1030",
                "speed": "设备连速：90cm/min",
                "productIng": "生产机种：GROUP  E",
                "pcbaNum": "设备内板数：6pcs"
                }
            },
            {
                "name": "回流焊",
                "detail": {
                "size": "设备型号：XPM2  1030",
                "speed": "设备连速：90cm/min",
                "productIng": "生产机种：GROUP  E",
                "pcbaNum": "设备内板数：6pcs"
                }
            },
            {
                "name": "PTR",
                "detail": {
                "size": "设备型号：CT-1205E",
                "speed": "压接磨具：CZYZ0062",
                "productIng": "生产机种：HO302A3FQ"
                }
            }
        ];
        data(processStatusData);
        if(processStatusData.length&&machineInfomation.length>0&&utilization.length>0){
            layoutData(processStatusData,machineInfomation,utilization);
        }
        testData(processTestNewData);

        // 自动刷新
        let timer=setTimeout(()=>{
            clearTimeout(timer);
            timer=null;
            window.location.reload();
        }, refreshTime);
    };
    var errorfn=function(e){
        alert("数据请求失败");
        let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        $(".left .chart>.echartWrap").html(html);
    };
    /*获取数据*/
    $(".container>.wrapper").css("display","none");
    request('ProcessStateReport','','GET',successfn,errorfn);
});

document.addEventListener("visibilitychange", function () {
    if (document.hidden) {
        clearInterval(timer);
    }
});



