/*声明一个变量存界面宽度*/
var clientWidth=document.documentElement.clientWidth;
var clientHeight=document.documentElement.clientHeight;

/*交付分析-订单维度*/
let chart1Event=function(resData){
    var myChart1 = echarts.init(document.getElementById('orderFormChart'));
// 使用刚指定的配置项和数据显示图表。
    let orderTotalQty=resData.orderTotalQty;/*订单总数*/
    var totalQtyWidth; /*右边红框的左偏移量*/

    var setData=[{name:'未交付', icon: 'circle',value:resData.orderUndeliveryQty},{name:'已交付', icon: 'circle',value:resData.orderDeliveryQty},{name:'预期值', icon: 'circle',value:resData.ordeqTy}];
    var option={
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} <br/>({d}%)",
            confine:true, /*是否将tooltip框限制在图标区域内，默认false*/
            position: [fontSize(0),fontSize(1)]
        },
        roseType:false,/* 值==radius 扇区圆心角展现数据的百分比，半径展现数据的大小*/
        legend: {
            orient: 'vertical', //垂直排列
            x:'62%',
            top:'50%',
            itemWidth:fontSize(0.18), //图例标记的宽度
            itemHeight:fontSize(0.18), //图例标记的高度
            itemGap:fontSize(0.32),//图例项之间的间距
            data: setData,
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
                return name + ' ：' + (parseInt(target));
            },
            // 设置文本颜色
            textStyle:{
                color: '#ffffff',
                fontSize: fontSize(0.2),
            }
        },
        graphic: [  //是原生图形元素组件
            {
                type: 'group',
                left: '62%',
                top: '30%',
                children: [
                    {
                        type: 'rect',
                        left: 'center',
                        top: 'center',
                        z: 100,
                        shape: {
                            width: fontSize(1.30),
                            height: fontSize(0.3)
                        },
                        style: {
                            width: fontSize(0.7),
                            height: fontSize(0.2),
                            fill: '#ed5588'
                        }
                    },
                    {
                        type: 'image', /*图片*/
                        id: 'logo',
                        right: fontSize(0.43),
                        top: 'center',
                        z: 100,
                        bounding: 'raw',
                        // origin: [10, 10],
                        style: {
                            image:mode==='release'?(urls()+'statics/webStatics/images/OrderReport/icon/amountIcon.png'):'../../statics/webStatics/images/OrderReport/icon/amountIcon.png',
                            width: fontSize(0.14),
                            height: fontSize(0.14)
                        }
                    },
                    {
                        type: 'text',   //文字80
                        right: 'center',
                        top: 'center',
                        z: 100,
                        style: {
                            fill: '#fff',
                            text: '订单总数量',
                            font: `normal 14PX Microsoft YaHei`,
                            fontSize:fontSize(0.16)
                        }
                    }
                ]
            },
            {
                type: 'group',
                bounding: 'raw',
                left: '62%',
                top: '43%',
                z: 100,
                shape: {
                    width:totalQtyWidth,
                },
                style: {
                    fill: 'rgba(0,0,0,0.3)'
                },
                children: [
                    {
                        type: 'text',   //文字80
                        left: 'left',
                        top: 'center',
                        z: 100,
                        style: {
                            fill: '#fff',
                            width:  fontSize(0.15),
                            height:  fontSize(0.3),
                            text: orderTotalQty,
                            font: `bold 0 Haettenschweiler`,
                            fontSize:fontSize(0.36)
                        }
                    }
                ]
            }

        ],
        series : [
            {
                name: '订单数量占比',
                type: 'pie',
                radius : '40%',
                center: ['30%', '50%'],
                data:[
                    {
                        value:setData[1].value,
                        name:'已交付',
                        itemStyle:{
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#35a2ee' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#0665da' // 100% 处的颜色
                                }
                                ],
                                global: false // 缺省为 false
                            }
                        },
                        // 高亮样式。
                        emphasis: {
                            // 高亮时点的颜色。
                            color: 'blue'
                        }
                    },
                    {
                        value:setData[0].value,
                        name:'未交付',
                        // selectedMode: 'single',
                        // selected:false,
                        itemStyle:{
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                    offset: 0, color: '#ff9c00' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#f9b524' // 100% 处的颜色
                                }
                                ],
                                global: false // 缺省为 false
                            }
                        }
                    }
                ],
                label: {
                    normal: {
                        fontSize:fontSize(0.18),
                        formatter: '{per|{d}%}',
                        rich: {
                            per: {
                                color: '#eee',
                                fontSize:fontSize(0.18),
                                padding: [fontSize(0.02), fontSize(0.04)],
                                borderRadius: fontSize(0.02)
                            }
                        }
                    }
                },
                labelLine: {
                    normal: {
                        length:fontSize(0.17),
                        length2:fontSize(0.17),
                        lineStyle: {
                            color: '#ffffff'
                        }
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: fontSize(0.1),
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }

            },
            {
                name:'目标值',
                type:'pie',
                radius: ['45%', '49%'],
                center: ['30%', '50%'],
                label: {
                    normal: {
                        show:false,
                        fontSize:fontSize(0.18),
                        formatter: '{per|{d}%}',
                        rich: {
                            per: {
                                color: '#eee',
                                fontSize:fontSize(0.18),
                                padding: [fontSize(0.02), fontSize(0.04)],
                                borderRadius: fontSize(0.02)
                            }
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show:false,
                        length:fontSize(0.17),
                        length2:fontSize(0.17),
                        lineStyle: {
                            color: '#ffffff'
                        }
                    }
                },
                data:[
                    {value:setData[2].value, name:'预期值',itemStyle:{color:'#3bebe8'},label:{show: true},labelLine: {show: true}},
                    {value:orderTotalQty-setData[2].value,name:'实际与预期差值',itemStyle:{color:'rgba(1,1,1,0)'}}
                ]
            }
        ],
    };
    myChart1.setOption(option);
};

