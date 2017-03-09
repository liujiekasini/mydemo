package com.oec.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinfo.boc.analysis.BocAnalysis;
import com.dinfo.boc.analysis.impl.BocAnalysisImpl;
import com.dinfo.boc.bean.BocParseInfo;
import com.dinfo.boc.utils.OecDaemonUtils;
import com.oec.demo.dao.AnaysisDao;
import com.oec.demo.service.AnaysisService;
import com.oec.model.Paging;
import com.oec.utils.ReadPropertity;

@Service
public class AnaysisServiceImpl implements AnaysisService {

	@Autowired
	private AnaysisDao anaysisDao;

	public boolean companyNameYZ(String getshortname, String getlongname) {
		return anaysisDao.companyNameYZ(getshortname, getlongname);
	}

	public int insertcompany(String getshortname, String getlongname) {
		return anaysisDao.insertcompany(getshortname, getlongname);
	}

	public boolean errorwordYZ(String geterrorword) {
		return anaysisDao.errorwordYZ(geterrorword);
	}

	public int insertword(String managerContent) {
		return anaysisDao.insertword(managerContent);
	}

	public Paging searchCompany(int page, int rows, String searchCompany) {
		return anaysisDao.searchCompany(page, rows, searchCompany);
	}

	public Paging searchCompany1(int page, int rows, String searchCompany) {
		return anaysisDao.searchCompany1(page, rows, searchCompany);
	}
	/**
	 * 企业风险
	 */
	public Paging startanaysis(String title, String content) {
		BocAnalysis analysis = new BocAnalysisImpl();
		 List<String> companyList=new ArrayList<String>();
		//查询公司简称 全称
		 companyList=anaysisDao.getCompanyList();
		OecDaemonUtils.initResources(ReadPropertity.getProperty("oec.ip"), 1, companyList);
		String projectCode = ReadPropertity.getProperty("oec.project");
		String rootOntoId = ReadPropertity.getProperty("oec.ontoId");
		int ontoLevel = -1;
		int dataMiningType = 1;
		List<String> usefulOntoIds = new ArrayList<String>();
		String keywords = "";

		List<BocParseInfo> bocInfos = analysis.analysisBocParseInfos(projectCode, dataMiningType, rootOntoId, ontoLevel,
				usefulOntoIds, title, keywords, content);
		OecDaemonUtils.closeConnection();
		Paging page = new Paging();
		page.setData(bocInfos);
		page.setTotal(bocInfos.size());
		return page;
	}

	public int insertRelate(String relateContent,String relation_nation) {
		return anaysisDao.insertRelate(relateContent,relation_nation);
	}

	public int insertStop(String stopContent,String stopType) {
		return anaysisDao.insertStop(stopContent,stopType);
	}

	public Paging search_M(int page, int rows, String searchCompany) {
		return anaysisDao.search_M(page,rows,searchCompany);
	}

	public Paging search_R(int page, int rows, String searchCompany) {
		return anaysisDao.search_R(page,rows,searchCompany);
	}
	public Paging search_T(int page, int rows, String searchCompany) {
		return anaysisDao.search_T(page,rows,searchCompany);
	}

	public int zhuce(String username, String password, String tele, String address) {
		return anaysisDao.zhuce(username,password,tele,address);
	}

}
