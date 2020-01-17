/*定义变量clientWidth 存储界面宽度*/
var clientWidth=document.documentElement.clientWidth;
var timeType='iw';//dd  iw  mm  日   周  月
var maintainStep="BACK";//0 单板  1整机   front单板   BACK整机


/*维修周期管理*/
var spaceBetween=clientWidth>786?9:3;
let canvasWH=clientWidth>786?175:58;
let font=clientWidth>786?40:10;
let canvasElement= "<canvas id='c3' width="+canvasWH+"height="+canvasWH+">您的浏览器版本太低</canvas>";
$("#fixedRateChart").html(canvasElement);
let fixedCycle = function (percent,badnum,up5num,down5num) {
    let canvas = document.getElementById('c3'),
        context = canvas.getContext('2d'),
        cirX = canvas.width/2,
        cirY = canvas.height/2,
        rad = Math.PI*2/100,
        rate=percent?percent*100:0,

        n = 0,
        r = clientWidth>786?65:20;
    function DreamLoading() {
        context.clearRect(0,0,canvas.width,canvas.height);
        writeCircle();
        writeText(n);
        writeOrange(n);
        if(n < rate) {
            n = n + 1;
        } else {
            n = rate;
        }
        window.requestAnimationFrame(DreamLoading);
    }

    function writeText(n) {
        let positionX=clientWidth>786?(cirX):(cirX-8);
        let positionY=clientWidth>786?(cirY+10):(cirY);
        context.save();
        context.textAlign = "center";
        context.strokeStyle = '#fff';
        context.font = font+"px Haettenschweiler";
        context.color='#fff';
        context.strokeText(n.toFixed(2) + '%', positionX,positionY);
        context.stroke();
        context.restore();
    }

    function writeOrange(n) {
        context.save();
        context.strokeStyle = '#0078f6';
        // context.fillStyle="#fff";
        context.lineWidth = clientWidth>786?15:5;
        context.beginPath();
        context.arc(cirX,cirY,r,-Math.PI/2,-Math.PI/2+rad*n,false);
        context.stroke();
        context.restore();
    }

    function writeCircle() {
        // 画个圆
        // stroke 边框
        // 保存当前的绘制缓冲区
        context.save();
        // 开始路径
        context.beginPath();
        context.strokeStyle = "#5a9be2";
        // context.fillStyle="#fff";
        context.lineWidth = clientWidth>786?15:5;
        context.arc(cirX,cirY,r,0,Math.PI*2,false);
        context.stroke();
        context.restore();
    }
    DreamLoading();


    $(".pieChart>.row>.right>p:eq(0)>span").text(badnum);
    $(".pieChart>.row>.right>p:eq(1)>span").text(up5num);
    $(".pieChart>.row>.right>p:eq(2)>span").text(down5num);
};