/*生产数量--折线区域图*/
let chart2Event=function(resDta){
    // var delivered=[820, 932, 901, 934, 1290, 1330, 120];/*已交付*/
    var delivered=resDta.dataPay;/*已交付*/
    var nuDelivered=resDta.dataNotPay;/*未支付*/
    Array.prototype.getMax=function(){
        return Math.max(...delivered);
    };
    var payMaxGotNotPay;/*已交付最大对应的未交付*/
    for(var i=0;i<delivered.length;i++){
        if(delivered.getMax()==delivered[i]){
            payMaxGotNotPay= nuDelivered[i];
        }
    }
    var total=payMaxGotNotPay+delivered.getMax();
    var payRate=((delivered.getMax()/total)*100).toFixed(2);

    var option = {
        tooltip: {
            position: [10, 10],
            trigger: 'axis',
        },
        legend: {
            x:'75%',
            top:clientWidth>786?'6%':0,
            bottom:fontSize(0.5),
            orient: 'vertical',
            icon: 'circle',
            itemWidth:fontSize(0.18), //图例标记的宽度
            itemHeight:fontSize(0.18), //图例标记的高度
            itemGap:fontSize(0.32),//图例项之间的间距
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.2),
            },
            data:['已交付','未交付']
        },
        grid: {
            left: '5%',
            right: '10%',
            height:'75%',
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
                formatter: function (value, index) {
                    // 格式化成月/日，只在第一个刻度显示年份
                    var date = new Date(value);
                    var texts = [(date.getMonth() + 1), date.getDate()];
                    // if (index === 0) {
                    //     texts.unshift(date.getYear());
                    // }
                    return texts.join('/');
                }
            },
            data: resDta.xAxisData
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                color:'#fff',
                fontSize:fontSize(0.15),
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
                name:'已交付',
                max:delivered.getMax,
                data: delivered,
                type: 'line',
                z:2,/*这个在上面*/
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
                },
                markPoint: { /*图表标记*/
                    symbol:mode==='release'?'image://'+urls()+'statics/webStatics/images/OrderReport/downHint.png':'image://../../statics/webStatics/images/OrderReport/downHint.png', /*图片标记*/
                    symbolSize:[fontSize(1.11),fontSize(1.525)],
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
                        formatter:`${payRate}%\n{b}:{c}\n未交付:${payMaxGotNotPay}`,
                        position:['0','50%'],
                        offset:[fontSize(0.15),0],
                        fontSize:fontSize(0.15),
                        lineHeight:fontSize(0.25),
                        color:'#44f3f0',
                    },
                    rich:{
                        rate:{
                            fontSize:fontSize(0.3),
                            marginBottom:fontSize(0.12),
                            color:'#fff',
                        },
                        b:{
                            fontSize:fontSize(0.15),
                        }
                    },
                    data: [
                        {type: 'max', name: '已交付'}
                    ]
                },
            },
            {
                name:'未交付',
                data: resDta.dataNotPay,
                type: 'line',
                z:1,
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
            }
        ]
    };
    var myChart2 = echarts.init(document.getElementById('productionQuantityChart'));
    myChart2.setOption(option);
};


