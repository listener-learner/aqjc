package com.tyust.web.action.en;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

import com.tyust.bean.en.EnApplyInfo;
import com.tyust.bean.en.EnPic;
import com.tyust.bean.en.EnPicExample;
import com.tyust.bean.en.EnTestInstrument;
import com.tyust.bean.en.EnTestReport;
import com.tyust.bean.en.EnTestReportExample;
import com.tyust.common.DateHandler;
import com.tyust.service.en.EnApplyInfoService;
import com.tyust.service.en.EnTestReportService;

public class EnTestReportAction {

	private EnTestReport enTestReport;

	private Map<String, Object> jsonStr;

	private File image;

	private String imageFileName;

	private EnTestReportService enTestReportService;
	
	private EnApplyInfoService enApplyInfoService;

	public String selEnTestReportList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int pageNum = Integer.parseInt(request.getParameter("page"));
		int rp = Integer.parseInt(request.getParameter("rp"));
		int start = (pageNum - 1) * rp;
		int limit = pageNum * rp;
		EnTestReportExample ex = new EnTestReportExample();
		EnTestReportExample.Criteria cri = ex.createCriteria();
		ex.setLimit(limit);
		ex.setStart(start);
		ex.enablePagination();
		ex.setOrderByClause("test_date desc");
		String state = request.getParameter("state");
		if (state != null) {
			if (state.equals("1")) {
				cri.andTestStatusEqualTo(state);
			} else if (state.equals("2")) {
				cri.andTestStatusEqualTo(state);
			}
		}
		
		List<EnTestReport> list = enTestReportService.getEnTestReportDAO().selectByExample(ex);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		EnTestReport report = new EnTestReport();
		try {
			for(int i = 0 ;i<list.size();i++){
				report = list.get(i);
				JSONObject json = new JSONObject();
				json.put("num", i+1);
				json.put("ReportId", report.getEnvironmentApplyId());
				json.put("TestSampleName", report.getTestSampleName());			
				json.put("TestReportNumber", report.getTestReportNumber());
				json.put("TestDate", DateHandler.dateToString(report.getTestDate()));
				json.put("TestResult", report.getTestResult());
				json.put("TestUserName", report.getTestUserName());
				if("1".equals(report.getTestStatus())){
					json.put("State", "未提交");
					json.put("operate","<a href='enTestReport_update.jsp?enTestReportId="+report.getEnvironmentTestReportId()+"&enApplyId="+report.getEnvironmentApplyId()+"'>修改</a>");
				}else{
					json.put("State", "已提交");
					json.put("operate","<a href='../../reportJsp/test_rpt.jsp?testRptId="+report.getEnvironmentTestReportId()+"&applyId="+report.getEnvironmentApplyId()+"'>打印 </a> | <a href='javascript:void(0)' onclick='zs_add(this)'>添加证书</a> | "
							+ "<a href='javascript:void(0)' onclick='zs_update(this)'>更新证书</a> | <a href='javascript:void(0)' onclick='zs_delete(this)'>证书删除</a>");
				}
				jsonArray.put(json);
			}
			jsonObject.put("rows", jsonArray);
			jsonObject.put("page", pageNum);
			jsonObject.put("total", list.size());
			ServletActionContext.getResponse().getWriter().write(jsonObject.toString());
		}catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveEnTestReport() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String operate = request.getParameter("operate");
		String enApplyId = enTestReport.getEnvironmentApplyId();
		// 修改申请记录的状态
		EnApplyInfo applyInfo = new EnApplyInfo();
		applyInfo.setEnvironmentApplyId(enApplyId);
		
		if (operate.equals("save")) {
			enTestReport.setTestStatus("1");
			applyInfo.setEnvironmentApplyStatus("5");
		} else if (operate.equals("submit")) {
			enTestReport.setTestStatus("2");
			applyInfo.setEnvironmentApplyStatus("6");
		}
		try {
			String enTestReportId = enTestReportService.insertEnTestReport(enTestReport);
			enApplyInfoService.getEnApplyInfoDAO().updateByPrimaryKeySelective(applyInfo);
			jsonStr.put("enTestReportId", enTestReportId);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		jsonStr.put("result", result);
		return "success";
	}

