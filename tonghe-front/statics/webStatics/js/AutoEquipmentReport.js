var clientWidth=document.documentElement.clientWidth;
var clientHeight=document.documentElement.clientHeight;



/*环状图*/
var setItemStyle=function(color1,color2){
    var itemStyle={
        color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
                offset: 0, color: color1 // 0% 处的颜色
            }, {
                offset: 1, color: color2 // 100% 处的颜色
            }
            ],
            global: false // 缺省为 false
        }
    }
    return itemStyle
};

var  setData= [{value:0, name:'运行', itemStyle:setItemStyle('#05ba4b','#28eb73')},
{value:0, name:'待机', itemStyle:setItemStyle('#ff9c00','#f9b524')},
{value:0, name:'异常', itemStyle:setItemStyle('#f03a38','#ff6c6b')},
{value:0, name:'离线', itemStyle:setItemStyle('#a6a6a6','#a6a6a6')},
{value:0, name:'停机', itemStyle:setItemStyle('#0665da','#35a2ee')}];
var option1 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: '70%',
        y:'30%',
        itemWidth:fontSize(0.30), //图例标记的宽度
        itemHeight:fontSize(0.15), //图例标记的高度
        itemGap:fontSize(0.25),//图例项之间的间距
        data:[{name:'运行',icon:'rect'},{name:'待机',icon:'rect'},{name:'异常',icon:'rect'},{name:'离线',icon:'rect'},{name:'停机',icon:'rect'}],
        formatter:  function(name){
            var total = 0;
            var target;
            let  data=setData;
            for (var i = 0, l = data.length; i < l; i++) {
                total += data[i].value;
                if (data[i].name == name) {
                    target = data[i].value;
                    }
            }
            var arr = [
                '{a|'+((target/total)*100).toFixed(2)+'%}',
                '{b|'+name+'}',
            ];
            return name + ' ：' + (parseInt(target));
            // return name + ' ：' + ((target/total)*100).toFixed(2) + '%';
            // return arr.join('\n')
        },
        // 设置文本为红色
        textStyle:{
                color: '#ffffff',
                fontSize: fontSize(0.2),
            // rich:{
            //     a:{
            //         color: '#ffffff',
            //         fontSize:fontSize(0.2),
            //         verticalAlign:'top',
            //         align:'center',
            //         padding:[0,0,28,0]
            //     },
            //     b:{
            //         color: '#ffffff',
            //         fontSize:fontSize(0.2),
            //         align:'center',
            //         padding:[0,10,0,0],
            //         lineHeight:25,
            //         fontWeight: 'bold'
            //     }
            // }
        }
    },
    series: [
        {
            name:'运行情况',
            type:'pie',
            center: ['35%', '55%'],
            radius: ['35%', '50%'],
            avoidLabelOverlap: false,
            selectedMode:'multiple',
            label: {
                normal: {
                    show: true,
                    /*position: 'center', *//*标签文字显示中间*/
                    formatter: '{per|{d}%}',
                    rich: {
                        per: {
                            color: '#eee',
                            fontSize:fontSize(0.18),
                            padding: [fontSize(0.02), fontSize(0.04)],
                            borderRadius: fontSize(0.02)
                        }
                    }
                },
            },
            labelLine: {
                normal: {
                    length:fontSize(0.2),
                    length2:fontSize(0.2),
                    lineStyle: {
                        color: '#ffffff'
                    }
                }
            },
            data:setData

        }
    ]
};
var currentIndex = -1;
setInterval(function () {
    var dataLen = option1.series[0].data.length;
    // 取消之前高亮的图形
    myChart1.dispatchAction({
        type: 'downplay',
        seriesIndex: 0,
        dataIndex: currentIndex
    });
    currentIndex = (currentIndex + 1) % dataLen;
    // 高亮当前图形
    myChart1.dispatchAction({
        type: 'highlight',
        seriesIndex: 0,
        dataIndex: currentIndex
    });
}, 1000);
var myChart1 = echarts.init(document.getElementById('operatingState'));
myChart1.resize();/*尺寸自适应*/
myChart1.setOption(option1);



/*为了对照参数，设备运行状态*/
var nowStatus='';
var itemStyle;
var runStatus=function(status){
    switch(status){
        case 'ok':
            nowStatus= '运行';
            itemStyle=setItemStyle('#05ba4b','#28eb73');
            break;
        case 'wait':
            nowStatus='待机';
            itemStyle=setItemStyle('#ff9c00','#f9b524');
            break;
        case 'fail':
            nowStatus='异常';
            itemStyle=setItemStyle('#f03a38','#ff6c6b');
            break;
        case 'offline':
            nowStatus='离线';
            itemStyle=setItemStyle('#a6a6a6','#a6a6a6');
            break;
        case 'stop':
            nowStatus='停机';
            itemStyle=setItemStyle('#0665da','#35a2ee');
    }
};



