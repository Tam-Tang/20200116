/*定义变量clientWidth 存储界面宽度*/
var clientWidth = document.documentElement.clientWidth;
var timeType='iw';//dd  iw  mm  日   周  月


let radarMap=function(resData,upDataTime,timeType){
    let datarangetime=upDataTime.timeStart+"~"+upDataTime.timeEnd;
    let baseData=[],trueData=[],indicator=[];
    for(let item of resData){
        baseData.push(item.starndardYield) ;/*标准百分比*/
        trueData.push(item.yield); /*实际百分比*/
        indicator.push({name:item.stationName+'良率',max:100,rate:item.starndardYield})
    }


    /*颜色设置*/
    var setItemStyle = function (color1, color2) {
        var itemStyle = {
            color: {
                type: 'radial',
                x: 0.5,
                y: 0.5,
                r: 0.5,
                colorStops: [{
                    offset: 0, color: color1 // 0% 处的颜色
                }, {
                    offset: 1, color: color2 // 100% 处的颜色
                }],
                global: false // 缺省为 false
            },

        };
        return itemStyle
    };
    /*1.雷达图*/
    var myChart1 = echarts.init(document.getElementById('productionQuantityChart'));
    /*label 标签值*/
    let label = function (color) {
        return {
            normal: {
                show: true,
                formatter: '{per|{c}%}',
                rich: {
                    per: {
                        width:fontSize(0.5),
                        color: color,
                        fontSize: fontSize(0.3),
                        fontFamily: 'Haettenschweiler',
                        textAlign:'center'
                    }
                }
            }
        }
    };
    /*基准80%的八边形*/

    let base = function (name) {
        let obj = {
            name: name,
            zlevel: 1,/*显示在下面*/
            itemStyle: setItemStyle('rgba(0,0,0,0)', '#8c8c8c'),
            areaStyle: {
                normal: {
                    opacity: 0.5
                }
            },
            symbol: 'none',
            // value: [80, 80, 80, 80, 80, 80, 80, 80]
            value:baseData
        };
        return obj;
    };

// 指定图表的配置项和数据
    var dataRes=[];
    if(timeType=="dd"){
        dataRes = [
            /*标准*/
            base('今日'),
            {
                name: '实际',
                zlevel: 2,/*显示在上面*/
                itemStyle: setItemStyle('rgba(0,0,0,0)', '#26ceff'),
                symbol: 'circle',
                symbolSize: fontSize(0.13),
                value:trueData,
                label: label('#26ceff'),

            }

        ]
    }

    if(timeType=="iw"){
        dataRes = [
            base('本周'),
            {
                name: '实际',
                zlevel: 2,/*显示在上面*/
                itemStyle: setItemStyle('rgba(0,0,0,0)', '#B3E4A1'),
                symbol: 'circle',/*圆圈*/
                symbolSize: fontSize(0.13),
                value:trueData,
                label: label('#B3E4A1')
            }

        ];
    }

    if(timeType=="mm"){
        dataRes = [
            base('本月'),
            {
                name: '实际',
                zlevel: 2,/*显示在上面*/
                itemStyle: setItemStyle('rgba(0,0,0,0)', '#eb9524'),
                symbol: 'circle',/*圆*/
                symbolSize: fontSize(0.13),
                value:trueData,
                label: label('#eb9524')
            }

        ];
    }

    var lineStyle = {
        normal: {
            width: 1,
            opacity: 0.5
        }
    };
    var restOption=function(realityIConColor,dataRes){
        var option = {
            backgroundColor: 'rgba(255,255,255,0)',
            legend: {
                show:true,
                x: '4%',
                top: clientWidth>786?'3%':0,
                data: timeType=="dd"?"今日":(timeType=="iw"?"本周":(timeType="mm"?"本月":"")),   /*需要不同状态不同的赋值与series里面的值对应*/
                itemGap: fontSize(0.22),
                itemWidth: fontSize(0.25),
                itemHeight: fontSize(0.25),
                textStyle: {
                    color: '#fff',
                    fontSize: fontSize(0.24)
                },
                selectedMode: 'single' //图例选择的模式，控制是否可以通过点击图例改变系列的显示状态。'single' 或者 'multiple' 使用单选或者多选模式。
            },
            graphic: [  //是原生图形元素组件
                {
                    type: 'group',
                    left: '4%',
                    top: '7%',
                    children: [
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: { /*设置举行元素的定位和大小*/
                                width: fontSize(4.6),
                                height: fontSize(0.33)
                            },
                            style: {
                                width: fontSize(4.60),
                                height: fontSize(0.33),
                                fill: 'rgba(0,152,255,0.5)',
                                borderRadius:fontSize(0.165),
                            }
                        },
                        {
                            type: 'text',   //文字
                            left: fontSize(1.3),
                            top: 'center',
                            z: 100,
                            style: {
                                fill: '#fff',
                                text: datarangetime,
                                font: `normal 14PX Microsoft YaHei`,
                                fontSize: fontSize(0.16),
                                textAlign:"middle"
                            }
                        }]
                },
                {
                    type: 'group',
                    left: '4%',
                    top: '7%',
                    children: [
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: {
                                width: fontSize(1.20),
                                height: fontSize(0.33)
                            },
                            style: {
                                width: fontSize(1.20),
                                height: fontSize(0.33),
                                fill: '#0f91e3'
                            }
                        },
                        {
                            type: 'image', /*图片*/
                            id: 'logo',
                            left: fontSize(0.13),
                            top: 'center',
                            z: 100,
                            bounding: 'raw',
                            // origin: [10, 10],
                            style: {
                                image:mode==='release'?(urls()+'statics/webStatics/images/nowTime.png'):'../../statics/webStatics/images/nowTime.png',
                                width: fontSize(0.16),
                                height: fontSize(0.16)
                            }
                        },
                        {
                            type: 'text',   //文字
                            id:'title',
                            left: fontSize(0.4),
                            top: 'center',
                            z: 100,
                            style: {
                                fill: '#fff',
                                text: '数据周期',
                                font: `normal 14PX Microsoft YaHei`,
                                fontSize: fontSize(0.16)
                            }
                        }]
                },
                {
                    type: 'group',
                    left: '4%',
                    top: '11%',
                    children: [
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: {
                                width: fontSize(1.30),
                                height: fontSize(0.3)
                            },
                            style: {
                                width: fontSize(0.3),
                                height: fontSize(0.2),
                                fill: 'rgba(0,0,0,0)'
                            }
                        },
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: {
                                width: fontSize(0.3),
                                height: fontSize(0.18)
                            },
                            style: {
                                width: fontSize(0.3),
                                height: fontSize(0.18),
                                fill: '#8c8c8c'
                            }
                        },
                        {
                            type: 'text',   //文字
                            left: fontSize(0.5),
                            top: 'center',
                            z: 100,
                            style: {
                                fill: '#fff',
                                text: '目标',
                                font: `normal 14PX Microsoft YaHei`,
                                fontSize: fontSize(0.16)
                            }
                        }]
                },
                {
                    type: 'group',
                    left: '4%',
                    top: '15%',
                    children: [
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: {
                                width: fontSize(1.30),
                                height: fontSize(0.3)
                            },
                            style: {
                                width: fontSize(0.3),
                                height: fontSize(0.2),
                                fill: 'rgba(0,0,0,0)',
                            }
                        },
                        {
                            type: 'rect',
                            left: 'left',
                            top: 'center',
                            z: 100,
                            shape: {
                                width: fontSize(0.3),
                                height: fontSize(0.18)
                            },
                            style: {
                                width: fontSize(0.3),
                                height: fontSize(0.18),
                                fill: realityIConColor
                            }
                        },
                        {
                            type: 'text',   //文字80
                            left: fontSize(0.5),
                            top: 'center',
                            z: 100,
                            style: {
                                fill: '#fff',
                                text: '实际',
                                font: `normal 14PX Microsoft YaHei`,
                                fontSize: fontSize(0.16)
                            }
                        }]
                }

            ],
            radar: {   /*雷达图坐标组件，专用于雷达图*/
                indicator:indicator,
                shape: 'circle',
                splitNumber: 11,
                name: {
                    formatter:function(name,indicator){
                        return `${name}\n{a|${indicator.rate}%} `
                    },

                    textStyle: {
                        color: '#fff',
                        fontSize: fontSize(0.24) /*字体大小*/
                    },
                    rich:{
                        a:{
                            height:fontSize(0.5),
                            align:'center',
                            fontSize:fontSize(0.2)
                        }
                    }
                },
                radius: '70%', /*半径*/
                splitLine: { /*分割线*/
                    lineStyle: {
                        width: fontSize(0.01),
                        color: '#072a31',
                        // color: [
                        //     'rgba(7, 42, 49, 0)', 'rgba(7, 42, 49, 0.5)',
                        //     'rgba(7, 42, 49, 0.6)', 'rgba(7, 42, 49, 0.7)',
                        //     'rgba(7, 42, 49, 0.8)', 'rgba(7, 42, 49, 0.9)',
                        //     'rgba(7, 42, 49, 1)'
                        //
                        // ].reverse()
                    }
                },
                splitArea: {
                    show: false
                },
                axisLine: { /*轴线*/
                    lineStyle: {
                        color: '#072a31',
                        width: fontSize(0.01)
                    }
                }
            },
            series: [
                {
                    name: timeType=="dd"?"今日":(timeType=="iw"?"本周":(timeType="mm"?"本月":"")),
                    type: 'radar',
                    lineStyle: lineStyle,
                    data:dataRes,
                    areaStyle: {
                        normal: {
                            opacity: 0.5
                        }
                    }
                }
            ]
        };
        return option
    };
    if(timeType=="dd"){
        /*需要不同状态不同的赋值*/
        myChart1.setOption(restOption('#26ceff',dataRes));
    }
    if(timeType=="iw"){
        myChart1.setOption(restOption('#38cccc',dataRes));
    }
    if(timeType=="mm"){
        myChart1.setOption(restOption('#eb9524',dataRes));
    }

};
/*点击今日/本周/本月*/
$(".container>.wrapper>.row>.chart>.myLegend").on('click', 'li', function (e) {
    if ($(this).find(".selectBox").hasClass('active')) {
        return true;
    } else {
        $(this).find(".selectBox").addClass("active").parent("li").siblings("li").find(".selectBox").removeClass("active");
        var selected=$(this).find(".selectBox").addClass("active").parent("li").text();
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
    request('QualityReport','timeType='+timeType,'GET',successfn,errorfn);
});


/*swiper轮播*/
let swiperEvent=function () {
    let that = this;
    let spaceBetween = clientWidth > 786 ? 9 : 3;
    var mySwiper = new Swiper('.swiper-container', {

        direction: 'horizontal',
        spaceBetween: spaceBetween,/*下边距*/
        loop: true,
        loopAdditionalSlides: 0, //loop模式下会在slides前后复制若干个slide,，前后复制的个数不会大于原总个数。
        slidesPerView: 1, //显示几张
        speed:3000,
        autoplay: {
            delay: 3000,
            disableOnInteraction: false,
        },
        centeredSlides: false, //设定为true时，active slide会居中，而不是默认状态下的居左
        observer: true,//修改swiper自己或子元素时，自动初始化swiper
        observeParents: true,//修改swiper的父元素时，自动初始化swiper
        paginationClickable: true,	//点击分页器的指示点分页器会控制Swiper切换
        //如果需要分页器，即下面的小圆点,type=‘custom'自定义
        pagination: {
            el: '.swiper-pagination',
            type: 'custom',
            autoplayDisableOnInteraction: false,  /*自动切换*/
            renderCustom: function (mySwiper, current, total) {
                var paginationHtml = " ";
                for (var i = 0; i < total; i++) {
                    // 判断是不是激活焦点，是的话添加active类，不是就只添加基本样式类
                    if (i === (current - 1)) {
                        paginationHtml += '<span class="swiper-pagination-customs swiper-pagination-customs-active"></span>';
                    } else {
                        paginationHtml += '<span class="swiper-pagination-customs"></span>';
                    }
                }
                return paginationHtml;
            }
        },
        //产品需求,滑动向前向后,切换上/下一周.未来日期不可选.
        on: {
            init: function () {
                $.each($(".pie"),function(i,v){
                    $(this).attr("id","pie"+i);
                });
                $.each($(".lineChart"),function(i,v){
                    $(this).attr("id","lineChart"+i);
                });
                intEchart();
            },
        },
    });
    // $(".swiper-container .swiper-slide").on('mouseenter', '.pie', function (e) {
    //     mySwiper.autoplay.stop();
    //     console.log($(e.target));
    //
    //     $(this).css({
    //         '-webkit-transform': 'scaleX(0)',
    //         'transform': 'scaleX(0)',
    //         '-webkit-transition': '1s all ease',
    //         'transition': '1s all ease'
    //     });
    //     $(this).next().css({
    //         '-webkit-transform': 'scaleX(1)',
    //         'transform': 'scaleX(1)',
    //         '-webkit-transition': '1s 1s all ease',
    //         'transition': '1s 1s all ease'
    //     })
    //     console.log($(this));
    //     console.log($(this).find(".lineChart"));
    // });
    // $(".swiper-container .swiper-slide").on('mouseleave', '.lineChart', function () {
    //     $(this).css({
    //         '-webkit-transform': 'scaleX(0)',
    //         'transform': 'scaleX(0)',
    //         '-webkit-transition': '1s all ease',
    //         'transition': '1s all ease'
    //     });
    //     $(this).prev().css({
    //         '-webkit-transform': 'scaleX(1)',
    //         'transform': 'scaleX(1)',
    //         '-webkit-transition': '1s 1s all ease',
    //         'transition': '1s 1s all ease'
    //     });
    //
    //     });

    $(".swiper-container .swiper-slide .pie").hover(function(){
        // mySwiper.autoplay.stop();
        $(this).addClass("changeSmall");
        $(this).next().addClass("changeBag")
    },function(){
        //console.log("离开饼图");
        if($(this).hasClass("changeSmall") && $(this).next().hasClass("changeBag")){
            var  timer=setTimeout(()=> {
                $(this).next().removeClass("changeBag").addClass("changeSmall");
                $(this).removeClass("changeSmall").addClass("changeBag");
                 if($(this).hasClass("changeBag") && $(this).next().hasClass("changeSmall")){
                     $(this).removeClass("changeBag");
                     $(this).next().removeClass("changeSmall");
                     clearTimeout(timer);
                     timer=null;
                 }
             },5000);
        }

    });

    /*鼠标事件，进入悬停，移出开始自动播放*/
    $(".swiper-container").mouseenter(
        (e) => {//滑过悬停
            mySwiper.autoplay.stop();

        }).mouseleave(function () {//离开开启

        mySwiper.autoplay.start(); //开始自动播放
    })
};

var pieChartOption=[],lineChartOption=[];
let pieAndLineChart=function(resData){
    /*加载循环的html*/
    let html = '';
    let n=0;
    pieChartOption=[];
    lineChartOption=[];
    for (let  item of resData) {
        n++;
        html += ` <div class="swiper-slide echart" >
                    <!--饼图-->
                    <div class="pie" >
                    </div>
                    <!--折线图-->
                    <div class="lineChart">
                    </div>                
                 </div>`;
        pieChartOption.push(option1Event(item.pieChart)) ;
        lineChartOption.push(option2Event(item.lineChart));
    }
    pieChartOption.push(pieChartOption[0]);
    pieChartOption.unshift(pieChartOption[pieChartOption.length-1]);
    lineChartOption.push(lineChartOption[0]);
    lineChartOption.unshift(lineChartOption[lineChartOption.length-1]);
    $(".swiper-container").html('<div class="swiper-wrapper">'+html+'</div>');
    swiperEvent();/*调取是wiper*/
};

/*颜色设置*/
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
    };
    return itemStyle
};
var option1Event=function(resData){
    var option1 = {
        title: {
            text: `工序名称：${resData.titleText}`,
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.18),
                fontWeight: 'normal'
            },
            x: '5%',
            top: '13%'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: '6.4%',
            y: '35.55%',
            borderColor:'#2c2f58',
            itemGap: fontSize(0.4),
            itemWidth: fontSize(0.20),
            itemHeight: fontSize(0.20),
            data: resData.legendData,
            icon: 'circle',
            // 设置文本为红色
            textStyle: {
                color: '#ffffff',
                fontSize: fontSize(0.18),
                padding: [fontSize(0.04), fontSize(0.2), fontSize(0.04)]
            },

        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['50%', '65%'],
                center: ['70%', '50%'],
                avoidLabelOverlap: false,
                data: [
                    {
                        value: resData.seriesData[0], name: resData.legendData[0],
                        itemStyle: setItemStyle('#35a2ee', '#0665da')
                    },
                    {
                        value: resData.seriesData[1], name: resData.legendData[1],
                        itemStyle: setItemStyle('#48f6f3', '#02bdba')
                    },
                    {
                        value: resData.seriesData[2], name:resData.legendData[2],
                        itemStyle: setItemStyle('#eba52c', '#eb6d11')
                    }
                ],
                label: {
                    normal: {
                        show: true,
                        formatter: '{per|{d}%}',
                        rich: {
                            per: {
                                color: '#eee',
                                fontSize: fontSize(0.18),
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: true,
                        lineStyle: {
                            color: '#ffffff'
                        },
                        length: fontSize(0.2),
                        length2: fontSize(0.2),
                    }
                },
            }
        ]
    };
    return option1;
};

