package com.oec.demo.dao;

import java.util.List;

import com.oec.model.Paging;

/**
 * @author liujie
 * @version 1.0
 * @since 2017骞�2鏈�23鏃� 涓婂崍11:21:43
 */
public interface AnaysisDao {

	boolean companyNameYZ(String getshortname, String getlongname);

	int insertcompany(String getshortname, String getlongname);

	boolean errorwordYZ(String geterrorword);

	int insertword(String geterrorword);

	Paging searchCompany(int page, int rows, String searchCompany);

	Paging searchCompany1(int page, int rows, String searchCompany);

	Paging startanaysis(String title, String content);

	int insertRelate(String relateContent,String relation_nation);

	int insertStop(String stopContent,String stopType);

	Paging search_M(int page, int rows, String searchCompany);

	Paging search_R(int page, int rows, String searchCompany);

	Paging search_T(int page, int rows, String searchCompany);

	int zhuce(String username, String password, String tele, String address);

	List<String> getCompanyList();
}