	public String updateEnTestReport() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String operate = request.getParameter("operate");
		String enApplyId = enTestReport.getEnvironmentApplyId();
		// 修改申请记录的状态
		EnApplyInfo applyInfo = new EnApplyInfo();
		applyInfo.setEnvironmentApplyId(enApplyId);
		if (operate.equals("save")) {
			enTestReport.setTestStatus("1");
			applyInfo.setEnvironmentApplyStatus("5");
		} else if (operate.equals("submit")) {
			enTestReport.setTestStatus("2");
			applyInfo.setEnvironmentApplyStatus("6");
		}
		try {
			enTestReportService.getEnTestReportDAO().updateByPrimaryKey(enTestReport);
			enApplyInfoService.getEnApplyInfoDAO().updateByPrimaryKeySelective(applyInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		jsonStr.put("result", result);
		return "success";
	}

	public String selEnTestReportById() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String enTestReportId = request.getParameter("enTestReportId");
		try {
			EnTestReport report = enTestReportService.getEnTestReportDAO().selectByPrimaryKey(enTestReportId);
			if (report != null) {
				jsonStr.put("testReportNumber", report.getTestReportNumber());
				jsonStr.put("testDate", DateHandler.dateToString(report.getTestDate()));
				jsonStr.put("testUserName", report.getTestUserName());
				jsonStr.put("testUnitName", report.getTestUnitName());
				jsonStr.put("testSampleName", report.getTestSampleName());
				jsonStr.put("testResult", report.getTestResult());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		jsonStr.put("result", result);
		return "success";
	}

	public String delEnTestReport() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String enTestReportId = request.getParameter("enTestReportId");
		String enApplyId = request.getParameter("enApplyId");
		try {
			enTestReportService.delEnTestReport(enTestReportId, enApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		jsonStr.put("result", result);
		return "success";
	}

	public String saveEnPic() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String enApplyId = request.getParameter("enApplyId");
		String picType = request.getParameter("picType");
		enTestReportService.uploadEnTestPic(enApplyId, picType, image, imageFileName);
		jsonStr.put("result", result);
		return "success";
	}

	public String selEnPic() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String enApplyId = request.getParameter("enApplyId");
		String enPicType = request.getParameter("enPicType");
		EnPicExample example = new EnPicExample();
		EnPicExample.Criteria cri = example.createCriteria();
		cri.andEnApplyIdEqualTo(enApplyId);
		cri.andEnPicTypeEqualTo(enPicType);
		try {
			List<EnPic> enPicList = enTestReportService.getEnPicDAO().selectByExample(example);
			JSONArray jsonArray = new JSONArray();
			if (enPicList != null && enPicList.size() > 0) {
				for (EnPic pic : enPicList) {
					JSONObject json = new JSONObject();
					json.put("enPicUrl", pic.getEnPicUrl());
					json.put("enPicId", pic.getEnPicId());
					jsonArray.put(json);
				}
			}
			ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public String delEnPic() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String enPicId = request.getParameter("enPicId");
		enTestReportService.delEnTestPic(enPicId);
		jsonStr.put("result", result);
		return "success";
	}

	// 保存检测中使用的检测仪器
	public String saveReportAndIns() {
		String result = "success";
		jsonStr = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String enTestReportId = request.getParameter("enTestReportId");
		String insIdStr = request.getParameter("insIdStr");
		List<String> insIdList = new ArrayList<String>();
		if (!insIdStr.equals("")) {
			String[] insIdA = insIdStr.trim().split(",");
			insIdList = Arrays.asList(insIdA);
		}
		try {

			// 删除原有关系
			enTestReportService.delByReportId(enTestReportId);

			for (String insId : insIdList) {
				EnTestInstrument record = new EnTestInstrument();
				record.setEnvironmentTestReportId(enTestReportId);
				record.setInstrumentId(insId);
				enTestReportService.insertReportAndIns(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		jsonStr.put("result", result);
		return "success";
	}

	public EnTestReport getEnTestReport() {
		return enTestReport;
	}

	public void setEnTestReport(EnTestReport enTestReport) {
		this.enTestReport = enTestReport;
	}

	public Map<String, Object> getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(Map<String, Object> jsonStr) {
		this.jsonStr = jsonStr;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public EnTestReportService getEnTestReportService() {
		return enTestReportService;
	}

	public void setEnTestReportService(EnTestReportService enTestReportService) {
		this.enTestReportService = enTestReportService;
	}

	public EnApplyInfoService getEnApplyInfoService() {
		return enApplyInfoService;
	}

	public void setEnApplyInfoService(EnApplyInfoService enApplyInfoService) {
		this.enApplyInfoService = enApplyInfoService;
	}
}
