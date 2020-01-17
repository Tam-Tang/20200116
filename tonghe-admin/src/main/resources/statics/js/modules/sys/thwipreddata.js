$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thwipreddata/list',
        datatype: "json",
        colModel: [			
			// { label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '站點ID', name: 'stationId', index: 'STATION_ID', width: 80 }, 			
			{ label: 'WIP警戒值', name: 'wipNum', index: 'WIP_NUM', width: 80 }			
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

    //初始化站点下拉数据
    loadStationData();

});


/**
 * 加载站点数据
 */
function loadStationData(){
    //清空
    $("#slt_stationId").empty();
    //异步加载
    $.ajax({
        type: "get",
        url: baseURL + 'sys/thstation/list?page=1&limit=99999',
        success: function (msg) {
            var objMsg = eval(msg);
            if (objMsg.code == 0) {
                if (objMsg.page.totalCount > 0) {
                    for (var item in objMsg.page.list) {
                        $("#slt_stationId").append("<option value='"
                            + objMsg.page.list[item].stationId + "'>"
                            + objMsg.page.list[item].stationName + "</option>");
                    }
                }
            } else {
                alert(objMsg.errMsg);
            }
        },
        error: function (msg) {
            alert("获取字典数据[站点数据]异常，请联系管理员！");
        }
    });
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		thWipRedData: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thWipRedData = {};
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
                var url = vm.thWipRedData.id == null ? "sys/thwipreddata/save" : "sys/thwipreddata/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thWipRedData),
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
                        url: baseURL + "sys/thwipreddata/delete",
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
			$.get(baseURL + "sys/thwipreddata/info/"+id, function(r){
                vm.thWipRedData = r.thWipRedData;
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