/*环状图  未交付分析（by生产数量）*/
let chart3Event=function(resData){
    //0-在製品,1-待上線,2-未開工單

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

    var myChart3 = echarts.init(document.getElementById('nonDeliveryChart'));
//使用刚指定的配置项和数据显示图表。
    var setmyChart3Data=[];
    var obj={};
    for(let item of resData ){
        if(item.orderStatus==0){
            obj={};
            obj.value=item.qty;
            obj.name="在制数";
            obj.itemStyle= setItemStyle('#eba52c','#eb6d11')
            setmyChart3Data.push(obj);
        }
        if(item.orderStatus==1){
            obj={};
            obj.value=item.qty;
            obj.name="待上线数";
            obj.itemStyle= setItemStyle('#48f6f3','#02bdba')
            setmyChart3Data.push(obj);
        }
        if(item.orderStatus==2){
            obj={};
            obj.value=item.qty;
            obj.name="未开工单数";
            obj.itemStyle= setItemStyle('#35a2ee','#0665da')
            setmyChart3Data.push(obj);
        }
    }
    var option={
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)",
            position: function (point, params, dom, rect, size) {
                return [0,point[1]-fontSize(3)];
            }
        },
        legend: {
            z:3,/*高于图表*/
            orient: 'vertical',
            x: '63%',
            y:'middle',
            itemWidth:fontSize(0.18), //图例标记的宽度
            itemHeight:fontSize(0.18), //图例标记的高度
            itemGap:fontSize(0.32),//图例项之间的间距
            data:['未开工单数','待上线数','在制数'],
            formatter:  function(name){
                var total = 0;
                var target;
                let  data=setmyChart3Data;
                for (var i = 0, l = data.length; i < l; i++) {
                    total += data[i].value;
                    if (data[i].name == name) {
                        target = data[i].value;
                    }
                }
                return name + ' ：' + (parseInt(target));
            },
            icon: 'circle',
            // 设置文本颜色
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.2),
            }
        },
        series: [
            {
                name:'生产数量',
                type:'pie',
                radius: ['32%', '40%'],
                center: ['28%', '50%'],
                avoidLabelOverlap: false,
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
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: fontSize(0.1),
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: '#ffffff'
                        },
                        length:fontSize(0.15),
                        length2:fontSize(0.2),
                    }
                },
                data:setmyChart3Data
            }
        ]
    };
    var currentIndex = -1;
    setInterval(function () {
        var dataLen = option.series[0].data.length;
        // 取消之前高亮的图形
        myChart3.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        currentIndex = (currentIndex + 1) % dataLen;
        // 高亮当前图形
        myChart3.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: currentIndex
        });
        // 显示 tooltip
        // myChart3.dispatchAction({
        //     type: 'showTip',
        //     seriesIndex: 0,
        //     dataIndex: currentIndex
        // });
    }, 1000);
    myChart3.setOption(option);
};


