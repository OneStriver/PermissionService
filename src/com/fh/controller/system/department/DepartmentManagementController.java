package com.fh.controller.system.department;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.common.Page;
import com.fh.entity.common.PageData;
import com.fh.entity.consts.ResultCode;
import com.fh.entity.system.ButtonRight;
import com.fh.entity.system.DepartmentInfo;
import com.fh.entity.system.User;
import com.fh.service.system.buttonRight.ButtonRightManager;
import com.fh.service.system.department.DepartmentManagementService;
import com.fh.service.system.fhlog.FHlogManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.Jurisdiction;
import com.fh.util.UuidUtil;

import net.sf.json.JSONArray;

/** 
 * 部门管理
 */
@Controller
@RequestMapping(value="/departmentManagement")
public class DepartmentManagementController extends BaseController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="departmentManagementService")
	private DepartmentManagementService departmentManagementService;
	@Resource(name="fhlogService")
	private FHlogManager fhlogService;
	@Resource(name="buttonRightService")
	private ButtonRightManager buttonRightService;
	
	/**
	 * 转到部门管理页面
	 * 顶级节点的parentId是0
	 */
	@RequestMapping(value="/toListDepartmentInfo")
	public ModelAndView toListDepartmentInfo(Model model)throws Exception{
		//根据当前角色ID和菜单ID获取某个页面的按钮权限
		PageData pageData = new PageData();
		pageData = this.getPageData();
		User loginUserRole = Jurisdiction.getLoginUserRole();
		String loginRoleId = loginUserRole.getRole().getROLE_ID();
		pageData.put("roleId", loginRoleId);
		List<ButtonRight> roleIdAndMenuIdButtonList = buttonRightService.getButtonRightByRoleIdAndMenuId(pageData);
		String roleIdAndMenuIdButtonListStr = JSONArray.fromObject(roleIdAndMenuIdButtonList).toString();
		ModelAndView mv = this.getModelAndView();
		//页面中的按钮权限
		model.addAttribute("buttonList", roleIdAndMenuIdButtonListStr);
		mv.setViewName("system/departmentManagement/department_list");
		return mv;
	}
	
	/**
	 * 条件查询部门信息
	 */
	@RequestMapping(value="/optionQueryDepartmentInfo")
	@ResponseBody
	public Map<String, Object> optionQueryDepartmentInfo(int pageNumber,int pageSize,Page pageObject) throws Exception {
		String currentUserName = Jurisdiction.getUsername();
		logBefore(logger, currentUserName+"查询当前所有的部门");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd.put("departmentParentId", 1);
		//条件查询用户的信息
		PageData countSubDepartmentInfoPageData = departmentManagementService.countSubDepartmentManagementInfo(pd);
		long subDepartmentInfoCount = ((Long)countSubDepartmentInfoPageData.get("subDepartmentInfoCount")).longValue();
		pageObject.setPageData(pd);
		pageObject.setCurrentStartIndex((pageNumber - 1) * pageSize);
		pageObject.setPageSize(Math.min(pageSize, (int)subDepartmentInfoCount));
		List<DepartmentInfo> departmentManagementInfoList = departmentManagementService.listPageDepartmentManagementInfo(pageObject);
		//循环处理数据
		map.put("total", subDepartmentInfoCount);  
        map.put("rows", departmentManagementInfoList);
		return map;
	}
	
	/**
	 * 添加部门信息
	 */
	@RequestMapping(value="/addDepartMentManagementInfo")
	@ResponseBody
	public String addDepartMentManagementInfo(String departmentParentId,String departmentName)throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"添加部门信息");
		try {
			//判断条件是否为空
			if(departmentParentId==null){
				departmentParentId = "1";
			}
			//获取当前的子单位
			PageData pd = new PageData();
			pd.put("departmentParentId",departmentParentId);
			//
			DepartmentInfo departmentInfo = new DepartmentInfo();
			departmentInfo.setDepartmentId(UuidUtil.get32UUID());
			departmentInfo.setDepartmentName(departmentName);
			departmentInfo.setDepartmentParentId(departmentParentId);
			departmentInfo.setCreateDepartmentTime(sdf.format(new Date()));
			departmentManagementService.insertDepartmentManagementInfo(departmentInfo);
			return ResultCode.SUCCESS.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILURE.getValue();
		}
	}
	
	/**
	 * 修改部门信息
	 */
	@RequestMapping(value="/updateDepartmentManagementInfo")
	@ResponseBody
	public String updateDepartmentManagementInfo(String departmentId,String departmentName)throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改部门信息");
		try {
			DepartmentInfo departmentInfo = new DepartmentInfo();
			departmentInfo.setDepartmentId(departmentId);
			departmentInfo.setDepartmentName(departmentName);
			departmentManagementService.updateDepartmentManagementInfo(departmentInfo);
			return ResultCode.SUCCESS.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILURE.getValue();
		}
	}
	
	/**
	 * 删除部门之前查询该部门下面是否有用户
	 */
	@RequestMapping(value="/selectDepartmentInfoBeforeDelete")
	@ResponseBody
	public String selectDepartmentInfoBeforeDelete(@RequestParam(value="departmentIds") String[] departmentIds)throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除部门之前查询该部门下面是否有用户");
		try {
			List<String> convertList = Arrays.asList(departmentIds);
			PageData pageData = new PageData();
			pageData.put("departmentIds", convertList);
			List<User> departmentIdUserList = userService.findUserByDepartmentId(pageData);
			if(departmentIdUserList!=null && departmentIdUserList.size()>0){
				return ResultCode.FAILURE.getValue();
			}else{
				return ResultCode.SUCCESS.getValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILURE.getValue();
		}
	}
	
	/**
	 * 删除部门信息
	 */
	@RequestMapping(value="/deleteDepartmentManagementInfo")
	@ResponseBody
	public String deleteDepartmentManagementInfo(@RequestParam(value="departmentIds") String[] departmentIds)throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除部门信息");
		try {
			List<String> convertList = Arrays.asList(departmentIds);
			PageData pageData = new PageData();
			pageData.put("departmentIds", convertList);
			departmentManagementService.deleteDepartmentManagementInfo(pageData);
			return ResultCode.SUCCESS.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILURE.getValue();
		}
	}
	
}