var fixedNum = 300;
var setItemStyle = function (color1, color2) {
    var itemStyle = {
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

let lineChartEvent=function(resData){
    let color=[["#ff9c00","#f9b524"],["#48f6f3","#02bdba"],["#0665da","#35a2ee"],["#2e3aec","#4954ed"],["#9a17eb","#b248f3"],["#9a17ea","#b248f4"]];
    let legendData=[];
    let seriesData=[];
    let obj={};
    let i=0;

    if(resData.series.length>0){
        for(let item of resData.series){
            legendData.push(item.name);
            item.type="line";
            item.stack="总量";
            item.symbol="none";
            item.areaStyle={};
            let color1=color[i][0],color2=color[i][1];
            item.itemStyle= setItemStyle(color1, color2);

            if(i+1==resData.series.length){
                item.label={
                    normal: {
                        show: true,
                        position: 'inside',
                        color: '#ffffff',
                        fontFamily: 'Haettenschweiler',
                        fontSize: fontSize(0.2)
                    }
                };
                item.symbol= mode==='release'?'image://'+urls()+'statics/webStatics/images/MaintainReport/mark.png':'image://../../statics/webStatics/images/MaintainReport/mark.png';
                item.symbolSize=[fontSize(0.45), fontSize(0.5)];
                item.itemStyle=[0, '-40%'];/*标记相对原本位置的偏移*/
            }
            seriesData.push(item);
            i++;
        }

    }

    /*不良分布趋势图，面积图*/
    var myChart2 = echarts.init(document.getElementById('areaChart'));
    var option = option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            },
            confine:true,
            position: function (pos, params, el, elRect, size) {
                var obj = {top:fontSize(0.1),
                    left:(pos[0] < size.viewSize[0] / 2)?fontSize(0.5):fontSize(0),
                };
                // obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                return obj;
            }
        },
        legend: {
            icon: 'circle',
            y:'3%',
            itemGap:fontSize(0.22),
            itemWidth:fontSize(0.2),
            itemHeight:fontSize(0.2),
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.16),
            },
            data:legendData
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            height:'80%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                // axisTick:{
                //     interval:0, /*  showMaxLabel:true,/*是否显示最大的tick的label*/
                //     rotate:40 /*标签显示不下可以倾斜40° -90~90的区间值*/
                // },
                axisLabel: {
                    color: '#fff',
                    fontSize: fontSize(0.2),
                    interval:0, /*  showMaxLabel:true,/*是否显示最大的tick的label*/
                    // rotate:40, /*标签显示不下可以倾斜40° -90~90的区间值*/
                    formatter:function(value)  /*做到label字数大于4换行*/
                    {
                        var ret = "";//拼接加\n返回的类目项
                        var maxLength = 4;//每项显示文字个数
                        var valLength = value.length;//X轴类目项的文字个数
                        var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                        if (rowN > 1)//如果类目项的文字大于3,
                        {
                            for (var i = 0; i < rowN; i++) {
                                var temp = "";//每次截取的字符串
                                var start = i * maxLength;//开始截取的位置
                                var end = start + maxLength;//结束截取的位置
                                //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                temp = value.substring(start, end) + "\n";
                                ret += temp; //凭借最终的字符串
                            }
                            return ret;
                        }
                        else {
                            return value;
                        }
                    }

                },

                data:resData.xAxisData
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    color: '#fff',
                    fontSize: fontSize(0.2),
                    margin:fontSize(0.25)/*坐标轴与表之间的间隙，默认8px*/
                },
                splitLine: { /*纵坐标的分割线*/
                    show: true,
                    lineStyle: {
                        color: '#2c2f58'
                    }
                }
            }
        ],
        series:seriesData
        // series: [
        //     {
        //         name: '04:00',
        //         type: 'line',
        //         stack: '总量',
        //         areaStyle: {},
        //         symbol:'none',
        //         itemStyle: setItemStyle('#ff9c00', '#f9b524'),
        //         data: [120, 132, 101, 134, 90, 230, 210, 122, 90]
        //     },
        //     {
        //         name: '08:00',
        //         type: 'line',
        //         stack: '总量',
        //         symbol:'none',
        //         areaStyle: {},
        //         itemStyle: setItemStyle('#48f6f3', '#02bdba'),
        //         data: [220, 182, 191, 234, 290, 330, 310, 201, 300]
        //     },
        //     {
        //         name: '12:00',
        //         type: 'line',
        //         stack: '总量',
        //         symbol:'none',
        //         areaStyle: {},
        //         itemStyle: setItemStyle('#0665da', '#35a2ee'),
        //         data: [150, 232, 201, 154, 190, 330, 410, 300, 210]
        //     },
        //     {
        //         name: '16:00',
        //         type: 'line',
        //         stack: '总量',
        //         symbol:'none',
        //         areaStyle: {normal: {}},
        //         itemStyle: setItemStyle('#2e3aec', '#4954ed'),
        //         data: [320, 332, 301, 334, 390, 330, 320, 410, 120]
        //     },
        //     {
        //         name: '20:00',
        //         type: 'line',
        //         stack: '总量',
        //         label: {
        //             normal: {
        //                 show: true,
        //                 position: 'inside',
        //                 color: '#ffffff',
        //                 fontFamily: 'Haettenschweiler',
        //                 fontSize: fontSize(0.2)
        //             }
        //         },
        //         areaStyle: {normal: {}},
        //         symbol: mode==='release'?'image://'+urls()+'statics/webStatics/images/MaintainReport/mark.png':'image://../../statics/webStatics/images/MaintainReport/mark.png',
        //         symbolSize: [fontSize(0.45), fontSize(0.5)],
        //         symbolOffset: [0, '-40%'],/*标记相对原本位置的偏移*/
        //         itemStyle: setItemStyle('#9a17eb', '#b248f3'),
        //         data: [820, 932, 901, 934, 1290, 1330, 1320, 290, 300]
        //     }
        // ]
    };
    myChart2.setOption(option);
};


    /*按钮选择*/
    var  selectEvent=function(timer1){
        /*点击整机/单板*/
        $(".container>.wrapper>.row>.left>.row>.toggleBtn").on('click', 'p', function (e) {
            clearTimeout(timer1); /*点击的时候关闭定时器*/
            timer1=null;
            if ($(this).find(".icon").hasClass('active')) {
                return true;
            } else {
                $(this).find(".icon").addClass("active").parent("p").siblings("p").find(".icon").removeClass("active");
                if( $(this).find(".icon").next().text()=='单板'){
                    maintainStep="front";//0 单板  1整机   front单板
                    $(".reflowSoldering>.chart>.theme>.title").text("单板WIP");
                    $("#secondaryCircumfluence").css({'visibility':'hidden'}) ;
                    $("#wipline").css({'visibility':'visible'});
                }else{
                    maintainStep="BACK";//0 单板  1整机  BACK整机
                    $(".reflowSoldering>.chart>.theme>.title").text("二次回流监控");
                    $("#secondaryCircumfluence").css({'visibility':'visible'}) ;
                    $("#wipline").css({'visibility':'hidden'});
                }
            }
            $(".container>.wrapper").css("display","none");
            $(".container>.loading").css("display","block");
            request('MaintainReport','timeType='+timeType+'&maintainStep='+maintainStep,'GET',successfn,errorfn);
        });

        /*点击今日/本周/本月*/
        $(".right .top .pieChart>.pieChartRow>.toggleBtn").on('click', 'li', function (e) {
            clearTimeout(timer1); /*点击的时候关闭定时器*/
            timer1=null;
            if ($(this).find(".icon").hasClass('active')) {
                return true;
            } else {
                $(this).find(".icon").addClass("active").parent("li").siblings("li").find(".icon").removeClass("active");
                var selected=$(this).find(".icon").addClass("active").next().text();
                switch(selected){
                    case '今日' :
                        timeType='dd';//dd  iw  mm  日   周  月
                        break;
                    case '本周':
                        timeType='iw';//dd  iw  mm  日   周  月
                        break;
                    case '本月':
                        timeType='mm';//dd  iw  mm  日   周  月
                        break;
                }
            }
            $(".container>.wrapper").css("display","none");
            $(".container>.loading").css("display","block");
            request('MaintainReport','timeType='+timeType+'&maintainStep='+maintainStep,'GET',successfn,errorfn);
        });

    };

    /*轮播*/
   var swiperEvent= function(length){
       if(mySwiper){
           mySwiper.destroy(true,true);
       }else{
           var mySwiper = new Swiper('.swiper-container', {

               direction : 'vertical', // 垂直切换选项
               spaceBetween: spaceBetween,/*下边距*/
               loop: true, // 循环模式选项
               slidesPerView:length>6?6:length,/*显示6页*/
               observer:true, //修改swiper自己或子元素时，自动初始化swiper
               observeParents:true, //修改swiper的父元素时，自动初始化swiper
               autoplay: {
                   delay: 1000, //时间 毫秒
                   disableOnInteraction: true  //用户操作之后是否停止自动轮播默认true
               },
           });
       }

       //鼠标覆盖停止自动切换
       mySwiper.el.onmouseover = function(){
           mySwiper.autoplay.stop();
       };
       //鼠标离开开始自动切换
       mySwiper.el.onmouseout = function(){
           mySwiper.autoplay.start();
       }
    };
   let fixedOverTimeEvent=function(resData,timeType,maintainStep){
        /*维修超时*/
        var datalist;
        datalist=resData;
        var max=Number(datalist[0].overTime);
        for(var item of datalist){
            var time=Number(item.overTime);
            time>max?max=time:max;
        }
        var html="";
        var cont=0;
        for(let item of datalist){
            cont++;
            let rate=((item.overTime/max)*100).toFixed(2);
            html+=` <li class="swiper-slide">
                <div class="importantInfo">
                    <span class="no">${cont}</span>
                    <span class="order">${item.unitSn}</span>
                    <i class="progressBar">
                        <i class="icon" style="width:${rate}%" data-rate="${rate}"></i>
                        <span class="time">${item.overTime}H</span>
                    </i>
                </div>
                <div class="cover flexRowCenterCenter">
                    <p class="detail">${item.introduce?item.introduce:"暂无详情信息"}</p>
                </div>
            </li>`;
        }
        // $(".fixedOverTime>.list>.swiper-container").attr("class","swiper-container"+timeType);
        let container= $(".fixedOverTime>.list>.swiper-container");
        if(maintainStep=="front"){
            container.addClass("front");
            if(container.hasClass("BACK")){
                container.removeClass("BACK");
            }
        }else if(maintainStep=="BACK"){
            container.addClass("BACK");
            if(container.hasClass("font")){
                container.removeClass("font");
            }
        }

        if(timeType=="dd"){
            container.addClass("dd");
           if(container.hasClass("iw")||container.hasClass("mm")){
               container.removeClass("iw");
               container.removeClass("mm")
           }
        }else if(timeType=="iw"){
            container.addClass("iw");
            if(container.hasClass("dd")||container.hasClass("mm")){
                container.removeClass("dd");
                container.removeClass("mm")
            }
        }else if(timeType=="mm"){
            container.addClass("mm");
            if(container.hasClass("dd")||container.hasClass("iw")){
                container.removeClass("dd");
                container.removeClass("iw")
            }
        }
        $(".fixedOverTime>.list>.swiper-container>.orderList").remove(); /*关键的一步，先将页面轮播部分清空*/
        $(".fixedOverTime>.list>.swiper-container").html('<ul class="swiper-wrapper orderList">'+html+'</ul>');  /*再找到位置，赋值进去*/
        var arr=$(".fixedOverTime>.list>.swiper-container>.orderList>li");
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
        swiperEvent(resData.length); /*调用swiper*/
   };



