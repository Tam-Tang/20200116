$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/thshift/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true ,hidden: true},
			{ label: '工厂ID', name: 'factoryId', index: 'FACTORY_ID', width: 80 }, 			
			{ label: '班別', name: 'shiftName', index: 'SHIFT_NAME', width: 80 },
			{ label: '序号', name: 'seqno', index: 'SEQNO', width: 80 }, 			
			{ label: '时间段', name: 'hourperiod', index: 'HOURPERIOD', width: 150 },
			{ label: 'UPH比例', name: 'uphratio', index: 'UPHRATIO', width: 80 },
			{ label: '开始HOUR', name: 'fitsrHour', index: 'FITSR_HOUR', width: 80 }, 			
			{ label: '结束HOUR', name: 'lastHour', index: 'LAST_HOUR', width: 80 }, 			
			{ label: '开始时间', name: 'fromTime', index: 'FROM_TIME', width: 80 }, 			
			{ label: '结束时间', name: 'toTime', index: 'TO_TIME', width: 80 }, 			
			{ label: '备注', name: 'note', index: 'NOTE', width: 80 }			
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
    initStartTime();
});

/**
 * 初始化节假日控件
 */
function initStartTime(){
    laydate.render({
        elem: '#hourperiod' //指定元素
        ,type: 'time'
        ,range: true
        ,done: function(value, date){
            //console.log(value);
            var spitStr = '-';
            var times = value.split(spitStr);
            vm.thShift.fromTime = times[0];
            vm.thShift.toTime = times[1];
            console.log(times);
            vm.$forceUpdate();
            //console.log('你选择的日期是：' + value + '\n获得的对象是' + JSON.stringify(date));
        }
    });
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		thShift: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.thShift = {
                uphratio : '0.000'
            };
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
                var url = vm.thShift.id == null ? "sys/thshift/save" : "sys/thshift/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.thShift),
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
                        url: baseURL + "sys/thshift/delete",
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
			$.get(baseURL + "sys/thshift/info/"+id, function(r){
                vm.thShift = r.thShift;
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