/*稼动率得绘制canvas*/
var canvasRange=function(data){
    let gotData=data?data:0.3;
    var clientWidth=document.documentElement.clientWidth;
    var clientHeight=document.documentElement.clientHeight;
    var canvasW=clientWidth>=1920?500:300;
    var canvasH=clientHeight>=1080?500:300;
    var font=clientWidth>786?20:10;


    var canvasElement= "<div style='position:relative;width:100%;height:100%'><canvas id='canvas' width="+canvasW+" height="+canvasH+">您的浏览器版本太低</canvas></div>";
    // var canvasElement= "<canvas id='canvas'>您的浏览器版本太低</canvas>";
    $("#gaugeChart").html(canvasElement);


    var canvas = document.getElementById("canvas");
    var  r = clientHeight>=1080?130:110;
    var ctx = canvas.getContext("2d");
    var cirX = canvas.width/2;
    var cirY = canvas.height/2;
    /*第一步  绘制一个灰色的环形底子*/
    function huitiao(){
        ctx.beginPath();
        ctx.arc(cirX,cirY+20,r,Math.PI*3/4,Math.PI/4);
        ctx.lineWidth = clientWidth>786?15:5;
        ctx.strokeStyle = "#dddddd";
        ctx.stroke();
    }

    var circ = Math.PI * 3/2;
    var quart = Math.PI *5/ 4;
    var imd = null;
    huitiao();/*标准*/
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = '#1a6bff';
    ctx.lineCap = 'square';
    ctx.closePath();
    ctx.fill();
    // ctx.lineWidth = 10.0;

    imd = ctx.getImageData(0, 0, canvasW, canvasH);
    function draw(current){
        ctx.putImageData(imd, 0, 0); /*在给定矩形内清空一个矩形：*/
        ctx.save(); //save和restore可以保证样式属性只运用于该段canvas元素
        ctx.beginPath();
        ctx.strokeStyle = "#f00";
        if(current>=0.3333){      /*三分之一时 变换颜色 */
            ctx.strokeStyle = "#0f0";
        }
        if(current>=0.6666){          /*三分之二时 再变换颜色*/
            ctx.strokeStyle = "#29d3ff";
        }

        ctx.arc(cirX,cirY+20,r,-(quart),((circ) * current) - quart,false);
        // ctx.arc(cirX,cirY+20,r,-start,(Math.PI*3/2)-end,false);
        var rate=(current*100).toFixed(2);
        var txt = rate +"%";
        ctx.textBaseline = "middle";
        ctx.textAlign = "center";
        ctx.font = "bold 30px 'Microsoft YaHei'";
        ctx.fillStyle='#ddd';
        ctx.fillText(txt,cirX,cirY+20);
        ctx.font = "normal 20px 'Microsoft YaHei'";
        ctx.fillText('稼动率', cirX,cirY-20);
        ctx.stroke();
        if(current>=1){
            clearInterval(timer);
        }
    }
    var t=0;
    var timer=null;
    function loadCanvas(now){
        timer = setInterval(function(){
            if(t>now){
                clearInterval(timer);
                timer=null;
                draw(now);//最后一次绘制
            }else{
                draw(t);
                t+=0.01;
            }
        },20);
    }
    loadCanvas(gotData);
    timer=null;
};


