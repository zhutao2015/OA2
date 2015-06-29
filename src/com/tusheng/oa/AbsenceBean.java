package com.tusheng.oa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class AbsenceBean {
	private int id;
	private int user_id;
	private Date start_time;
	private Date end_time;
	private int status;
    private Date created_at;
    private String name;
   
    public boolean absencein(int uid,String start_time,String end_time, int status) {
		DB db = new DB();
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = format.format(d);
		String s = "insert into absence set user_id =\"" + uid + "\", start_time= \"" + start_time + "\",end_time=\"" + end_time +"\",status=\"" +status + "\",created_at =\"" + now + "\"";
		db.insert(s);
		db.close();
		return true;
	}
    public ArrayList<AbsenceBean> absenceout(int uid) {
		DB db = new DB();			
		ArrayList<AbsenceBean> result = new ArrayList<AbsenceBean>();
		try {
			String s = "select created_at,start_time,end_time,status from absence where user_id=" + uid;
			ResultSet rs = db.select(s);
			while(rs.next()){
				AbsenceBean bean = new AbsenceBean();
				bean.created_at = rs.getDate("created_at");
				bean.start_time = rs.getDate("start_time");
				bean.end_time = rs.getDate("end_time");
				bean.status = rs.getInt("status");
				result.add(bean);
			}
			db.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();
			return result;
		}
    }   
    public boolean mgabsencein(int id,int z) {
		DB db = new DB();
		String s = "update absence set status ="+ z +" where id=" + id;
		db.insert(s);
		db.close();
		return true;		
    }      
    public ArrayList<Integer> userid() {
		DB db = new DB();					
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			String s = "select user_id from absence";
			ResultSet rs = db.select(s);
			while(rs.next()){
				AbsenceBean bean = new AbsenceBean();
				bean.user_id = rs.getInt("user_id");
				result.add(bean.user_id);
			}
			db.close();
			return result;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();
			return result;
		}
    }
    public String nameselec(int uid){
    	DB db = new DB();					
		String realname = null;
		try {
			String s = "select realname from user where id=" + uid;
			ResultSet rs = db.select(s);
			while(rs.next()){
			realname = rs.getString("realname");
			}
			db.close();
			return realname;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();
			return realname;
		}
	}
    public ArrayList<AbsenceBean> mgabsenceout() {
		DB db = new DB();	
		AbsenceBean ubean = new AbsenceBean(); 
		ArrayList<AbsenceBean> result = new ArrayList<AbsenceBean>();
		ArrayList<Integer> userid = ubean.userid();
	    int i = 0;
		try {
			String s = "select id,created_at,start_time,end_time,status from absence";
			ResultSet rs = db.select(s);
			while(rs.next()){	
				AbsenceBean bean = new AbsenceBean();
				bean.id = rs.getInt("id");
				bean.name=bean.nameselec(userid.get(i++));
				bean.created_at = rs.getDate("created_at");
				bean.start_time = rs.getDate("start_time");
				bean.end_time = rs.getDate("end_time");
				bean.status = rs.getInt("status");
				result.add(bean);
			}
			db.close();
			return result;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();
			return result;
		}
		
    }
//  setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
