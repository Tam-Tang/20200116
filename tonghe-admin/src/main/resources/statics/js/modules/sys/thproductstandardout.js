$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thproductstandardout/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true  ,hidden: true},
			{ label: '产线', name: 'lineId', index: 'LINE_ID', width: 80 },
			{ label: '标准产出数量', name: 'num', index: 'NUM', width: 80 },
			{ label: '开始时间', name: 'startTime', index: 'START_TIME', width: 80 },
			{ label: '结束时间', name: 'endTime', index: 'END_TIME', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });

    //初始化时间控件
    initTime();

    //初始化产线下拉数据
    loadProductLineData();
});


/**
 * 初始化控件
 */
function initTime(){
    laydate.render({
        elem: '#startTime' //指定元素
        ,done: function(value, date){
            vm.thProductStandardOut.startTime = value;
            //console.log('你选择的日期是：' + value + '\n获得的对象是' + JSON.stringify(date));
        }
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#endTime' //指定元素
        ,done: function(value, date){
            //必须先选择开始时间
            if(vm.thProductStandardOut.startTime.length ==0 ){
                alert('请先选择开始时间!');
            }
            //判断相同天
            // if(moment(value).format('L') != moment(vm.thProductStandardOut.startTime).format('L')){
            //     alert('请先选择与开始时间同一天!');
            // }
            //结束必须大于开始
            if(moment(value).isBefore(vm.thProductStandardOut.startTime)){
                alert('结束时间必须大于开始时间!');
            }

            vm.thProductStandardOut.endTime = value ;

            //console.log('你选择的日期是：' + value + '\n获得的对象是' + JSON.stringify(date));
        }
        ,type: 'datetime'
    });
}

/**
 * 加载产线数据
 */
function loadProductLineData(){
    //清空
    $("#slt_productLineId").empty();
    //异步加载
    $.ajax({
        type: "get",
        url: baseURL + 'sys/thproductline/list?page=1&limit=99999',
        success: function (msg) {
            var objMsg = eval(msg);
            if (objMsg.code == 0) {
                if (objMsg.page.totalCount > 0) {
                    for (var item in objMsg.page.list) {
                        $("#slt_productLineId").append("<option value='"
                            + objMsg.page.list[item].lineId + "'>"
                            + objMsg.page.list[item].lineName + "</option>");
                    }
                }
            } else {
                alert(objMsg.errMsg);
            }
        },
        error: function (msg) {
            alert("获取字典数据[产线数据]异常，请联系管理员！");
        }
    });
}


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		thProductStandardOut: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thProductStandardOut = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.thProductStandardOut.id == null ? "sys/thproductstandardout/save" : "sys/thproductstandardout/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thProductStandardOut),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sys/thproductstandardout/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "sys/thproductstandardout/info/"+id, function(r){
                vm.thProductStandardOut = r.thProductStandardOut;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});