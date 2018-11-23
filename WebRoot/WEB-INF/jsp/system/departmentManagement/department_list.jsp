<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title>部门管理</title>
	  	<meta name="renderer" content="webkit">
	  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<style type="text/css">
		input[readonly] {
			background-color: gray;
		}
		</style>
		<!-- CSS -->
		<link type="text/css" rel="stylesheet" href="static/zTree_v3/css/metroStyle/metroStyle.css"/>
		<link type="text/css" rel="stylesheet" href="static/css/bootstrap.min.css"/>
		<link type="text/css" rel="stylesheet" href="static/css/font-awesome.css"/>
		<link type="text/css" rel="stylesheet" href="static/css/bootstrap-table.min.css"/>
		<link type="text/css" rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
  		<link type="text/css" rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
  		<link type="text/css" rel="stylesheet" href="static/css/jquery-confirm.min.css">
		<link type="text/css" rel="stylesheet" href="static/css/sideBarMenu.css"/>
		<!-- JS -->
		<script type="text/javascript" src="static/zTree_v3/js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.all.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap-table.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap-table-zh-CN.min.js"></script>
		<script type="text/javascript" src="static/js/jquery-confirm.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/common/jquery.bootstrap.teninedialog.v3.js"></script>
	</head>
<body>

	<div class="layui-fluid">
	    <div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<table id="departmentManageTable"></table>
			</div>
		</div>
    </div> 
    
	<!-- 添加部门信息模态框 -->
	<div class="modal fade" id="addDepartmentInfoModal" role="dialog" data-backdrop="static">
		<div class="modal-dialog" style="width:350px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						&times;
					</button>
					<span class="modal-title" style="font-weight:bold;">添加部门信息</span>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td>
								<div class="input-group">
									<span class="input-group-addon" style="width:110px;">部门名称</span>
									<input id="addDepartmentName" name="departmentName" class="form-control" style="width:150px;">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="addDepartmentInfo">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改部门信息模态框 -->
	<div class="modal fade" id="updateDepartmentInfoModal" role="dialog" data-backdrop="static">
		<div class="modal-dialog" style="width:350px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						&times;
					</button>
					<span class="modal-title" style="font-weight:bold;">修改部门信息</span>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td>
								<div class="input-group">
									<span class="input-group-addon" style="width:110px;">部门名称</span>
									<input id="updateDepartmentId" name="departmentId" class="form-control" style="display:none;">
									<input id="updateDepartmentName" name="departmentName" class="form-control" style="width:150px;">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="updateDepartmentInfo">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 操作按钮 -->
	<div id="toolbar">  
	    <div class="btn-group">
	        <button class="btn btn-default" id="addButton" onclick="addDepartmentInfoButton()">
	            <i class="glyphicon glyphicon-plus"></i>添加
	        </button>
	        <button class="btn btn-default" id="updateButton" onclick="updateDepartmentInfoButton()">
	            <i class="glyphicon glyphicon-edit"></i>修改
	        </button>  
	        <button class="btn btn-default" id="deleteButton" onclick="deleteDepartmentInfoButton()">
	            <i class="glyphicon glyphicon-trash"></i>删除  
	        </button>
	    </div>
	</div>
	
	<!-- ace scripts -->
	<script type="text/javascript" src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/layuiadmin/layui/layui.js"></script>
	<script type="text/javascript" src="static/js/departmentManagement/departmentManagement.js"></script>
	<script type="text/javascript">
		$(function(){
			//控制按钮的显示和隐藏
			var reveivedButtonList = ${buttonList};
			var currentPageButtonList = eval(reveivedButtonList);
			if(currentPageButtonList!=null && currentPageButtonList.length>0){
				$.each(currentPageButtonList, function(index,object){ 
			        if(object.buttonId==1 && object.status==0){
			        	$("#addButton").css('display','none');
			        }
			        if(object.buttonId==3 && object.status==0){
			        	$("#updateButton").css('display','none');
			        }
			        if(object.buttonId==5 && object.status==0){
			        	$("#deleteButton").css('display','none');
			        }
			    });
			}
		});
		layui.config({
			base : 'static/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use([ 'index', 'console']);
	</script>
	
</body>
</html>