/*维修超时*/
var top5Swiper=function(data){
    var data=data;
    var max=Number(data[0].stopTime);
    for(let item of data){
        let time=Number(item.stopTime);
        time>max?max=time:null;
    }
    var html="";
    var key=0;
    for(let item of data){
        key++;
        let rate=((item.stopTime/max)*100).toFixed(2);
        html+=` <li class="row">
            <div class="importantInfo">
                <span class="no">${key}</span>
                <span class="order">${item.euipmentName}</span>
                <span class="stopTime">${item.stopTime}H</span>
                <i class="progressBar">
                    <i class="icon" style="width:${rate}%" data-rate="${rate}"></i>
                    <span class="time"></span>
                </i>     
            </div>
             <div class="cover">
                <p class="detail">${item.introduce?item.introduce:"暂无详情信息"}</p>
             </div>
        </li>`;
    }
    $(".chart:eq(2)>.echartWrap>.list>.swiper1>.orderList").html(html);
    var list= $(".chart:eq(2)>.echartWrap>.list>.swiper1>.orderList>li .icon");
    var arr=$(".chart:eq(2)>.echartWrap>.list>.swiper1>.orderList>li");
    for(let item of arr){
        let rate=$(item).find(".icon").data("rate");
        if(rate>80){
            $(item).find(".icon").css({
                "background-image":'linear-gradient(-90deg, #eba52c, #eb4911)',
                "transition":"width 3s ease"
            })
        }else if(rate<=80&&rate>40){
            $(item).find(".icon").css({"background-image":'linear-gradient(-90deg, #12a9e1 0%, #0063d3 100%)'})
        }else{
            $(item).find(".icon").css({"background-image":'linear-gradient(-90deg, #48f6f3 0%, #02bdba 100%)'})
        }
    }
};




/*底部滚动*/
var machineSwiper=function(data){
    var machineData=[
        {
            id:1,
            machinTimelyStatus:'OK',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        },
        {
            id:2,
            machinTimelyStatus:'OK',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        },
        {
            id:3,
            machinTimelyStatus:'OK',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        },
        {
            id:4,
            machinTimelyStatus:'OK',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        },
        {
            id:5,
            machinTimelyStatus:'STOP',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        },
        {
            id:6,
            machinTimelyStatus:'OK',
            machineName:"----",
            uph:"--",
            position:"--",
            type:"--",
            model:"--",
            brand:"--"
        }
    ];
    var imageObject=function(i){
        return 'statics/webStatics/images/AutoEquipmentReport/image'+i+'.png';
    };
    var swiperData=data.length>0?data:machineData;
    var html="";
    var img="";
    var i=0;
    for(let item of swiperData){
        i++;
        if(item.machineName=="硬盘锁螺丝机"){
            img=imageObject(1);
        }
        if(item.machineName=="内存条贴标机"){
            img=imageObject(2);
        }
        if(item.machineName=="硬盘锁螺丝贴标机"){
            img=imageObject(3);
        }
        if(item.machineName=="组装自动搬运车"){
            img=imageObject(4);
        }
        if(item.machineName=="包装搬运安规测试自动化"){
            img=imageObject(5);
        }
        if(item.machineName=="包装自动封箱线1线"){
            img=imageObject(6);
        }
        if(item.machineName=="包装自动封箱线2线"){
            img=imageObject(7);
        }
        if(item.machineName=="包装装箱自动化1线"){
            img=imageObject(8);
        }
        if(item.machineName=="包装码垛自动化1线"){
            img=imageObject(9);
        }
        if(item.machineName=="包装装箱自动化2线"){
            img=imageObject(10);
        }
        if(item.machineName=="包装码垛自动化2线"){
            img=imageObject(11);
        }
        if(item.machineName=="服务器单板CPU、散热器组装自动化"){
            img=imageObject(12);
        }
        if(item.machineName=="单板锁螺丝机"){
            img=imageObject(13);
        }
        if(item.machineName=="自动插内存条"){
            img=imageObject(14);
        }



        html+=` <div class=" chart introduce  swiper-slide" >
                    <i class="icon green" data-status=${item.machinTimelyStatus}></i>
                    <div class="status ">
                        <div class="machine">
                            <div class="flexColumnCenterCenter">                  
                                <img  src=${mode==='release'?(urls()+img):"../../"+img}  alt="">
                            </div>
                        </div>
                        <div class="cover"> 
                           <!-- <ul class="upkeepInfos flexColumnCenterStart">
                                 <li>设备运行状态：${item.coverInfos.upkeepType||"--"}</li>
                                 <li>设备保养类型：${item.coverInfos.workStatus||"--"}</li>
                                 <li>距离下次保养时间：${item.coverInfos.intervalTime||"--"}</li>
                                 <li>设备最近的保养信息：${item.coverInfos.upkeepInfo||"--"}</li>
                            </ul>-->
                        </div>
                        <div class="detail">
                            <p>设备名称：${item.machineName}</p>
                            <p>设备UPH：${item.uph}</p>
                            <p>设备位置：${item.position}</p>
                            <p>设备类型：${item.type}</p>
                            <p>设备型号：${item.model}</p>
                            <p>设备品牌：${item.brand}</p>
                        </div>
                        <img  class="leftIcon" src=${mode==='release'?(urls()+'statics/webStatics/images/OrderReport/rightBottomMark.png'):'../../statics/webStatics/images/OrderReport/rightBottomMark.png'}  alt="">
                    </div>    
                </div>`;
    }
    $(".machineState>.swiper-wrapper").html(html);

    var swiperArr=$(".machineState>.swiper-wrapper>.chart");
    for(let item of swiperArr){
        let status=$(item).find(".icon").data("status");
        if(status=='FAILS'){
            $(item).find(".icon").removeClass("green").addClass("yellow");
        }else if(status=='STOP'){
            $(item).find(".icon").removeClass("green").addClass("red");
        }
    }
};

