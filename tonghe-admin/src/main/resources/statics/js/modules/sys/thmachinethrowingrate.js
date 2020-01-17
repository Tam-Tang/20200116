$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thmachinethrowingrate/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '插入时间', name: 'createTime', index: 'CREATE_TIME', width: 80 }, 			
			{ label: '日期', name: 'dataTime', index: 'DATA_TIME', width: 80 }, 			
			{ label: '线别', name: 'lineName', index: 'LINE_NAME', width: 80 }, 			
			{ label: '机台编号', name: 'machineNo', index: 'MACHINE_NO', width: 80 }, 			
			{ label: '班别', name: 'shiftName', index: 'SHIFT_NAME', width: 80 }, 			
			{ label: '物料编号', name: 'partNumber', index: 'PART_NUMBER', width: 80 }, 			
			{ label: '置件数量', name: 'setQty', index: 'SET_QTY', width: 80 }, 			
			{ label: '抛料数量', name: 'throwQty', index: 'THROW_QTY', width: 80 }, 			
			{ label: '抛料率', name: 'throwRate', index: 'THROW_RATE', width: 80 }, 			
			{ label: '是否推送', name: 'isPush', index: 'IS_PUSH', width: 80 }, 			
			{ label: '推送时间', name: 'pushTime', index: 'PUSH_TIME', width: 80 }			
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
		thMachineThrowingRate: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thMachineThrowingRate = {};
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
                var url = vm.thMachineThrowingRate.id == null ? "sys/thmachinethrowingrate/save" : "sys/thmachinethrowingrate/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thMachineThrowingRate),
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
                        url: baseURL + "sys/thmachinethrowingrate/delete",
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
			$.get(baseURL + "sys/thmachinethrowingrate/info/"+id, function(r){
                vm.thMachineThrowingRate = r.thMachineThrowingRate;
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