/*折线图*/
let option2Event=function(resData){
    var option2 = {   // 指定图表的配置项和数据
        title: {
            text: '良率趋势图',
            x: 'center',
            textStyle: {
                color: '#44f3f0',
                fontSize: fontSize(0.18),
            },
            padding: [fontSize(0.3), 10]
        },
        legend: {
            show: false, /*不显示*/
            data: ['趋势图']
        },
        grid: {/*绘图网格*/
            left: '13%',
            right: '7%',
            top: '20%',
            bottom: '20%',/*距离底部的距离*/
        },
        xAxis: [
            {
                type: 'category',
                data:resData.xaxis,
                boundaryGap: false,
                gridIndex: 0,
                axisLabel: {
                    color: '#fff',
                    fontSize: fontSize(0.16),
                }
            }
        ],
        yAxis: [
            {
                show: true,
                type: 'value',
                max: 100,
                axisLine:{
                    show:false,  /*是否显示坐标轴线*/
                },
                axisLabel: {
                    color: '#fff',
                    fontSize: fontSize(0.16),
                    formatter: '{value} %',
                    margin:fontSize(0.25) /*坐标轴线与表格间的间隙*/
                },
                gridIndex: 0,
                splitLine: { /*纵坐标的分割线*/
                    show: true,
                    lineStyle: {
                        color: '#2c2f58'
                    }
                }
            }
        ],
        series: [
            {
                name: '趋势图',
                type: 'line',
                yAxisIndex: 0,
                // 显示数值
                data: resData.seriesData,
                symbol: mode==='release'?'image://'+urls()+'statics/webStatics/images/MaintainReport/mark.png':'image://../../statics/webStatics/images/MaintainReport/mark.png',
                symbolSize: [fontSize(0.5), fontSize(0.55)],
                symbolOffset: [0, '-40%'],/*标记相对原本位置的偏移*/
                lineStyle: {
                    color: '#44f3f0',
                },
                label: {
                    normal: {
                        show: true,
                        formatter: '{per|{c}%}',
                        offset:[0,37],
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
                itemStyle: {
                    color: '#44f3f0'
                },
            }
        ]
    };
    return option2;
};



/*echart*/
var intEchart=function() {
    /*swiper 轮播Item获取*/
    var reswidth = $('.swiper-wrapper>.swiper-slide>.pie').width();
    var resheigth = $('.swiper-wrapper>.swiper-slide>.pie').height();
    var mySwiperSlid=$('.swiper-wrapper>.swiper-slide');/*每一个轮播的小项*/
    let i=0;
    for (let item of mySwiperSlid) {
        var myChart2 = echarts.init(document.getElementById("pie"+i)); /*环状图*/
        var myLineChart2 = echarts.init(document.getElementById("lineChart"+i));/*折线图*/
        myChart2.resize({ /*重新设置宽高*/
            width: reswidth,
            height: resheigth
        });

        for(var k=0;k<pieChartOption.length;k++){
            if(i==k){
                myChart2.setOption(pieChartOption[k]); /*环状图*/
                myLineChart2.setOption(lineChartOption[k]);/*折线图*/
            }
        }
        i++;

    }
};




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
/*PCBA直通率*/
var option3 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} <br/>({d}%)"
    },
    title: {
        text: '',
        x: 'center',
        y:'0%',
        bottom:fontSize(0.2),
        textStyle: {
            color: '#39faff',
            fontSize: fontSize(0.18),
        },
        padding: [fontSize(0.3), 10]
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
        data:['','']
    },
    grid: {
        left: '5%',
        right: '7%',
        top: '20%',
        // height:'100%',
        bottom:fontSize(0.2),
        borderWidth:0,/*网格的边框线宽*/
        containLabel: true /*grid 区域是否包含坐标轴的刻度标签*/
    },
    tooltip: {   /*提示框组件*/
        trigger: 'axis', /*坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用*/
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
            confine: true /*true 可以不让提示框出坐标区*/
        },
    },
    xAxis: {
        type: 'category',
        data: [],
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
            name:'目标',
            data: [],
            type: 'line',
            z:2,/*这个在上面*/
            symbol:'none',/*没有标记样式*/
            itemStyle: { /*边线颜色*/
                normal: {
                    color: '#f5f5f5',

                }
            },
            areaStyle:areaColor("rgba(255,255,255,0.5)","rgba(255,255,255,0)"),

        },
        {
            name:'实际',
            type: 'line',
            z:1,
            data: [],
            itemStyle: { /*边线颜色*/
                normal: {
                    color: 'rgba(38,207,255,0.5)'
                }
            },
            label: {
                normal: {
                    show: true,
                    width:fontSize(0.35),  /*label 的宽高*/
                    height:fontSize(0.25),
                    lineHeight:fontSize(0.25),
                    borderWidth:2, /*label边框，默认为0*/
                    borderColor: '#26cfff',
                    backgroundColor: 'rgba(0,0,0,0.8)',
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
        }
    ]
};






