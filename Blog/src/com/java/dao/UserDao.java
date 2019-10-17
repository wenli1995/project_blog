package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.User;
import com.java.util.DateUtil;

public class UserDao {
	public List<User> queryUser(Connection conn,String sql) throws Exception{
		ArrayList<User> userList=new ArrayList<>();
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			User user=new User();
			user.setUserId(rs.getInt("id"));
			user.setMotto(rs.getString("motto"));
			user.setPassword(rs.getString("password"));
			user.setPoints(rs.getInt("points"));
			user.setRegisterDate(DateUtil.String2Date(rs.getString("registerDate"),"yyyy-MM-dd"));
			user.setUserName(rs.getString("userName"));
			userList.add(user);
		}
		return userList;
		
	}
	
	public User queryUser(Connection conn,User user) throws Exception{
		String sql="select * from user where userName=? and password=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getUserName());
		stat.setString(2, user.getPassword());
		ResultSet rs=stat.executeQuery();
		User loginUser=null;
		if(rs.next()){
			loginUser=new User();
			loginUser.setUserName(rs.getString("userName"));
			loginUser.setPassword(rs.getString("password"));
			loginUser.setUserId(rs.getInt("id")); 
			loginUser.setMotto(rs.getString("motto"));
		}
		return loginUser;
	}
	
	public User queryUserInfoById(Connection conn, int userId) throws Exception{
		String sql="select * from user where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1,userId );
		ResultSet rs=stat.executeQuery();
		User aUser=null;
		if(rs.next()){
			aUser=new User();
			aUser.setUserId(rs.getInt("id"));
			aUser.setUserName(rs.getString("userName"));
			aUser.setMotto(rs.getString("motto"));
			aUser.setPoints(rs.getInt("points"));
			aUser.setRegisterDate(DateUtil.String2Date(rs.getString("registerDate"),"yyyyMMdd"));
			
		}
		return aUser;
		
	}
	
	public boolean existUserWithName(Connection conn, String userName) throws Exception{
		String sql="select * from user where userName=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1,userName );
		ResultSet rs=stat.executeQuery();
		if(rs.next()){
			return true;
			
		}
		return false;
		
	}
	
	public int modifyUserInfo(Connection conn,User user) throws SQLException{
		String sql="update user set motto=? where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getMotto());
		stat.setInt(2,user.getUserId());
		return stat.executeUpdate();
	}
	
	
	public int modifyUserPsw(Connection conn,User user) throws SQLException{
		String sql="update user set password=? where id=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getPassword());
		stat.setInt(2,user.getUserId());
		return 0;
	}
	
	public int addUser(Connection conn,User user) throws SQLException{
		String sql="insert into user(id,userName,password,motto,points,registerDate) values(null,?,?,'',0,now())";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, user.getUserName());
		stat.setString(2, user.getPassword());
		return stat.executeUpdate();
	}
}
