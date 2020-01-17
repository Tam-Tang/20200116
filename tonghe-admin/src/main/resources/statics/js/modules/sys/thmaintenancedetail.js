$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thmaintenancedetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '班别', name: 'shiftType', index: 'SHIFT_TYPE', width: 80 }, 			
			{ label: '订单号', name: 'orderNumber', index: 'ORDER_NUMBER', width: 80 }, 			
			{ label: '工单号', name: 'workNumber', index: 'WORK_NUMBER', width: 80 }, 			
			{ label: '产品SN', name: 'unitSn', index: 'UNIT_SN', width: 80 }, 			
			{ label: '工号', name: 'employeeSn', index: 'EMPLOYEE_SN', width: 80 }, 			
			{ label: '姓名', name: 'employeeName', index: 'EMPLOYEE_NAME', width: 80 }, 			
			{ label: '维修完成时间', name: 'finishTime', index: 'FINISH_TIME', width: 80 }, 			
			{ label: '维修位号', name: 'stepSn', index: 'STEP_SN', width: 80 }, 			
			{ label: '不良描述', name: 'description', index: 'DESCRIPTION', width: 80 }, 			
			{ label: '不良工段', name: 'defectStep', index: 'DEFECT_STEP', width: 80 }, 			
			{ label: '维修类型', name: 'maintainType', index: 'MAINTAIN_TYPE', width: 80 }, 			
			{ label: '是否推送', name: 'isPush', index: 'IS_PUSH', width: 80 }, 			
			{ label: '推送时间', name: 'pushTime', index: 'PUSH_TIME', width: 80 }, 			
			{ label: '维修工段', name: 'maintainStep', index: 'MAINTAIN_STEP', width: 80 }, 			
			{ label: '失败开始时间', name: 'failTime', index: 'FAIL_TIME', width: 80 }, 			
			{ label: '维修开始时间', name: 'startTiem', index: 'START_TIEM', width: 80 }			
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
		thMaintenanceDetail: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thMaintenanceDetail = {};
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
                var url = vm.thMaintenanceDetail.id == null ? "sys/thmaintenancedetail/save" : "sys/thmaintenancedetail/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thMaintenanceDetail),
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
                        url: baseURL + "sys/thmaintenancedetail/delete",
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
			$.get(baseURL + "sys/thmaintenancedetail/info/"+id, function(r){
                vm.thMaintenanceDetail = r.thMaintenanceDetail;
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