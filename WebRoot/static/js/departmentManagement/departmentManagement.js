$(function(){
	
	// 初始化Table
	var oTable = new TableInit();
	oTable.Init();
	
	//=========================添加部门信息====================	
	$("#addDepartmentInfo").click(function(){
		var addDepartmentNameValue = $("#addDepartmentName").val();
		if(addDepartmentNameValue==null || addDepartmentNameValue==''){
			$("#addDepartmentName").tips({
                side:2,
                msg:'部门名称不能为空！',
                time:2
            })
            return;
		}
		$.ajax({
			type: "POST",
			url: 'departmentManagement/addDepartMentManagementInfo.do',
			data:{
				departmentName: $("#addDepartmentName").val()
	    	},
			success: function(data){
				 $('#addDepartmentInfoModal').modal('hide');
				 $('#departmentManageTable').bootstrapTable('refresh', {url: 'departmentManagement/optionQueryDepartmentInfo.do'});
				 if(data=="0"){
					$.alert({
						title: '提示',
						content: '添加成功！',
						buttons: {
							ok: {
								text: "确定"
							}
						}
					});
				}else{
					$.alert({
						title: '提示',
						content: '<span style="font-weight:bold;color:red;">添加失败！</span>',
						buttons: {
							ok: {
								text: "确定"
							}
						}
					});
				}
			}
		});
    });
	
	//====================修改部门信息================
	$("#updateDepartmentInfo").click(function(){
		var updateDepartmentNameValue = $("#updateDepartmentName").val();
		if(updateDepartmentNameValue==null || updateDepartmentNameValue==''){
			$("#updateDepartmentName").tips({
                side:2,
                msg:'单位名称不能为空！',
                time:2
            })
            return;
		}
		$.ajax({
			type: "POST",
			url: 'departmentManagement/updateDepartmentManagementInfo.do',
			data:{
				departmentId: $("#updateDepartmentId").val(),
				departmentName: $("#updateDepartmentName").val()
	    	},
			success: function(data){
				$('#updateDepartmentInfoModal').modal('hide');
				$('#departmentManageTable').bootstrapTable('refresh', {url: 'departmentManagement/optionQueryDepartmentInfo.do'});
				if(data=="0"){
					$.alert({
						title: '提示',
						content: '修改成功！',
						buttons: {
							ok: {
								text: "确定"
							}
						}
					});
				}else{
					$.alert({
						title: '提示',
						content: '<span style="font-weight:bold;color:red;">修改失败！</span>',
						buttons: {
							ok: {
								text: "确定"
							}
						}
					});
				}
			}
		});
	});
	
});

var TableInit = function() {
	var initObject = new Object();
	// 初始化Table
	initObject.Init = function() {
		$('#departmentManageTable').bootstrapTable({
			url : 'departmentManagement/optionQueryDepartmentInfo.do',	// 请求后台的URL（*）
			method : 'GET', 											// 请求方式（*）
			toolbar: '#toolbar',                						// 工具按钮用哪个容器
			striped : true, 											// 是否显示行间隔色
			cache : false, 												// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, 											// 是否显示分页（*）
			sortable : false, 											// 是否启用排序
			sortOrder : "asc", 											// 排序方式
			dataType : "json",
			queryParamsType : "",
			queryParams : function (params) {
        		var temp = {   
    				pageNumber: params.pageNumber,
    				pageSize: params.pageSize
            	};
    		    return temp;
            },
			sidePagination : "server", 					// 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, 							// 初始化加载第一页，默认第一页
			pageSize : 10, 								// 每页的记录行数（*）
			pageList : [10,30,50,80,100], 				// 可供选择的每页的行数（*）
			search : false, 							// 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			showColumns : true, 						// 是否显示所有的列
			showRefresh : false, 						// 是否显示刷新按钮
			minimumCountColumns : 2, 					// 最少允许的列数
			clickToSelect : true, 						// 是否启用点击选中行
			height : 480, 								// 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			showToggle : true, 							// 是否显示详细视图和列表视图的切换按钮
			cardView : false, 							// 是否显示详细视图
			detailView : false, 						// 是否显示父子表
			columns : [ 
				{
					checkbox: true,  
					visible: true                  		// 是否显示复选框  
                },
				{
					field : 'departmentId',
					title : '序号',
					align : 'center',
					visible : false
				}, 
				{
					field : 'departmentName',
					title : '部门名称',
					align : 'center'
				},
				{
					field : 'departmentParentId',
					title : '父部门ID',
					align : 'center',
					visible : false
				},
				{
					field : 'createDepartmentTime',
					title : '创建时间',
					align : 'center'
				}
			]
		});
	};
	return initObject;
};