/*底部滚动*/
var swiperInit=function(){
    var swiper=new Swiper(".swiper-container",{
        slidesPerView:5,  /*显示多少个*/
        spaceBetween: clientWidth>786?50:3,/*下边距*/
        initialized:0,
        setWrapperSize:true,
        loop:true,
        speed:2000,
        centeredSlides:true,
        roundLengths:true,
        updateOnImagesReady:true,
        //导航按钮
        navigation:{
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        autoplay:{
            delay: 3000,    // 自动播放间隔，单位ms
            disableOnInteraction:false,// 值为false表示用户操作swiper之后，不会停止播放，值true停止
        }
    });
    /*鼠标事件，进入悬停，移出开始自动播放*/
    $(".machineState").mouseenter(
        (e) => {//滑过悬停
            swiper.autoplay.stop();

        }).mouseleave(function () {//离开开启

        swiper.autoplay.start(); //开始自动播放
    })
};

$(function(){
    var euipmentsStatePieData=[],autoMachineInfos,refreshTime=0;
    var successfn=function(res) {
        $(".container>.wrapper").css("display","flex");
        $(".container>.loading").css("display","none");
        autoMachineInfos = res.data.autoMachineInfos;
        refreshTime = res.data.boardconfig.refreshTime*1000; /*刷新时间*/
        /*设备运行状况处理数据开始*/
        /*将一个对象转化成数组对象*/
        if(res.data.euipmentsStatePieData){
            for(let i in res.data.euipmentsStatePieData){
                runStatus(i);
                let obj={};
                obj.value= parseInt(res.data.euipmentsStatePieData[i]);
                obj.name=nowStatus;
                obj.itemStyle=itemStyle;
                euipmentsStatePieData.push(obj);
            }
            setData=euipmentsStatePieData;
            option1.series[0].data=euipmentsStatePieData; /*update 设备运行状况*/
            myChart1.setOption(option1);/*请求成功后重新赋值option1*/
            myChart1.resize();/*尺寸自适应*/
            /*设备运行状况处理数据结束*/
        }else{
            $("#operatingState").html(`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据</p>`)
        }


        /*稼动率数据处理开始*/
        if(res.data.averageCropMobility&&res.data.averageCropMobility>0){
            canvasRange((res.data.averageCropMobility)/100); /*调用canvas画进度条**/
        }else{
            $(".dataStatistics>.chart:eq(1)>.echartWrap").html(`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据,或稼动率小于0</p>`)
        }

        /*设备状态监控*/
        var top5=res.data.euipmentsStateListData.slice(0,5);
        if(top5.length>0){
            top5Swiper(top5);
        }else{
            $(".dataStatistics>.chart:eq(1)>.echartWrap").html(`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据</p>`)
        }
        /*轮播*/
        if(res.data.autoMachineInfos.length>0){
            machineSwiper(res.data.autoMachineInfos); /* 参数：res.data.autoMachineInfos  增加参数调回返回数据，不传参数就是默认参数*/
            swiperInit();  /*调用底部是wiper函数*/
        }else{
             $(".machineState>.swiper-wrapper").html(html);
        }
       

        /*自动刷新页面*/
        setTimeout(()=>{
            clearTimeout();
            window.location.reload();
        }, refreshTime);
    };
    var errorfn=function(e){
        alert("数据请求失败");
        let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
        $(".container>.wrapper").css("display","flex");
        $(".container>.loading").css("display","none");
        $(".dataStatistics>.chart:eq(0)>.echartWrap ").html(html);
        $(".dataStatistics>.chart:eq(1)>.echartWrap ").html(html);
        $(".dataStatistics>.chart:eq(2)>.echartWrap ").html(html);
        $(".machineState>.swiper-wrapper").html(html);

    };
    /*获取数据*/
    $(".container>.wrapper").css("display","none");

    request('AutoEquipmentReport','','GET',successfn,errorfn);

});