let myChart3Event=function(resData){
    let goalData=[];
    for(let i=0,length=resData.trueData.length;i<length;i++){
        goalData[i]=80+Math.ceil(Math.random()*10);
    }
    /*渐变颜色设置*/
    var areaColor=function(color1,color2){
        var itemStyle= {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: color1
                }, {
                    offset: 1,
                    color:color2
                }])
            }
        };
        return itemStyle
    };
    var myChart3 = echarts.init(document.getElementById('secondaryCircumfluence'));
    /*二次回流焊--折线区域图*/
    var option3 = {
        tooltip: {
            position: [10, 0],
            trigger: 'axis'
        },
        legend: {
            show:false,
            x:'75%',
            top:clientWidth>786?'2%':0,
            bottom:fontSize(0.3),
            orient: 'vertical',
            icon: 'circle',
            itemWidth:fontSize(0.18), //图例标记的宽度
            itemHeight:fontSize(0.18), //图例标记的高度
            itemGap:fontSize(0.32),//图例项之间的间距
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.2),
            },
            data:['实际','目标']
        },
        grid: {
            left: '5%',
            right: '7%',
            top: '20%',
            bottom:fontSize(0.2),
            borderWidth:0,/*网格的边框线宽*/
            containLabel: true /*grid 区域是否包含坐标轴的刻度标签*/
        },
        xAxis: {
            type: 'category',
            data: resData.xaxis,
            boundaryGap: false,
            axisLabel:{
                color:'#fff',
                fontSize:fontSize(0.15),
                showMaxLabel:true,/*是否显示最大的tick的label*/
            },
        },
        yAxis: {
            type: 'value',
            min:function(value){return value.min-5},
            axisLine:{
                show:false, /*是否显示坐标轴轴线*/
            },
            axisLabel: {
                color:'#fff',
                fontSize:fontSize(0.15),
                margin:fontSize(0.25)/*坐标轴与表之间的间隙，默认8px*/
            },
            splitLine: { /*纵坐标的分割线*/
                show: true,
                lineStyle:{
                    color:'#2c2f58'
                }
            }
        },
        series: [
            {
                name:'实际',
                type: 'line',
                z:5,
                data: resData.trueData,
                itemStyle: { /*边线颜色*/
                    normal: {
                        color: 'rgba(38,207,255,0.5)'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        width:fontSize(0.35),  /*label 的宽高*/
                        height:fontSize(0.35),
                        lineHeight:fontSize(0.35),
                        borderWidth:2, /*label边框，默认为0*/
                        borderColor: '#26cfff',
                        borderRadius:fontSize(0.175),
                        backgroundColor: '#19202c',
                        formatter: '{per|{c}%}',
                        offset:[0,20],
                        rich: {
                            per: {
                                color: '#ffffff',
                                fontSize: fontSize(0.18),
                                fontFamily: 'Haettenschweiler',
                                padding: [2, 4],
                                align:'center',
                            }
                        }
                    }
                },
                areaStyle: areaColor("rgba(38,207,255,0.5)","rgba(38,207,255,0)"),
            },
            {
                name:'目标',
                // data: resData.goalData,
                data: resData.goalData.length>0?resData.goalData:goalData,
                type: 'line',
                z:1,/*这个在上面*/
                symbol:'none',/*没有标记样式*/
                itemStyle: { /*边线颜色*/
                    normal: {
                        color: '#f5f5f5',

                    }
                },
                areaStyle:areaColor("rgba(255,255,255,0.5)","rgba(255,255,255,0)"),

            }

        ]
    };
    if (myChart3 != null && myChart3 != "" && myChart3 != undefined) {
            myChart3.dispose();
        }
    myChart3 = echarts.init(document.getElementById('secondaryCircumfluence'));
    myChart3.setOption(option3);

};

