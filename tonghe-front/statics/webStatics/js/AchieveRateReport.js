/*定义变量clientWidth 存储界面宽度*/
var clientWidth=document.documentElement.clientWidth;



$(function(){
    // /*请求成功后执行*/
    let refreshTime=0;
    var successfn=function(res){
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
        res.data.wipnumber.unshift( {
            stationName:'工序名称',
            standardData:'目标值',
            wipQty:'WIP',
            rate:50,
        });
        $(".cycleTime").text(res.data.datarangetime.timeStart+"~"+res.data.datarangetime.timeEnd);
        var newData=[
            {
                week:'上周',
                pass:res.data.top1datas.PreWeekTop1Ach?res.data.top1datas.PreWeekTop1Ach.lineName:"--",
                flunk:res.data.top1datas.PreWeekTop1NoAch?res.data.top1datas.PreWeekTop1NoAch.lineName:"--",
            },
            {
                week:'本周',
                pass:res.data.top1datas.NowWeekTop1Ach?res.data.top1datas.NowWeekTop1Ach.lineName:"--",
                flunk:res.data.top1datas.NowWeekTop1NoAch?res.data.top1datas.NowWeekTop1NoAch.lineName:"--",
            }
        ];
        updateData(newData);/*动态消息*/
        swiperInit();/*消息滚动*/
        achievingRatess( res.data.completionrate);/*各工序达成率*/
        producting(res.data.wipnumber); /*各工序在制品*/
        setTimeout(()=>{
            clearTimeout();
            window.location.reload();
        }, refreshTime);
    };
    /*请求失败*/
    var errorfn=function(e){
        alert("数据请求失败");
        let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
        $(".container>.wrapper").css("display","block");
        $(".container>.loading").css("display","none");
        $(".productRate>.chart>.showChart").html(html);
        $(".inProduction>.chart>.inProductionCharte").html(html);

    };
    $(".container>.wrapper").css("display","none");
    request('AchieveRateReport','','GET',successfn,errorfn);

});


//不知道有多少为小数的四舍五入再保留n位小数 num为传入的值，n为保留的小数位
let fomatFloat=function (num,n){
    var f = parseFloat(num);
    if(isNaN(f)){
        return false;
    }
    f = Math.round(num*Math.pow(10, n))/Math.pow(10, n); // n 幂
    var s = f.toString();
    var rs = s.indexOf('.');
    //判定如果是整数，增加小数点再补0
    if(rs < 0){
        rs = s.length;
        s += '.';
    }
    while(s.length <= rs + n){
        s += '0';
    }
    return s;
};



/*各工序产出达成率*/
var achievingRatess=function(resData){
    let data=resData?resData:[];
    let barUl=$(".productRate>.chart>.showChart>.barGraph>ul");
    let html='';
    if(data.length>0){
        // let maxValue=Math.max();
        var amountArr=[];
        for(let value of data){
            amountArr.push(value.schedulQty);
            amountArr.push(value.actualQty);
        }
        let maxValue=Math.max.apply(null,amountArr); /*计划和实际的所有数据求最大值*/
        let precision= Math.ceil(maxValue/5);    /*精度*/
        let scaleMaximum=Math.ceil(precision*5); /*刻度最大值*/
        let li= $(".productRate>.chart >.showChart>.coord>li");
        var n=0;
        for(let liElent of li){
            let text= scaleMaximum-n*precision;
            $(liElent).find(".number").text(text);
            n++;
        }
        for(let item of data ){
            let planeNumber=((item.schedulQty/scaleMaximum)*100).toFixed(2);
            let reality=((item.actualQty/scaleMaximum)*100).toFixed(2);
            let compRate=((reality/planeNumber)*100).toFixed(2); /*计算的百分比*/
            let finishRate=((item.achRate/100)*100).toFixed(2);
            let baseRate=item.standardAchRate;/*标准百分比*/
            html+=` <li>
                                <ul class="picture">
                                    <li class="plan"  data-height=${planeNumber}  data-baseRate="${baseRate}">
                                    </li>
                                    <li class="actual"  data-height=${reality} data-baseRate="${baseRate}"></li>
                                    <li class="${finishRate<baseRate&&'red'}" style="bottom:${finishRate>=80?80:finishRate}%;color:${finishRate<baseRate?'#e82851':'#44f3f0'}">${finishRate}%</li>
                                </ul>
                                <p data-position=${item.lineName}>${item.lineName}</p>
                                <div class="reminder" >
                                    <div class="wrapper clear">
                                        <div class="arrow clear">
                                        <i></i>
                                        </div>
                                        <p class="text">过低原因:${item.reminder?item.reminder:'暂未设置'}</p>
                                    </div>
                                </div>
                                <ul class="cover">
                                    <li>${item.lineName}</li>
                                    <li class="plan"> <i class="icon"></i> <span> 计划：${item.schedulQty}</span></li>
                                    <li class="actual"> <i class="icon"></i> <span> 实际：${item.actualQty}</span></li>
                                </ul>
                            </li>`;
        }
        barUl.html(html);
    }else{
        html="<p class='empty'>抱歉，后台没有数据~</p>";
        $(".productRate>.chart>.showChart").html(html);
        $(".productRate>.chart>.showChart>p").css({position:"absolute",left:"50%",top:"50%",transform:"translate(-50%,-50%)","transform-origin": "center center","font-size":" 1.667rem"});
    }

    let liArr=$(".productRate>.chart>.showChart>.barGraph>ul>li");
    let h=10,autoHeight=5;
    for(let item of liArr){
        let random= parseInt(Math.random()*(1000-800+1)+800); /*随机数*/
        let barList=$(item).children(".picture").find("li");
        let reminder=$(item).children(".reminder");
        let lenght=barList.length;
        let finishText=barList.eq(2).text().slice(0,barList.eq(2).text().lastIndexOf("%"));
        let baseRate=barList.attr("data-baseRate");
        if(finishText<baseRate){
            reminder.css({"display":'block',"animation":`changeTop ${random}ms linear 0s infinite alternate`});
        }else{
            reminder.css("display",'none');
        }
        for(let i=0;i<lenght-1;i++){
            let height=($(barList).eq(i).attr("data-height")-6.8)*100/100;  /*最终高度，必须使用setTimeOut 在线程执行完了空闲时setTimeOut才会启动执行*/
            $(barList).eq(i).height("0rem");/*必须有最开始的高度，每次加载重绘，否则没有动态的效果*/
            setTimeout(function() {
                $(barList).eq(i).css('height',height.toFixed(2)+'%');
            }, 100);
        }
    }
};

