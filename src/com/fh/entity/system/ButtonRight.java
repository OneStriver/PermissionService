package com.fh.entity.system;

public class ButtonRight {

	private String id;
	private String roleId;
	private String roleName;
	private Integer menuId;
	private String menuName;
	private Integer buttonId;
	private String buttonName;
	private Integer status;

	public ButtonRight() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getButtonId() {
		return buttonId;
	}

	public void setButtonId(Integer buttonId) {
		this.buttonId = buttonId;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ButtonRight [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", menuId=" + menuId
				+ ", menuName=" + menuName + ", buttonId=" + buttonId + ", buttonName=" + buttonName + ", status="
				+ status + "]";
	}

}
