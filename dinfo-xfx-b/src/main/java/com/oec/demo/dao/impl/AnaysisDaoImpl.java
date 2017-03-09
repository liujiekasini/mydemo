package com.oec.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oec.demo.dao.AnaysisDao;
import com.oec.model.Paging;

/**
 * @author liujie
 * @version 1.0
 * @since 2017骞�2鏈�23鏃� 涓婂崍11:22:28
 */
@Repository
public class AnaysisDaoImpl implements AnaysisDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean companyNameYZ(String getshortname, String getlongname) {
		String sql = "select * from rec_company_name where short_name=? and full_name=?";
		List<Map<String, Object>> count = jdbcTemplate.queryForList(sql, new Object[] { getshortname, getlongname });
		if (count.size() > 0) {
			return true;
		}
		return false;
	}

	// rec_company_name琛�
	public int insertcompany(String getshortname, String getlongname) {
		if (companyNameYZ(getshortname, getlongname)) {
			return -1;
		}
		String sql = "insert into rec_company_name(short_name,full_name,create_time) values(?,?,NOW())";
		int count = 0;
		try {
			if(getshortname.contains("#")){
				String[] strs=getshortname.split("#");
				for(String str:strs){
					count+=jdbcTemplate.update(sql,new Object[]{str,getlongname});
				}
			}else{
			count = jdbcTemplate.update(sql, new Object[] { getshortname, getlongname });
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			count=0;
		}
		return count;
	}

	public boolean errorwordYZ(String geterrorword) {
		String sql = "select * from rec_person_name where name=?";
		List<Map<String, Object>> count = jdbcTemplate.queryForList(sql, new Object[] { geterrorword });
		if (count.size() > 0) {
			return true;
		}
		return false;
	}

	// rec_company_name琛�
	public int insertword(String managerContent) {
		if (errorwordYZ(managerContent)) {
			return -1;
		}
		String sql = "insert into rec_person_name(name,create_time) values(?,NOW())";
		int count=0;
		try {
			if(managerContent.contains("#")){
				String[] strs=managerContent.split("#");
				for(String str:strs){
					
					count+=jdbcTemplate.update(sql,new Object[]{str});
				}
			}else{
				count = jdbcTemplate.update(sql, new Object[] { managerContent });
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			count=0;
		}
		return count;
	}

	public Paging searchCompany(int page, int rows, String searchCompany) {
		int start = rows * (page - 1);
		String sql = "select short_name as shortName,full_name as fullName,date_format(create_time,'%y-%m-%d %h:%m:%s') as createTime from rec_company_name";
		if (!searchCompany.equals("")) {
			sql += " where short_name like '%" + searchCompany + "%' or full_name like '%" + searchCompany + "%'";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
		sql += "  limit " + start + "," + rows;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Paging pagelist = new Paging();
		pagelist.setData(list);
		pagelist.setTotal(list1.size());
		return pagelist;
	}

	public Paging searchCompany1(int page, int rows, String searchCompany) {
		int start = rows * (page - 1);
		String sql = "select name as name,date_format(create_time,'%y-%m-%d %h:%m:%s')  as createTime from company_wrong";
		if (!searchCompany.equals("")) {
			sql += " where name like '%" + searchCompany + "%'";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
		sql += "  limit " + start + "," + rows;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Paging pagelist = new Paging();
		pagelist.setData(list);
		pagelist.setTotal(list1.size());
		return pagelist;
	}

	public Paging startanaysis(String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 鎻掑叆鍏崇郴
	 */
	public int insertRelate(String relateContent,String relation_nation) {
		if (yanZhengGX(relateContent)) {
			return -1;
		}
		String sql = "insert into rec_relation(relation,relation_nation,create_time) values(?,?,NOW())";
		int count = 0;
		try {
			if(relateContent.contains("#")){
				String[] strs=relateContent.split("#");
				for(String str:strs){
					count+=jdbcTemplate.update(sql,new Object[]{str,relation_nation});
				}
			}else{
				count = jdbcTemplate.update(sql, new Object[] {relateContent,relation_nation });
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			count=0;
		}
		return count;
	}
	public boolean yanZhengGX(String name){
		String sql="select relation from rec_relation where relation=?";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql,new Object[]{name});
		if(list.size()>0){
			return true;
		}return false;
	}

	public int insertStop(String stopContent,String stopType) {
		if (yanZhengStop(stopContent)) {
			return -1;
		}
		String sql = "insert into stop_word(word,word_type,create_time) values(?,?,NOW())";
		int count = 0;
		try {
		if(stopContent.contains("#")){
			String[] strs=stopContent.split("#");
			for(String str:strs){
				count+=jdbcTemplate.update(sql,new Object[]{str,stopType});
			}
		}else{
			count = jdbcTemplate.update(sql, new Object[] {stopContent,stopType });
		}
		} catch (DataAccessException e) {
			e.printStackTrace();
			count=0;
		}
		return count;
	}
	public boolean yanZhengStop(String name){
		String sql="select word from stop_word where word=?";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql,new Object[]{name});
		if(list.size()>0){
			return true;
		}return false;
	}

	public Paging search_M(int page, int rows, String searchCompany) {
		int start = rows * (page - 1);
		String sql = "select name as name,date_format(create_time,'%y-%m-%d %h:%m:%s') as createTime from rec_person_name";
		if (!searchCompany.equals("")) {
			sql += " where name like '%" + searchCompany.trim() + "%'";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
		sql += "  limit " + start + "," + rows;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Paging pagelist = new Paging();
		pagelist.setData(list);
		pagelist.setTotal(list1.size());
		return pagelist;
	}
	public Paging search_R(int page, int rows, String searchCompany) {
		int start = rows * (page - 1);
		String sql = "select relation as relation,relation_nation as relationNation,date_format(create_time,'%y-%m-%d %h:%m:%s') as createTime from rec_relation";
		if (!searchCompany.equals("")) {
			sql += " where relation like '%" + searchCompany.trim() + "%'";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
		sql += "  limit " + start + "," + rows;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Paging pagelist = new Paging();
		pagelist.setData(list);
		pagelist.setTotal(list1.size());
		return pagelist;
	}
	public Paging search_T(int page, int rows, String searchCompany) {
		int start = rows * (page - 1);
		String sql = "select word as word,word_type as wordType,date_format(create_time,'%y-%m-%d %h:%m:%s') as createTime from stop_word";
		if (!searchCompany.equals("")) {
			sql += " where word like '%" + searchCompany.trim() + "%'";
		}
		List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql);
		sql += "  limit " + start + "," + rows;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Paging pagelist = new Paging();
		pagelist.setData(list);
		pagelist.setTotal(list1.size());
		return pagelist;
	}
	private boolean  yanZhengZC(String  username,String tele){
		String sql="select * from t_user where username =? and tele = ?";
		Map<String,Object> map=jdbcTemplate.queryForMap(sql,new Object[]{username,tele});
		if(map!=null){
			return true;
		}return false;
	}
	public int zhuce(String username, String password, String tele, String address) {
		if(yanZhengZC(username,tele)){
				return -1;
		}
		String sql="insert into t_user(username,password,tele,address) values (?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{username,password,tele,address});
	}

	public List<String> getCompanyList() {
		String sql="select short_name,full_name from rec_company_name";
		List<Map<String,Object>> olist=jdbcTemplate.queryForList(sql);
		List<String> strlist=new ArrayList<String>();
		if(olist!=null && olist.size()!=0){
			for(Map<String,Object> map:olist){
				strlist.add(map.get("short_name")+","+map.get("fule_name"));
			}
		}
		return strlist;
	}
}
