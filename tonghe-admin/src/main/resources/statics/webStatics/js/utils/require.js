/*
 * Date:2018-11-27
 * Author:Wang li
 * public function
 * */

//api接口统一路径
var urls = function(){
    var curWwwPath = window.document.location.href,
        pathName=window.document.location.pathname,
        pos = curWwwPath.indexOf(pathName),
        localhostPaht = curWwwPath.substring(0, pos),
        projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName)+'/';
};
var localhostPaht = function(){
    var curWwwPath = window.document.location.href,
        pathName=window.document.location.pathname,
        pos = curWwwPath.indexOf(pathName),
        localhostPaht = curWwwPath.substring(0, pos);
    return localhostPaht;
};

//切换正式和测试
var mode = 'release' ;//正式  release /  测试 test

var rootUrl=urls();
if(mode!='release'){
    // rootUrl="http://119.119.118.162:8888/tonghe/";
    // rootUrl="http://119.119.118.162:8080/tonghe/";/*jams 电脑*/
    rootUrl="http://localhost:63342/tonghe-front/statics/webStatics/js/";/*webstorm 使用*/
    // rootUrl="http://127.0.0.1:5500/statics/webStatics/js/";/*vsCode 使用*/
}


//传参为：接口，数据(可以null)，‘get,post,delete,put’
var setRequestUrl=[   /*设置api地址*/
    {
        name:"AchieveRateReport",
        localhostJsonUrl:"AchieveRateReport.json",
        devJsonUrl:"statics/webStatics/js/AchieveRateReport.json",
        buildUrl:"board/producerate"
    },{
        name:"AutoEquipmentReport",
        localhostJsonUrl:"auto.json",
        devJsonUrl:"statics/webStatics/js/auto.json",
        buildUrl:"board/auto"
    },{
        name:"HumitureReport",
        localhostJsonUrl:"HumitureReport.json",
        devJsonUrl:"statics/webStatics/js/HumitureReport.json",
        buildUrl:"board/temperatureesd"
    },{
        name:"MaintainReport",
        localhostJsonUrl:"MaintainReport.json",
        devJsonUrl:"statics/webStatics/js/MaintainReport.json",
        buildUrl:"board/maintenance"
    },{
        name:"OrderReport",
        localhostJsonUrl:"OrderReport.json",
        devJsonUrl:"statics/webStatics/js/OrderReport.json",
        buildUrl:"board/order"
    },{
        name:"ProcessStateReport",
        localhostJsonUrl:"ProcessStateReport.json",
        devJsonUrl:"statics/webStatics/js/ProcessStateReport.json",
        buildUrl:"board/euipment"
    },{
        name:"QualityReport",
        localhostJsonUrl:"quality.json",
        devJsonUrl:"statics/webStatics/js/quality.json",
        buildUrl:"board/quality"
    },{
        name:"ThrowRateReport",
        localhostJsonUrl:"ThrowRateReport.json",
        devJsonUrl:"statics/webStatics/js/ThrowRateReport.json",
        buildUrl:"board/mateeral"
    },{
        name:"VideoReport"
    }
];

var request=function(name,data,types, successfn, errorfn) {
    var url="";
    for(let item of setRequestUrl){
        if(item.name==name){
            url=rootUrl+item.buildUrl
        }
    }
    data = (data==null || data=="" || typeof(data)=="undefined")? "" : data;
    // url=rootUrl+url;
    $.ajax({
        type: types,
        data: data,
        url: url,
        cache:false,
        dataType: "json",
        contentType : 'application/json;charset=UTF-8',
        success: function(d){
            successfn(d);
        },
        error: function(e){
            if(errorfn){
                errorfn(e);
            }
            console.log(e);
        }
    });
};

var requestT=function(url, data,types, successfn, errorfn) {
    data = (data==null || data=="" || typeof(data)=="undefined")? "" : JSON.stringify(data);
    url=rootUrl+url;
    $.ajax({
        type: types,
        data: data,
        url: url,
        cache:false,
        dataType: "text",
        contentType : 'application/json;charset=UTF-8',
        success: function(d){
            successfn(d);

        },
        error: function(e){
            if(errorfn){
                errorfn(e);
            }
            console.log(e);
        }
    });
};

var getI18n=function(callback){
    request("/rest/i18n/all",null,"GET",function(data){
        var t_i18n="",
            attr_t="";
        $.each($(".lang_text"),function(i,v){
            t_i18n=$(this).attr("data-i18n");
            $(this).text(data[t_i18n]);
        })
        $.each($(".lang_attr"),function(m,n){
            t_i18n=$(this).attr("data-i18n");
            attr_t=$(this).attr("data-t");
            $(this).attr(attr_t,data[t_i18n]);
        });
        if(callback){
            callback();
        }
    });
};

var  getUrlname=function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
        return unescape(r[2]);
    }else{
        return null;
    };
};

$(function(){
    getFontSize();


    let key=window.location.pathname.lastIndexOf("/");
    let htmlName=window.location.pathname.slice(key+1);

    let htmlArr=[
        {name:'OrderReport.html',title:'订单管理看板'},
        {
            name:'AchieveRateReport.html',title:'产出管理看板'
        },
        {
            name:'HumitureReport.html',title:'温湿度、ESD监控看板'
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
    var n=0;
    var time=4*60*1000;
    console.log(time);
    // setInterval(function(){
    //     console.log(htmlName);
    //     debugger;
    //     n>htmlArr.length && (n=0);
    //     var t=htmlArr[n];
    //     console.log(t);
    //
    //     if(t.name==htmlName){
    //         setTimeout(function(){
    //             location.reload();
    //             window.document.location.href=htmlArr[n+1].name;
    //             console.log(htmlArr[n+1].name);
    //         },300);
    //     }
    //     n++;
    //     if(n==9){
    //         window.document.location.href=htmlArr[0].name
    //     }
    // },7201);

});


/*设置整体字体大小*/
var getFontSize=function(){
    //document.documentElement.clientWidth,获取浏览器窗口文档显示区域的宽度，不包括滚动条
    //document.documentElement.offsetWidth 获取DOM文档的根节点html元素对象的宽度，即offsetWidth=width+padding+border，不包括margin
    let clientWidth=document.documentElement.clientWidth;
    if(clientWidth>1921){
        document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
    }else if(clientWidth>1200){
        document.documentElement.style.fontSize = 1920 /160+ 'px';  /*12px*/
    }else if(clientWidth>768){
        document.documentElement.style.fontSize = 720 /72+ 'px';  /*10px*/
    }else{
        document.documentElement.style.fontSize = 720 /180+ 'px';  /*5px*/
        // document.documentElement.style.transform= scale(0.5);
        // document.documentElement.style.webkitTransformOriginX= left;
    }
    $("body").css("visibility","visible");
};
    /*echart 中的字体*/
 fontSize=function(res){
    var docEl = document.documentElement,
        clientWidth = window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth;
    if (!clientWidth) return;
    var fontSizeValue;
    if(clientWidth>1921){
          fontSizeValue = 100 * (clientWidth / 1920);
        return res*fontSizeValue;
    }else if(clientWidth>786){
         fontSizeValue = 100 * 0.833;
        return res*fontSizeValue;
    }else{
        fontSizeValue = 100 * 0.3333333;
        return res*fontSizeValue;
    }
}