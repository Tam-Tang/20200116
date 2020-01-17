$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thautomachineinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '設備名稱', name: 'machineName', index: 'MACHINE_NAME', width: 80 }, 			
			{ label: '設備UPH', name: 'uph', index: 'UPH', width: 80 }, 			
			{ label: '設備位置', name: 'position', index: 'POSITION', width: 80 }, 			
			{ label: '設備類型', name: 'type', index: 'TYPE', width: 80 }, 			
			{ label: '設備型號', name: 'model', index: 'MODEL', width: 80 }, 			
			{ label: '設備品牌', name: 'brand', index: 'BRAND', width: 80 }, 			
			{ label: '設備編號', name: 'machineNo', index: 'MACHINE_NO', width: 80 }, 			
			{ label: '加工精度', name: 'machineAccuracy', index: 'MACHINE_ACCURACY', width: 80 }, 			
			{ label: '運行總時間', name: 'runTotalTime', index: 'RUN_TOTAL_TIME', width: 80 }, 			
			{ label: '運行時長', name: 'runTime', index: 'RUN_TIME', width: 80 }, 			
			{ label: '產出顯示', name: 'outputDisplay', index: 'OUTPUT_DISPLAY', width: 80 }, 			
			{ label: '設備稼動率', name: 'utilizationRate', index: 'UTILIZATION_RATE', width: 80 }, 			
			{ label: '設備及時狀態', name: 'machinTimelyStatus', index: 'MACHIN_TIMELY_STATUS', width: 80 }, 			
			{ label: '設備狀態顯示', name: 'machinStatusDisplay', index: 'MACHIN_STATUS_DISPLAY', width: 80 }, 			
			{ label: '保養週期顯示', name: 'machinIntervalDisplay', index: 'MACHIN_INTERVAL_DISPLAY', width: 80 }			
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		thAutoMachineInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thAutoMachineInfo = {};
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
                var url = vm.thAutoMachineInfo.id == null ? "sys/thautomachineinfo/save" : "sys/thautomachineinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thAutoMachineInfo),
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
                        url: baseURL + "sys/thautomachineinfo/delete",
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
			$.get(baseURL + "sys/thautomachineinfo/info/"+id, function(r){
                vm.thAutoMachineInfo = r.thAutoMachineInfo;
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