/*各工序在制品*/
var producting=function(resData){
    /*各工序  定位标准值 未完待续*/
    let processList=resData?resData:[];
    let content='';
    if(processList.length>0){
        for(let item of processList){
            var complateRate=((item.wipQty/item.standardData)*100).toFixed(2);
            content+=`<li class=${(item.status&&item.status)==2?'yellow':((item.status&&item.status)==3?'red':'')}>
                <div class="intro clear ">
                    <p class="name">${item.stationName}</p>
                    <p class="number">${item.standardData}</p>
                    <p class="targetValue"><span>${item.wipQty}</span></p>
                </div>
                <div class="cover">
                    <div class="changingColor" data-rate=${item.rate?item.rate:complateRate}>
                        <i class="waterWaves1"></i>
                        <i class="waterWaves2"></i>
                    </div>
                </div>
            </li>`
        }
    }else{
        content="<p class='empty' style=position:absolute;left:50%;top:50%;transform:translate(-50%,-50%);transform-origin:center;font-size:1.667rem>抱歉，后台没有数据~</p>";
    }

    $(".inProductionCharte>.list").html(content);
    var liElementArr=$(".inProductionCharte>.list>li");
    let changeH=3;
    for(let li of liElementArr ){
        var w=$(li).find(".intro").width(); /*获取宽度，后面设置宽高相等得正方形*/
        $(li).find(".cover").height(w);
        let timer=setInterval(function(){
            changeH+=0.1;
            if(changeH>=$(li).find(".changingColor").attr("data-rate")){
                clearInterval(timer);
                timer=null;
            }else{
                var height=changeH-13;
                $(li).find(".cover").children(".changingColor").height(height+'%');
            }
        },10);

        var time1= parseInt(Math.random() * (6000 - 4000 + 1) + 6000);
        var time2=parseInt(Math.random() * (8000 - 7000 + 1) + 7000);

        $(li).find(".waterWaves1").css({"animation":" rageAnimate1 "+time1+"ms linear 0s infinite alternate"});
        $(li).find(".waterWaves2").css({"animation":" rageAnimate2 "+time2+"ms linear 0s infinite alternate"});

        if($(li).attr("class")=='red'){
            $(li).find(".changingColor").css({'background-image':'linear-gradient(#811c56, #ff215f)'});

        }
        if($(li).attr("class")=='yellow'){
            $(li).find(".changingColor").css({'background-image':'linear-gradient(#815f41, #ffa83c)'});
        }
    }
};

/*消息动态*/
var updateData=function(data){
    var newData=data?data:[];
    let html=`<li class="swiper-slide" >
                                   <p>上周达标TOP1：<span>${newData[0].pass}</span></p>
                                   <p>未达标TOP1: <span>${newData[0].flunk}</span></p>
                               </li>
                               <li class="swiper-slide">
                                   <p>本周达标TOP1：<span>${newData[1].pass}</span></p>
                                   <p>未达标TOP1: <span>${newData[1].flunk}</span></p>
                               </li>`;
    $(".new>.detail>ul").html(html);
};

/*消息滚动*/
var swiperInit=function(){
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 2,
        paginationClickable: true,
        speed: 5000,
        spaceBetween: 0,
        initialSlide: 0,
        loop: true,
        observer: true,//修改swiper自己或子元素时，自动初始化swiper
        autoplay:{
            delay: 3000,    // 自动播放间隔，单位ms
            disableOnInteraction:false,// 值为false表示用户操作swiper之后，不会停止播放，值true停止
        },
        loopAdditionalSlides: 2,
        autoplayDisableOnInteraction: false,
    });
    /*鼠标事件，进入悬停，移出开始自动播放*/
    $(".detail").mouseenter(
        (e) => {//滑过悬停
            swiper.autoplay.stop();

        }).mouseleave(function () {//离开开启

        swiper.autoplay.start(); //开始自动播放
    })

}

