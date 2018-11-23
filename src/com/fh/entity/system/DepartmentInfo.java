package com.fh.entity.system;

import java.util.List;

public class DepartmentInfo {

	private String departmentId;
	private String departmentName;
	private String departmentParentId;
	private String createDepartmentTime;
	private DepartmentInfo parentDepartmentInfo;
	private List<DepartmentInfo> subDepartmentInfo;
	private boolean hasDepartmentInfo = false;
	private boolean parentNode = false;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentParentId() {
		return departmentParentId;
	}

	public void setDepartmentParentId(String departmentParentId) {
		this.departmentParentId = departmentParentId;
	}

	public String getCreateDepartmentTime() {
		return createDepartmentTime;
	}

	public void setCreateDepartmentTime(String createDepartmentTime) {
		this.createDepartmentTime = createDepartmentTime;
	}

	public DepartmentInfo getParentDepartmentInfo() {
		return parentDepartmentInfo;
	}

	public void setParentDepartmentInfo(DepartmentInfo parentDepartmentInfo) {
		this.parentDepartmentInfo = parentDepartmentInfo;
	}

	public List<DepartmentInfo> getSubDepartmentInfo() {
		return subDepartmentInfo;
	}

	public void setSubDepartmentInfo(List<DepartmentInfo> subDepartmentInfo) {
		this.subDepartmentInfo = subDepartmentInfo;
	}

	public boolean isHasDepartmentInfo() {
		return hasDepartmentInfo;
	}

	public void setHasDepartmentInfo(boolean hasDepartmentInfo) {
		this.hasDepartmentInfo = hasDepartmentInfo;
	}

	public boolean isParentNode() {
		return parentNode;
	}

	public void setParentNode(boolean parentNode) {
		this.parentNode = parentNode;
	}

}