/*单板WIP*/
/*折线区域图*/
let myChart4Event=function(resData){
    let goalData=[];
    for(let i=0,length=resData.trueData.length;i<length;i++){
        goalData[i]=80+Math.ceil(Math.random()*10);
    }
    var delivered=resData.goalData.length>0?resData.goalData:goalData;/*类型一，后面的*/
    var trueData=resData.trueData;/*类型二*/
    Array.prototype.getMax=function(){
        return Math.max(...trueData);
    };
    var payMaxGotNotPay;/*二类的最大对应的一类*/
    console.log(goalData);
    for(var i=0;i<trueData.length;i++){
        if(trueData.getMax()==trueData[i]){
            payMaxGotNotPay= delivered[i];
        }
    }
    var option4 = {
        tooltip: {
            position: [10, 10],
            trigger: 'axis',
        },
        legend: {
            x:'70%',
            top:clientWidth>786?'6%':0,
            bottom:fontSize(0.5),
            icon: 'circle',
            itemWidth:fontSize(0.13), //图例标记的宽度
            itemHeight:fontSize(0.13), //图例标记的高度
            itemGap:fontSize(0.14),//图例项之间的间距
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.14),
            },
            data:['类型一','类型二']
        },
        grid: {
            left: '5%',
            right: '10%',
            height:'70%',
            bottom:fontSize(0.2),
            borderWidth:0,/*网格的边框线宽*/
            containLabel: true /*grid 区域是否包含坐标轴的刻度标签*/
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLabel:{
                color:'#fff',
                fontSize:fontSize(0.15),
                showMaxLabel:true,/*是否显示最大的tick的label*/
                // 使用函数模板，函数参数分别为刻度数值（类目），刻度的索引
                // formatter: function (value, index) {
                //     // 格式化成月/日，只在第一个刻度显示年份
                //     var date = new Date(value);
                //     var texts = [(date.getMonth() + 1), date.getDate()];
                //     // if (index === 0) {
                //     //     texts.unshift(date.getYear());
                //     // }
                //     return texts.join('/');
                // }
            },
            data:resData.xaxis
        },
        yAxis: {
            type: 'value',
            min:function(value){return value.min-5},
            axisLabel: {
                color:'#fff',
                fontSize:fontSize(0.15),
                margin:fontSize(0.25)/*坐标轴与表之间的间隙，默认8px*/
            },
            splitLine: { /*纵坐标的分割线*/
                show: true,
                lineStyle:{
                    color:'#2c2f58'
                }
            }
        },
        series: [
            {
                name:'类型一',
                max:delivered.getMax,
                data:delivered,
                type: 'line',
                z:1,/*这个在下面*/
                symbol:'none',/*没有标记样式*/
                itemStyle: { /*边线颜色*/
                    normal: {
                        color: 'rgba(38,132,233,0.6)',

                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(45,216,255,0.6)'
                        }, {
                            offset: 1,
                            color: 'rgba(45,216,255,0)'
                        }])
                    }
                }

            },
            {
                name:'类型二',
                max:trueData.getMax,
                data: resData.trueData,
                type: 'line',
                z:5, /*前面*/
                symbol:'none',/*没有标记样式*/
                itemStyle: { /*边线颜色*/
                    normal: {
                        color: 'rgba(76,20,181,0.8)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(76,20,181,0.8)'
                        }, {
                            offset: 1,
                            color: 'rgba(45,217,255,0)'
                        }])
                    }
                },
                markPoint: { /*图表标记*/
                    symbol:mode==='release'?'image://'+urls()+'statics/webStatics/images/MaintainReport/downHint.png':'image://../../statics/webStatics/images/MaintainReport/downHint.png', /*图片标记*/
                    symbolSize:[fontSize(1.11),fontSize(1.0)],
                    symbolOffset:[0,'40%'], /*标记相对于原本位置的偏移*/
                    silent:true,
                    markLine:{ /*图表标线*/
                        lineStyle:{
                            show:true,
                            width:2,
                            type:'dashed'
                        },
                        data:[{
                            name:'最大值',
                            type:'max',
                            valueIndex:1,/*y轴上的最大值*/
                        },
                            {
                                name:'终点',yAxis:0,
                                valueIndex:1,/*y轴上的最大值*/
                            }
                        ]
                    },
                    label:{
                        formatter:`一类:${payMaxGotNotPay}\n{b}:{c}`,
                        position:['0','50%'],
                        offset:[fontSize(0.15),0],
                        fontSize:fontSize(0.15),
                        lineHeight:fontSize(0.20),
                        color:'#44f3f0',
                    },
                    rich:{
                        b:{
                            fontSize:fontSize(0.15),
                        }
                    },
                    data: [
                        {type: 'max', name: '二类'},
                    ]
                },
            }
        ]
    };
    var myChart4 = echarts.init(document.getElementById('wipline'));
    myChart4.setOption(option4);
};