let payCycleRouting=function(resData,baseRate){
    var dataArr=resData.orderCycleTimeOutDTOS;
    /*维修超时*/
    var dataList=[
        {
            id:1,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:100,
        },
        {
            id:2,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:83
        },
        {
            id:3,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:66.67
        },
        {
            id:4,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:41.67
        },
        {
            id:5,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:33.333
        },
        {
            id:6,
            orderNumber:'SN0000000000',
            overTime:'0',
            rate:25
        },
    ];
    var data=dataArr?dataArr:dataList;
    var info={
        achievingRate:resData.orderCycleTimeOutPerctDTO.percent?resData.orderCycleTimeOutPerctDTO.percent/100:0, /*实际达成率*/
        percentGoalMet:baseRate?baseRate/100:0, /*目标达成率*/
        productedNum:resData.orderCycleTimeOutPerctDTO.totalQty?resData.orderCycleTimeOutPerctDTO.totalQty:0, /*已生产*/
        overTimeNum:resData.orderCycleTimeOutPerctDTO.overTimeQty?resData.orderCycleTimeOutPerctDTO.overTimeQty:0,   /*超时生产*/
    };
    if(data.length>0){
        var max=Number(data[0].overTime);
        for(var item of data){
            var time=Number(item.overTime);
            time>max?max=time:max;
        }
        var html="";
        var cont=0;
        for(let item of data){
            cont++;
            let rate=((item.overTime/max)*100).toFixed(2);
            html+=`<li class="swiper-slide">
                            <div class="importantInfo">
                                <span class="no">${cont}</span>
                                <span class="order">${item.orderNumber?item.orderNumber:"000000000"}</span>
                                <span class="time">${item.overTime?item.overTime:"0"}H</span>
                                <i class="progressBar">
                                <i class="icon" style="display:inline-block;width:${rate}%" data-rate="${rate}"></i>
                            </i>
                            </div>
                           
                            <div class="cover">
                                <p class="detail">${item.introduce?item.introduce:"暂无详情信息"}</p>
                            </div>
                        </li>`
        }
        $(".orderList").html(html);
        var list= $(".swiper-container>.orderList>li .icon");
        var arr=$(".swiper-container>.orderList>li");
        for(let item of arr){
            let rate=$(item).find(".icon").data("rate");
            if(rate>80){
                $(item).find(".icon").css({
                    "background-image":'linear-gradient(-90deg, #eba52c, #eb4911)',
                    "transition":"width 3s ease"
                })
            }else if(rate<=80&&rate>40){
                $(item).find(".icon").css({"background-image":'linear-gradient(-90deg, #12a9e1 0%, #0063d3 100%)',
                    "transition":"width 3s ease"
                })
            }else{
                $(item).find(".icon").css({"background-image":'linear-gradient(-90deg, #48f6f3 0%, #02bdba 100%)',
                    "transition":"width 3s ease"
                })
            }
        }

        /*最后一块：swiper 配置*/
        var clientWidth=document.documentElement.clientWidth;
        var spaceBetween=clientWidth>786?9:3;
        let mySwiper = new Swiper('.swiper-container', {
            direction:'vertical',
            spaceBetween: spaceBetween,/*下边距*/
            loop: true,
            loopedSlides: 0,
            slidesPerView:dataArr.length>6?6:dataArr.length, //显示几张
            autoplay: {
                delay: 2000,
                disableOnInteraction: false,
            },
        });
        //鼠标覆盖停止自动切换
        mySwiper.el.onmouseover = function(){
            mySwiper.autoplay.stop();
        };
        //鼠标离开开始自动切换
        mySwiper.el.onmouseout = function(){
            mySwiper.autoplay.start();
        };
        // 周期达成率
        cycleRate(info.achievingRate,info.percentGoalMet,info.productedNum,info.overTimeNum);
    }else{
        html=`<div class="length0" style="margin:30% auto;font-size:1.66rem">很抱歉,后台没有数据~~</div>`;
        $(".orderList").html(html);
    }


};



