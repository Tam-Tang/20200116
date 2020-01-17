$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thprolineachrate/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '插入时间', name: 'createTime', index: 'CREATE_TIME', width: 80 }, 			
			{ label: '日期', name: 'dataTime', index: 'DATA_TIME', width: 80 }, 			
			{ label: '线别', name: 'lineName', index: 'LINE_NAME', width: 80 }, 			
			{ label: '班别', name: 'shiftName', index: 'SHIFT_NAME', width: 80 }, 			
			{ label: '0前段  1后段', name: 'step', index: 'STEP', width: 80 }, 			
			{ label: '生产排产量', name: 'schedulQty', index: 'SCHEDUL_QTY', width: 80 }, 			
			{ label: '前段无值，后段生产，报表系统维护', name: 'standardQry', index: 'STANDARD_QRY', width: 80 }, 			
			{ label: '生产产出量', name: 'actualQty', index: 'ACTUAL_QTY', width: 80 }, 			
			{ label: '前段有值，后段需要报表系统自己计算', name: 'achRate', index: 'ACH_RATE', width: 80 }, 			
			{ label: '默认0 不推送，1推送', name: 'isPush', index: 'IS_PUSH', width: 80 }, 			
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
		thProLineAchRate: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thProLineAchRate = {};
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
                var url = vm.thProLineAchRate.id == null ? "sys/thprolineachrate/save" : "sys/thprolineachrate/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thProLineAchRate),
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
                        url: baseURL + "sys/thprolineachrate/delete",
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
			$.get(baseURL + "sys/thprolineachrate/info/"+id, function(r){
                vm.thProLineAchRate = r.thProLineAchRate;
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