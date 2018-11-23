package com.fh.entity.system;

import java.io.Serializable;

/**
 * 类名称：角色 类描述：
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ROLE_ID;
	private String ROLE_NAME;
	private String RIGHTS;
	private String PARENT_ID;

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}

	public String getRIGHTS() {
		return RIGHTS;
	}

	public void setRIGHTS(String rIGHTS) {
		RIGHTS = rIGHTS;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

}