/*请求成功后执行*/
var refreshTime;
var successfn=function(res){
    $(".container>.wrapper").css("display","block");
    $(".container>.loading").css("display","none");
    refreshTime=res.data.boardconfig.refreshTime*1000; /*成功后返回的刷新时间*/
    let html=`<p class="empty" style="width=100%;position:absolute;left:50%;top:50%;transform: translate(-50%,-50%);transform-origin: center;font-size: 1.667rem;text-align: center">抱歉，数据为空☹☹</p>`;

    if(res.data.yields.length>0){
        radarMap(res.data.yields,res.data.datarangetime,timeType);
    }else{
         $(".chart:eq(0) .barCharts").html(html);
    }

    if(res.data.pcbaDirectPassRate.xaxis.length>0&&res.data.ctoDirectPassRate.xaxis.length>0&&res.data.yieldTrendAndTop3.length>0){
        /*pcba 直通率*/
        var myChart3 = echarts.init(document.getElementById('pcba'));
        option3.title.text=res.data.pcbaDirectPassRate.title||"没有标题";
        option3.xAxis.data=res.data.pcbaDirectPassRate.xaxis;
        option3.series[0].data=res.data.pcbaDirectPassRate.goalData;
        option3.series[0].areaStyle=areaColor("rgba(255,255,255,0.5)","rgba(255,255,255,0)");
        option3.series[1].areaStyle= areaColor("rgba(38,207,255,0.5)","rgba(38,207,255,0)");
        option3.series[1].label.normal.borderColor="#26cfff";
        option3.series[1].data=res.data.pcbaDirectPassRate.trueData;
        myChart3.setOption(option3);
        /*cto直通率*/
        var myChart4 = echarts.init(document.getElementById('cto'));
        option3.title.text=res.data.ctoDirectPassRate.title||"没有标题";
        option3.xAxis.data=res.data.ctoDirectPassRate.xaxis;
        option3.series[0].data=res.data.ctoDirectPassRate.goalData;
        option3.series[0].areaStyle=areaColor("rgba(255,255,255,0.5)","rgba(255,255,255,0)");
        option3.series[1].areaStyle= areaColor("rgba(27,215,182,0.6)","rgba(27,215,182,0)");
        option3.series[1].label.normal.borderColor="#44f3f0";
        option3.series[1].data=res.data.ctoDirectPassRate.trueData;
        myChart4.setOption(option3);

        /*top3--环形饼图和折线图*/
        pieAndLineChart(res.data.yieldTrendAndTop3);
    }else{
        $(".chart:eq(1) .ecahrtWrap").html(html);
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
    $(".chart:eq(0) .barCharts").html(html);
    $(".chart:eq(1) .ecahrtWrap").html(html);
};
window.onload=function(){
    $(".container>.wrapper").css("display","none");
    let legend=$(".container>.wrapper>.row>.chart>.myLegend>li");
    /*初始化的图例选中样式*/
    for(let item of legend){
        if($(item).text()=="今日" && timeType=="dd"&&!$(item).find(".selectBox").hasClass("active")){
            $(item).find(".selectBox").addClass("active")
        }
        if($(item).text()=="本周" && timeType=="iw"&&!$(item).find(".selectBox").hasClass("active")){
            $(item).find(".selectBox").addClass("active")
        }
        if($(item).text()=="本月" && timeType=="mm"&&!$(item).find(".selectBox").hasClass("active")){
            $(item).find(".selectBox").addClass("active")
        }
    }
    request('QualityReport','timeType='+timeType,'GET',successfn,errorfn);

};