/*产品工序流程图*/
$(function(){
    var liArray=$(".productProcess>ul>li");
    var n=0;
    setInterval(function(){
        n>liArray.length && (n=0);
        var t=liArray.eq(n);
        if(n==0){
            t.siblings().children("i").css({"animation":" "}).removeClass("active");
        }
        setTimeout(function(){
            t.children("i").css({"animation":" myblink 150ms linear 0s 5 alternate"}).addClass("active");
            t.children("img").css({'transform': 'scale(1.2)', '-ms-transform':'scale(1.2)', '-moz-transform':'scale(1.2)'}).addClass("scale");
            t.siblings().children("img").css({'transform': '', '-ms-transform':'', '-moz-transform':''}).removeClass("scale");
            if(n==6){
                t.siblings().children("i").css({"animation":" "}).removeClass("active");
                t.children("img").css({'transform': 'scale(1.2)', '-ms-transform':'scale(1.2)', '-moz-transform':'scale(1.2)'}).addClass("scale");
                t.siblings().children("img").css({'transform': '', '-ms-transform':'', '-moz-transform':''}).removeClass("scale");
            }
        },1500);
        n++;
    },700);
});


/*交付周期达成率*/
let cycleRate=function(achievingRate,percentGoalMet,productedNum,overTimeNum){
    let trueRate=0;
    let timer=setInterval(function () {
        trueRate+=0.01;
        if(trueRate>=achievingRate){
            clearInterval(timer);
            timer=null;
        }
        $(".bottom .count .ratio>p:eq(1)>.trueRate").css({"width":(trueRate*100).toFixed(2)+'%'}).text((trueRate*100).toFixed(2)+'%');
    },20);
    $(".bottom .count .ratio>p:eq(1)>i:eq(1)").css({"left":(percentGoalMet*100).toFixed(2)+'%'});
    if(achievingRate>=percentGoalMet){
        $(".bottom .count .ratio>p:eq(1)>.trueRate").css({ "background-image": "linear-gradient(90deg,#1668ef 0%,#2addda 100%)"});
    }else{
        $(".bottom .count .ratio>p:eq(1)>.trueRate").css({"background-image": "linear-gradient(-90deg,#eba52c 0%,#eb4911 100%)"});
    };
    $(".bottom .count .status>p:eq(0)>.productedNum").text(productedNum);
    $(".bottom .count .status>p:eq(1)>.productedNum").text(overTimeNum);

};


/*请求成功后执行*/
var refreshTime;
var successfn=function(res){
    $(".container>.loading").css("display","none");
    $(".container>.wrapper").css("display","block");

    refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
    let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，后台没数据</p>`
    if(res.data.deliveryByOrder){
        chart1Event(res.data.deliveryByOrder);/*交付分析-订单维度*/
    }else{
        $("#orderFormChart").html(html);
    }
    if(res.data.deliveryByOrderQty&&res.data.deliveryByOrderQty.dataPay.length>0&&res.data.deliveryByOrderQty.dataNotPay.length>0&&res.data.deliveryByOrderQty.xAxisData.length>0){
        chart2Event(res.data.deliveryByOrderQty);/*交付分析-生产数量维度*/
    }else{
        $("#productionQuantityChart").html(html);
    }
    if(res.data.undeliveryPerByQty){
        chart3Event(res.data.undeliveryPerByQty);/*未交付分析（生产数量）*/
    }else{
        $("#nonDeliveryChart").html(html);
    }
    if(res.data.orderCycleTimeOut&&res.data.orderCycleTimeOut.orderCycleTimeOutDTOS.length>0&&res.data.boardconfig.complianceRate){
        var baseRate=res.data.boardconfig.complianceRate;/*达成率标准*/
        payCycleRouting(res.data.orderCycleTimeOut,baseRate);/*交付周期追踪*/
    }else{
        $(".bottom>.row>.chart:eq(2)>.contain").html(html);
    }
    // 自动刷新
    let timer1=setTimeout(()=>{
        clearTimeout(timer1);
        timer1=null;
        window.location.reload();
    }, refreshTime);
};
/*请求失败*/
var errorfn=function(e){
    alert("数据请求失败");
    $(".container>.loading").css("display","none");
    $(".container>.wrapper").css("display","block");
    let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">请求失败了，☹☹</p>`;
    $("#orderFormChart").html(html);
    $("#productionQuantityChart").html(html);
    $("#nonDeliveryChart").html(html);
    $(".bottom>.row>.chart:eq(2)>.contain").html(html);
};
$(function(){
    $(".container>.wrapper").css("display","none");
    request('OrderReport','','GET',successfn,errorfn);
});