/*处理数字位*/
var format_number= function(n){
    var b=parseInt(n).toString();
    var len=b.length;
    if(len<=3){return b;}
    var r=len%3;
    return r>0?b.slice(0,r)+","+b.slice(r,len).match(/\d{3}/g).join(","):b.slice(r,len).match(/\d{3}/g).join(",");
};

/*请求成功后执行*/
var refreshTime;
var successfn=function(res){
    $(".container>.wrapper").css("display","block");
    $(".container>.loading").css("display","none");
    refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
    let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据</p>`
    let pie=res.data.maintenanceDetailPie; /*维修周期管理*/
    fixedCycle(pie.percent>0?(pie.percent/100):0,pie.badnum||0,pie.up5num||0,pie.down5num||0);/*刷新维修周期管理*/
    var maintenanceNumber=res.data.maintenanceNumber;
    /*维修超时TOP10*/
    var fixedOverTime=res.data.maintenanceCycleTop10;
    if(fixedOverTime.length>0){
        fixedOverTimeEvent(fixedOverTime,timeType,maintainStep);/*维修超时TOP10*/

    }else{
        $(".fixedOverTime>.list>.swiper-container>.orderList").html(html);
    }

    /*修改维修数量*/
    $(".container>.wrapper>.row>.right .pieChart >.pieChartRow>.pie .fixedNum>.number").text(format_number(maintenanceNumber.finishQty||0));
    $(".container>.wrapper>.row>.right .pieChart >.pieChartRow>.pie .fixedState>p:eq(0)>span").text(format_number(maintenanceNumber.maintainQty||0));
    $(".container>.wrapper>.row>.right .pieChart >.pieChartRow>.pie .fixedState>p:eq(1)>span").text(format_number(maintenanceNumber.totalQty||0));

    /*二次回流的数据不全*/
    if(res.data.tworeflux&&res.data.tworeflux.xaxis.length>0&&res.data.tworeflux.trueData.length>0){
        let tworeflux=res.data.tworeflux;
        myChart3Event(tworeflux);
    }else{
        $("#secondaryCircumfluence").html(html);
    }

    /*单板WIP 数据不够，需要变一下*/
    if(res.data.veneerWip&&res.data.veneerWip.xaxis.length>0&&res.data.veneerWip.trueData.length>0){
        myChart4Event(res.data.veneerWip);
    }else{
        $("#wipline").html(html);
    }

    /*不良分布走势图*/
    if(res.data.trendData&&(res.data.trendData.series.length>0)&&(res.data.trendData.xAxisData.length>0) ){
        lineChartEvent(res.data.trendData);
    }else{
        $("#areaChart").html(html);
    }

    // 自动切换整机和单板
    let time=refreshTime;
    var startTime = new Date().getTime();
    let timer1=setTimeout(function(){
        time-=3000*60; /*为了结束定时器做的时间*/
        var thisItem=$(".container>.wrapper>.row>.left>.row>.toggleBtn>p");
        if( !thisItem.find(".icon").hasClass("active")){
            thisItem.find(".icon").addClass("active").parent("p").siblings("p").find(".icon").removeClass("active");
        }else{
            //thisItem.find(".icon").addClass("active").parent("p").siblings("p").find(".icon").addClass("active");
        }
        thisItem.find(".icon").toggleClass("active");
        if( $(thisItem).find(".active").next().text()=='单板'){
            maintainStep="front";//0 单板  1整机   front单板
            $(".reflowSoldering>.chart>.theme>.title").text("单板WIP");
            $("#secondaryCircumfluence").css({'visibility':'hidden'}) ;
            $("#wipline").css({'visibility':'visible'});
            $(".container>.wrapper").css("display","none");
            $(".container>.loading").css("display","block");
            request('MaintainReport','timeType='+timeType+'&maintainStep='+maintainStep,'GET',successfn,errorfn);

        }else{
            maintainStep="BACK";//0 单板  1整机  BACK整机
            $(".reflowSoldering>.chart>.theme>.title").text("二次回流监控");
            $("#secondaryCircumfluence").css({'visibility':'visible'}) ;
            $("#wipline").css({'visibility':'hidden'});
            $(".container>.wrapper").css("display","none");
            $(".container>.loading").css("display","block");
            request('MaintainReport','timeType='+timeType+'&maintainStep='+maintainStep,'GET',successfn,errorfn);
        }
        clearTimeout(timer1);
        timer1=null;
    },2*60*1000);
    selectEvent(timer1);/*选择的事件掉哟个*/
    /*自动刷新页面*/
    let timer2=setTimeout(()=>{
        clearTimeout(timer2);
        timer2=null;
        window.location.reload();
    }, refreshTime);


};
/*请求失败*/
var errorfn=function(e){
    alert("数据请求失败");
    let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
    $(".container>.wrapper").css("display","block");
    $(".container>.loading").css("display","none");
    $(".left .chart>.echartWrap").html(html);
    $(".right .top .reflowSoldering>.chart>div").html(html);
    $(".right .bottom>.chart>.showChart ").html(html);
};
$(function(){
    let legend=$(".pieChart>.pieChartRow>.toggleBtn>li");
    /*初始化的图例选中样式*/
    for(let item of legend){
        if($(item).find("span").text()=="今日" && timeType=="dd"&&!$(item).find(".icon").hasClass("active")){
            $(item).find(".icon").addClass("active")
        }
        if($(item).find("span").text()=="本周" && timeType=="iw"&&!$(item).find("icon").hasClass("active")){
            $(item).find(".icon").addClass("active")
        }
        if($(item).find("span").text()=="本月" && timeType=="mm"&&!$(item).find("span").hasClass("active")){
            $(item).find(".icon").addClass("active")
        }
    }

    /*获取数据*/
    $(".container>.wrapper").css("display","none");
    request('MaintainReport','timeType='+timeType+'&maintainStep='+maintainStep,'GET',successfn,errorfn);

});