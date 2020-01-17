

let CurentTime=function() {
    var now = new Date();
    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');

    var year = now.getFullYear();     //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();        //日

    var week=now.getDay();                  //周

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒

    var clock = year + "/";

    if(month < 10)
        clock += "0";

    clock += month + "/";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    clock+=show_day[week]+" ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    setTimeout(showTime(clock),1000);
};

var showTime=function(clock){
    let key=window.location.pathname.lastIndexOf("/");
    let htmlName=window.location.pathname.slice(key+1);
    let htmlArr=[
        {name:'OrderReport.html',title:'订单管理看板'},
        {
            name:'AchieveRateReport.html',title:'产出管理看板'
        },
        {
            name:'AutoEquipmentReport.html',title:'自动化管理看板'
        },
        {
            name:'HumitureReport.html',title:'温湿度、ESD实时监控看板'
        },
        {
            name:'MaintainReport.html',title:'维修管理看板'
        },
        {
            name:'ProcessStateReport.html',title:'工序状态看板'
        },
        {
            name:'QualityReport.html',title:'质量管理看板'
        },
        {
            name:'ThrowRateReport.html',title:'物料管理看板'
        },
        {
            name:'VideoReport.html',title:'车间监控看板'
        },
        {
            name:'AutoEquipmentReport.html',title:'自动化管理看板'
        }
    ];
    let html=`<header class="row flexRowBetweenCenter">
    <div class="company flexColumnCenterStart">
        <p class="companyName"></p>
        <!--<span class="EnglishCompanyName">Order Management Report</span>-->
    </div>
    <div class="title">
        <p>统合电子(杭州)有限公司</p>
    </div>
    <div class="showTime flexColumnCenterStart">
        <div class="top">
            <img class="icon" src="${mode==='release'?urls()+'statics/webStatics/images/headerTime.png':'../../statics/webStatics/images/headerTime.png'}" alt=""><span class="time">00:00:00</span>
        </div>
        <div class="bottom flexRowBetweenCenter">
            <div class="date ">
                XXXX/XX/XX
            </div>
            <div class="week">星期X </div>
        </div>
    </div>
    </header>`;
    $(html).replaceAll("header");
    for(var item of htmlArr){
        if(item.name==htmlName){
            $("header").find(".companyName").text(item.title);
        }
    };
    let date= clock.split(/\s+/)[0];
    let week= clock.split(/\s+/)[1];
    let time= clock.split(/\s+/)[2];
    $("header").find(".time").text(time);
    $("header").find(".date").text(date);
    $("header").find(".week").text(week);
};

$(function(){
    setInterval(CurentTime,1000) ;

    // let key=window.location.pathname.lastIndexOf("/");
    // console.log(window.location.pathname.slice(key+1));
    // let htmlName=window.location.pathname.slice(key+1);
    // let htmlArr=[
    //     {name:'OrderReport.html',title:'订单管理看板'},
    //     {
    //       name:'AchieveRateReport.html',title:'产出达成率看板'
    //     },
    //     {
    //         name:'AutoEquipmentReport.html',title:'自动化设备看板'
    //     },
    //     {
    //         name:'HumitureReport.html',title:'温湿度、ESD监控看板'
    //     },
    //     {
    //         name:'MaintainReport.html',title:'维修看板'
    //     },
    //     {
    //         name:'ProcessStateReport.html',title:'工序状态看板'
    //     },
    //     {
    //         name:'QualityReport.html',title:'质量监控看板'
    //     },
    //     {
    //         name:'ThrowRateReport.html',title:'产线抛料率'
    //     },
    //     {
    //         name:'VideoReport.html',title:'车间监控看板'
    //     }
    // ];
    // let html=`<header class="row flexRowBetweenCenter">
    // <div class="company flexColumnCenterStart">
    //     <p class="companyName"></p>
    //     <!--<span class="EnglishCompanyName">Order Management Report</span>-->
    // </div>
    // <div class="title">
    //     <p>统合电子(杭州)有限公司</p>
    // </div>
    // <div class="showTime flexColumnCenterStart">
    //     <div class="top">
    //         <img class="icon" src="../../statics/webStatics/images/headerTime.png" alt=""><span class="time">00:00:00</span>
    //     </div>
    //     <div class="bottom flexRowBetweenCenter">
    //         <div class="date ">
    //             XXXX/XX/XX
    //         </div>
    //         <div class="week">星期X </div>
    //     </div>
    // </div>
    // </header>`;
    // // $("header").load(html);
    //
    // $(html).replaceAll("header");
    // for(var item of htmlArr){
    //     if(item.name==htmlName){
    //         $("header").find(".companyName").text(item.title);
    //     }
    // };
    // let date= CurentTime().split(/\s+/)[0];
    // let week= CurentTime().split(/\s+/)[1];
    // let time= CurentTime().split(/\s+/)[2];
    // $("header").find(".time").text(time);
    // $("header").find(".date").text(date);
    // $("header").find(".week").text(week);

    // $.ajax({
    //     url:"header.html",
    //     type:"get",
    //     success:function(result){
    //         //console.log(result)
    //         $("header").html(result);
    //         $(result).replaceAll("header");
    //         // $(`<link rel="stylesheet" href="css/header.css">`).appendTo("head");
    //         for(var item of htmlArr){
    //                 if(item.name==htmlName){
    //                     $("header").find(".companyName").text(item.title);
    //                 }
    //         };
    //         let date= CurentTime().split(/\s+/)[0];
    //         let week= CurentTime().split(/\s+/)[1];
    //         let time= CurentTime().split(/\s+/)[2];
    //         $("header").find(".time").text(time);
    //         $("header").find(".date").text(date);
    //         $("header").find(".week").text(week);
    //     }
    // });

});
