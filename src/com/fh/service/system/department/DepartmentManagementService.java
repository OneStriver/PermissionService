package com.fh.service.system.department;

import java.util.List;

import com.fh.entity.common.Page;
import com.fh.entity.common.PageData;
import com.fh.entity.system.DepartmentInfo;

/** 
 * 说明： 人员所属部门接口类
 */
public interface DepartmentManagementService{
	
	/**
	 * 新增
	 */
	public void insertDepartmentManagementInfo(DepartmentInfo departmentInfo)throws Exception;
	
	/**
	 * 修改
	 */
	public void updateDepartmentManagementInfo(DepartmentInfo departmentInfo)throws Exception;
	
	/**
	 * 删除
	 */
	public void deleteDepartmentManagementInfo(PageData pd)throws Exception;
	
	/**
	 * 条件统计当前单位信息
	 */
	public PageData countSubDepartmentManagementInfo(PageData pd)throws Exception;
	
	/**
	 * 条件查询单位信息
	 */
	public List<DepartmentInfo> listPageDepartmentManagementInfo(Page page)throws Exception;
	
	/**
	 * 树形结构展示使用
	 */
	public List<DepartmentInfo> listDepartmentInfoAndSubDepartmentInfo(String parentDepartmentId)throws Exception;
	
	/**
	 * 根据父节点ID查询节点子节点
	 */
	public List<DepartmentInfo> listSubDepartmentInfoByParentId(String parentDepartmentId)throws Exception;
	
}

