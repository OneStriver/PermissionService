package com.fh.service.system.department.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.common.Page;
import com.fh.entity.common.PageData;
import com.fh.entity.system.DepartmentInfo;
import com.fh.service.system.department.DepartmentManagementService;

/** 
 * 说明： 部门信息管理
 */
@Service("departmentManagementService")
public class DepartmentMangementServiceImpl implements DepartmentManagementService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public void insertDepartmentManagementInfo(DepartmentInfo departmentInfo) throws Exception {
		dao.save("DepartmentManagementMapper.insertDepartmentManagementInfo", departmentInfo);
	}

	@Override
	public void updateDepartmentManagementInfo(DepartmentInfo departmentInfo) throws Exception {
		dao.update("DepartmentManagementMapper.updateDepartmentManagementInfo", departmentInfo);
	}

	@Override
	public void deleteDepartmentManagementInfo(PageData pd) throws Exception {
		dao.delete("DepartmentManagementMapper.deleteDepartmentManagementInfo", pd);
	}
	
	@Override
	public PageData countSubDepartmentManagementInfo(PageData pd) throws Exception {
		return (PageData)dao.findForObject("DepartmentManagementMapper.countSubDepartmentManagementInfo", pd);
	}

	/**
	 * 分页获取单位信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentInfo> listPageDepartmentManagementInfo(Page page) throws Exception {
		return (List<DepartmentInfo>)dao.findForList("DepartmentManagementMapper.listPageDepartmentManagementInfo", page);
	}

	/**
	 * 左侧树显示出所有的单位管理的层级和子层级
	 */
	@Override
	public List<DepartmentInfo> listDepartmentInfoAndSubDepartmentInfo(String parentDepartmentId) throws Exception {
		List<DepartmentInfo> subDepartmentInfoByParentIdList = this.listSubDepartmentInfoByParentId(parentDepartmentId);
		for(DepartmentInfo departmentInfo : subDepartmentInfoByParentIdList){
			departmentInfo.setSubDepartmentInfo(this.listDepartmentInfoAndSubDepartmentInfo(departmentInfo.getDepartmentId()));
			if(departmentInfo.getSubDepartmentInfo()!=null && departmentInfo.getSubDepartmentInfo().size()!=0){
				departmentInfo.setParentNode(true);
			}else{
				departmentInfo.setParentNode(false);
			}
		}
		return subDepartmentInfoByParentIdList;
	}
	
	/**
	 * 查询直属当前节点的下级部门
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentInfo> listSubDepartmentInfoByParentId(String parentDepartmentId) throws Exception {
		return (List<DepartmentInfo>)dao.findForList("DepartmentManagementMapper.listSubDepartmentInfoByParentId", parentDepartmentId);
	}

	/**
	 * 循环查询出该节点下面的所有子节点
	 *//*
	@Override
	public List<UnitManagementInfo> listCurrentUnitInfoAndSubUnitInfo(Integer unitParentId,List<UnitManagementInfo> list) throws Exception {
		List<UnitManagementInfo> subUnitManagementInfoByParentIdList = this.listSubUnitManagementInfoByParentId(unitParentId);
		for(UnitManagementInfo unitManagementInfo : subUnitManagementInfoByParentIdList){
			list.add(unitManagementInfo);
			this.listCurrentUnitInfoAndSubUnitInfo(unitManagementInfo.getUnitId(),list);
		}
		return list;
	}*/

}

