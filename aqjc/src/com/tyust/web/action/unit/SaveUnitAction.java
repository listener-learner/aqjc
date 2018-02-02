/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.tyust.web.action.unit;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tyust.bean.unit.TBaseUnitInfo;
import com.tyust.common.OpertionType;
import com.tyust.common.SaveLog;
import com.tyust.service.unit.UnitService;


public class SaveUnitAction {

	private UnitService unitService;
	//ID
	private String unitId;
	 //部门级别
	private String unitClass;
	 //部门名称
	private String unitName;
	 //地址
	private String address;

	private  String telephone;
	 //联系人
	private  String contactPerson;
	 //EMAIL
	private  String email;
	 //负责人
	private  String header;

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}


	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitClass() {
		return unitClass;
	}

	public void setUnitClass(String unitClass) {
		this.unitClass = unitClass;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * Method execute
	 * @return ActionForward 添加部门
	 */
	public String execute() {
		HttpServletRequest request=ServletActionContext.getRequest();
		TBaseUnitInfo unitInfo = new TBaseUnitInfo();
		try {
			Random rd = new Random();
			unitInfo.setUnitId(String.valueOf(rd.nextLong()));
			unitInfo.setAddress(address);
			unitInfo.setContactPerson(contactPerson);
			unitInfo.setEmail(email);
			unitInfo.setHeader(header);
			unitInfo.setTelephone(telephone);
			unitInfo.setUnitName(unitName);
			unitInfo.setUpUnitId(unitId);
			unitInfo.setCreateDate(new Date());
			unitInfo.setUnitClass(unitClass);
			
			unitService.savaUnit(unitInfo);
			SaveLog.saveLog(request, "添加部门", OpertionType.State.ADD, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			SaveLog.saveLog(request, "添加部门", OpertionType.State.ADD, "添加失败");
		}
		return "success";
	}
}