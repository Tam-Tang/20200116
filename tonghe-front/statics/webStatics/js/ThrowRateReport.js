/*处理数字位*/
var format_number= function(n){
    var b=parseInt(n).toString();
    var len=b.length;
    if(len<=3){return b;}
    var r=len%3;
    return r>0?b.slice(0,r)+","+b.slice(r,len).match(/\d{3}/g).join(","):b.slice(r,len).match(/\d{3}/g).join(",");
};

$(function(){
    $(".container>.wrapper").css("display","none");

    /*请求成功后执行*/
    var refreshTime;
    var successfn=function(res){
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
             let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉数据为空，☹☹</p>`;
        $(".container>.wrapper>.row>.day-putInto-sum>p").text(format_number(res.data.dayMachineThrowingRate.setQty));
        $(".container>.wrapper>.row>.day-putOut-number>p").text(format_number(res.data.dayMachineThrowingRate.throwQty));
        $(".container>.wrapper>.row>.day-putOut-rate>p").text(res.data.dayMachineThrowingRate.percent);
        let first=1;/*第一次请求*/
        let result = []; /*重组的数组*/
         if(res.data.dayEachMachineThrowingRate.length>0){
            for(let i=0;i<res.data.dayEachMachineThrowingRate.length;i+=2){
                result.push(res.data.dayEachMachineThrowingRate.slice(i,i+2));
            }
            rejectRateEvent(result,first,refreshTime/2);
        }else{
             $(".putOutAnalyze>.chart>.showChart>.throwAnalyze").html(html);
        }
        
        // 自动刷新
        let timer=setTimeout(()=>{
            clearTimeout(timer);
            timer=null;
            window.location.reload();
        }, refreshTime);
    };
    /*请求失败*/
    var errorfn=function(e){
        alert("数据请求失败");
        let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        $(".putOutAnalyze>.chart>.showChart>.throwAnalyze").html(html);
    };
    request('ThrowRateReport',"",'GET',successfn,errorfn);
});


/*抛料率的主题内容*/
let timer=null;
var rejectRateEvent=function(resData,first,layoutTime){
    var setHtmlEvent=function(data){
        var html='';
        for(let index=0,length=data.length;index<length;index++){
            var inHtml='';
            for(let item of data[index]){
                inHtml+=`  <li class="inLi">
                            <p class="title">
                                <span class="equipmentNo">${item.machineNo||"****"}</span>
                                <span class="lineNo">所属线别：${item.lineName||"**线"}</span>
                            </p>
                            <div class="ecahrtWrap flexRowBetweenCenter">
                                <div class="left ">
                                    <p class=" flexColumnStartCenter"  data-rate="${item.throwRate||0}">
                                       ${item.throwRate||0}
                                        <span>%</span>
                                    </p>
                                    <div class="rateHeight ani"  data-height="${item.percent||0}"  swiper-animate-effect="slidInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                                        <div class="water">
                                            <i></i>
                                            <i></i>
                                        </div>
                                    </div>
                                    <div class="introduce">
                                        <ul  class="flexColumnCenterStart">
                                            <li>机台名称：<br>&nbsp;&nbsp;&nbsp;&nbsp;${item.machineNo||"****"}</li>
                                            <li>所属线别：<br>&nbsp;&nbsp;&nbsp;&nbsp;${item.lineName||"**线"}</li>
                                            <li>置件数量：<br>&nbsp;&nbsp;&nbsp;&nbsp;undefined</li>
                                            <li>抛料数量：<br>&nbsp;&nbsp;&nbsp;&nbsp;undefined</li>
                                            <li>抛料率：${item.throwRate||0}</li>
                                        </ul>
                                    </div>
                                </div>
                                <!--料号相对信息-->
                                <ul class="right flexRowStartCenter">
                                    <li>
                                        <div class="information flexColumnStartCenter">
                                            <p class="partNumber">
                                                <i class="no">1</i>&nbsp;${item.top3[0].partNumber||"*********"}
                                            </p>
                                            <p class="rate">
                                               ${item.top3[0].rate||0}
                                            </p>
                                            <span> %</span>
                                        </div>
                                        <div class="show row flexRowBetweenEnd">
                                            <div class="sumNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[0].setRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    置件数量
                                                </p>
                                            </div>
                                            <div class="putOutNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[0].throwRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    抛料数量
                                                </p>
                                            </div>
                                        </div>
                                        <div class="detail">
                                            <div class="introduce flexColumnCenterStart">
                                                <p class="set">制件数：${item.top3[0].setQty||0}</p>
                                                <p class="throw">抛料数：${item.top3[0].throwQty||0}</p>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="information flexColumnStartCenter">
                                            <p class="partNumber">
                                                <i class="no">2</i>&nbsp;${item.top3[1].partNumber||0}
                                            </p>
                                            <p class="rate">
                                               ${item.top3[1].rate||0}
                                            </p>
                                            <span> %</span>
                                        </div>
                                        <div class="show row flexRowBetweenEnd">
                                            <div class="sumNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[1].setRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    置件数量
                                                </p>
                                            </div>
                                            <div class="putOutNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[1].throwRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    抛料数量
                                                </p>
                                            </div>
                                        </div>
                                         <div class="detail">
                                            <div class="introduce flexColumnCenterStart">
                                                <p class="set">制件数：${item.top3[1].setQty||0}</p>
                                                <p class="throw">抛料数：${item.top3[1].throwQty||0}</p>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="information flexColumnStartCenter">
                                            <p class="partNumber">
                                                <i class="no">3</i>&nbsp;${item.top3[2].partNumber||0}
                                            </p>
                                            <p class="rate">
                                                ${item.top3[2].rate||0}
                                            </p>
                                            <span> %</span>
                                        </div>
                                        <div class="show row flexRowBetweenEnd">
                                            <div class="sumNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[2].setRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    置件数量
                                                </p>
                                            </div>
                                            <div class="putOutNumber col-xl-6 col-xs-6">
                                                <p class="bar">
                                                    <i style="height:${item.top3[2].throwRate||0}%"></i>
                                                </p>
                                                <p class="typeName">
                                                    抛料数量
                                                </p>
                                            </div>
                                        </div>
                                         <div class="detail">
                                            <div class="introduce flexColumnCenterStart">
                                                <p class="set">制件数：${item.top3[2].setQty||0}</p>
                                                <p class="throw">抛料数：${item.top3[2].throwQty||0}</p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </li>`;
            }
            html+=`<li class="swiper-slide">
                 <ul class="inList flexColumnStartCenter">
                   ${inHtml}
                </ul>
            </li>`;
        }
        $(".throwAnalyze>.list").html(html);

    };
    setHtmlEvent(resData);

    var setRate=function(key){
        /*慢慢增加水柱*/
        let number=0;
        let outLi=$(".throwAnalyze>.list>li");
        console.log("看似是");
        for(let item of outLi){
            number++;
            console.log("key:"+key,"number:"+number);
            let inLi=$(item).find(".inLi");
            for(let element of inLi){
                let dataRate=$(element).find(".ecahrtWrap").children(".left").find("p").attr("data-rate");
                let rateUseToHeight=$(element).find(".ecahrtWrap").children(".left").find(".rateHeight").attr("data-height");
                if(dataRate>=0.32){
                    $(element).find(".ecahrtWrap").children(".left").css({'box-shadow':'0px 0px 23px 0px rgba(231, 121, 40, 0.5)'});
                    $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").addClass("danger");
                }else if(dataRate>=0.3){
                    $(element).find(".ecahrtWrap").children(".left").css({'box-shadow':'0px 0px 23px 0px rgba(66, 226, 255, 0.5)'});
                    $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").addClass("warning");
                }
                if(rateUseToHeight.lastIndexOf("%")!=-1){
                    rateUseToHeight=rateUseToHeight.slice(0,-1);
                }else{
                    rateUseToHeight=rateUseToHeight;
                }
                let height=(rateUseToHeight-5.66)*100/100;  /*最终高度，必须使用setTimeOut 在线程执行完了空闲时setTimeOut才会启动执行*/
                $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").height("0rem");/*必须有最开始的高度，每次加载重绘，否则没有动态的效果*/
                let timer=setTimeout(function() {
                    $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").css("height", height.toFixed(2) + '%');
                    clearTimeout(timer);
                    timer=null;
                }, 100);
                $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").find("i").eq(0).css({"animation":" ripple 7000ms linear 0s infinite alternate"});
                $(element).find(".ecahrtWrap").children(".left").find(".rateHeight").find("i").eq(1).css({"animation":" ripple 3000ms linear 0s infinite alternate"});
            }

        }



        // let sumRate= $(".throwAnalyze>.list>li .ecahrtWrap>.left>p").text();
        // let test=sumRate.replace(/\s*/g,"");/*去掉所有空格*/
        // // let rateUseToHeight=(test.slice(0,4)/0.35)*100;
        // let heightRate=$(".throwAnalyze>.list>li .ecahrtWrap>.left>.rateHeight").attr("data-height");
        // console.log(heightRate);
        // let rateUseToHeight=heightRate.slice(0,-1);
        //
        // if(test.slice(0,-1)>=0.32){
        //     $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left").css({'box-shadow':'0px 0px 23px 0px rgba(231, 121, 40, 0.5)'});
        //     $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight").addClass("danger");
        // }else if(test.slice(0,-1)>=0.3){
        //     $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left").css({'box-shadow':'0px 0px 23px 0px rgba(66, 226, 255, 0.5)'});
        //     $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight").addClass("warning");
        // }
        //
        // let height=(rateUseToHeight-5.66)*100/100;  /*最终高度，必须使用setTimeOut 在线程执行完了空闲时setTimeOut才会启动执行*/
        // $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight").height("0rem");/*必须有最开始的高度，每次加载重绘，否则没有动态的效果*/
        // setTimeout(function() {
        //     $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight").css("height", height.toFixed(2) + '%');
        // }, 100);
        // $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight>.water>i:nth-child(1)").css({"animation":" ripple 7000ms linear 0s infinite alternate"});
        // $(".throwAnalyze>.list>li:eq("+key+")>.ecahrtWrap>.left>.rateHeight>.water>i:nth-child(2)").css({"animation":" ripple 3000ms linear 0s infinite alternate"});
    };

    swiperEvent(setRate,resData); /*调用轮播*/
};

var swiperEvent=function(setRate,resData){
    let indexMust=Math.floor( resData.length/2) ;//最终有多少个导航
    let remainder=resData.length%4;//最后一个框中的余数；
    let firstPage=resData.length%2;//第一页余数
    let larstPage=true;//最后一页有几个，默认两个
    let html="";
    console.log(resData[resData.length-1]);
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: resData.length==1?1:2,
        spaceBetween: 0,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
            renderBullet: function (index, className) {
                    if((index+1)==resData.length){
                        if(resData[resData.length-1].length==1){
                            larstPage=false;
                            html='<p class="' + className + '"><span class="page1"></span></p>';
                        }else{
                            // html='<p class="' + className + '"><span class="page1"></span><span class="page2"></span>'+(index+1)+'</p>';
                            html='<p class="' + className + '"><span class="page1"></span><span class="page2"></span></p>';
                        }
                    }else{
                        html='<p class="' + className + '"><span class="page1"></span><span class="page2"></span></p>';
                    }
                return html;
            },
        },
        loop: true,  //loop 不能slidesPerColumn一起使用
        speed:3000,
        loopAdditionalSlides:0,//loop模式下会在slides前后复制若干个slide,，前后复制的个数不会大于原总个数。默认为0，前后各复制1个。0,1,2 --> 2,0,1,2,0
        observer: true, //监听，当改变swiper的样式（例如隐藏/显示）或者修改swiper的子元素时，自动初始化swiper。
        observeParents:true,//修改swiper的父元素时，自动初始化swiper
        autoplay:{
            delay: 8000,    // 自动播放间隔，单位ms
            disableOnInteraction:false,// 值为false表示用户操作swiper之后，不会停止播放，值true停止
        },
        on:{
            init: function(){ //Swiper2.x的初始化是onFirstInit
                setRate();
                let indexMust=Math.floor( resData.length/2) ;//最终有多少个导航
                let  remainder=resData.length%4;//最后一个框中的余数；
                let firstPage=resData.length%2;//第一页余数
            },
            slideNextTransitionStart: function(){
                // alert('开始切换'+this.activeIndex);
                // for(let i=0,length=resData.length;i<length;i++){
                //     $(".throwAnalyze>.list>li:eq("+i+")>.ecahrtWrap>.left>.rateHeight").height("0rem");/*必须有最开始的高度，每次加载重绘，否则没有动态的效果*/
                // }
            },
            slideChangeTransitionEnd: function(){
                // setRate(this.activeIndex);
            }
        }

    });

    /*鼠标事件，进入悬停，移出开始自动播放*/
    $(".swiper-container").mouseenter(
        (e) => {//滑过悬停
            swiper.autoplay.stop();

        }).mouseleave(function () {//离开开启

        swiper.autoplay.start(); //开始自动播放
    })
};