//======================添加部门信息===============
function addDepartmentInfoButton(){
	//清空已选择的选项
	$("#addDepartmentName").val("");
	$("#addDepartmentInfoModal").modal('show');
}

//======================修改部门信息===============
function updateDepartmentInfoButton(){
	var selectRowDatas = $("#departmentManageTable").bootstrapTable('getSelections');
	if(selectRowDatas.length==0){
		$.alert({
		    title: '提示',
		    content: '请选择需要修改的数据！',
		    buttons: {
                ok: {
                    text: "确定"
                }
		    }
		});
		return false;
	}else if(selectRowDatas.length>1){
		$.alert({
		    title: '提示',
		    content: '每次只能修改一行！',
		    buttons: {
                ok: {
                    text: "确定"
                }
		    }
		});
		return false;
	}
	$("#updateDepartmentId").val(selectRowDatas[0].departmentId);
	$("#updateDepartmentName").val(selectRowDatas[0].departmentName);
	$("#updateDepartmentInfoModal").modal('show');
}

//============================删除多选的单位信息=====================
var check_result;
function deleteDepartmentInfoButton(){
	var departmentIdsArray = [];
	var selectRowDatas = $("#departmentManageTable").bootstrapTable('getSelections');
	if(selectRowDatas.length==0){
		$.alert({
		    title: '提示',
		    content: '<span style="font-size:14px;font-weight:bold;color:red;">请选择需要删除的部门数据!</span>',
		    buttons: {
                ok: {
                    text: "确定",
                    btnClass: 'btn-primary'
                }
		    }
		});
		return false;
	}
	for(var i=0;i<selectRowDatas.length;i++){
		departmentIdsArray.push(selectRowDatas[i].departmentId);
	}
	checkUsersUnderDepartment(departmentIdsArray);
	if(check_result=="1"){
		$.alert({
			title: '提示',
			content: '<span style="font-weight:bold;color:red;">请先删除部门关联的用户！</span>',
			buttons: {
				ok: {
					text: "确定"
				}
			}
		});
		return false;
	}
	$.confirm({
        title: '<span style="font-size:14px;">提示</span>',
        content: '<span style="font-size:14px;font-weight:bold;color:red;">确认删除选中的部门信息吗?</span>',
        buttons: {
            ok: {
                text: "确定",
                btnClass: 'btn-primary',
                keys: ['enter'],
                action: function(){
        			$.ajax({
        				type: 'POST',
        				url: 'departmentManagement/deleteDepartmentManagementInfo.do',
        				dataType:"json",
        				data: {"departmentIds":departmentIdsArray},
        				traditional:true,
        				success: function(data){
        					$('#departmentManageTable').bootstrapTable('refresh', {url: 'departmentManagement/optionQueryDepartmentInfo.do'});
        					if(data=="0"){
        						$.alert({
        							title: '提示',
        							content: '删除成功！',
        							buttons: {
        								ok: {
        									text: "确定"
        								}
        							}
        						});
        					}else{
        						$.alert({
        							title: '提示',
        							content: '<span style="font-weight:bold;color:red;">删除失败！</span>',
        							buttons: {
        								ok: {
        									text: "确定"
        								}
        							}
        						});
        					}
        				}
        			});
                }
            },
            cancel: {
                text: "取消",
                btnClass: 'btn-default',
                keys: ['esc'],
                action:function () {
                	
                }
            }
        }
    });
}

//删除部门之前检查部门下面时候存在用户
function checkUsersUnderDepartment(departmentIdsArray){
	$.ajax({
		type: 'POST',
		url: 'departmentManagement/selectDepartmentInfoBeforeDelete.do',
		dataType:"json",
		data: {"departmentIds":departmentIdsArray},
		async: false,
		traditional:true,
		success: function(data){
			check_result=data;
		}
	});
	return check_result;
}

