package com.java.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao=new UserDao();
	DbUtil dbutil=new DbUtil();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if(StringUtil.isNotEmpty(action)){
			if(action.equals("modifyInfo")){
				updateUserInfo(request,response);
			}else if(action.equals("modifyPsw")){
				updateUserPsw(request,response);
			}
		}
		else{
			queryUserInfo(request,response);
		}
	}

	private void queryUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String userId=request.getParameter("userId");
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			User user=userdao.queryUserInfoById(conn, Integer.parseInt(userId));
			request.setAttribute("user", user);
			request.getRequestDispatcher("userInfo.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//用户修改一般信息
	private void updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String motto=request.getParameter("motto");
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		user.setMotto(motto);
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			int num=userdao.modifyUserInfo(conn, user);
			if(num!=0){
				response.sendRedirect("User?userId="+userId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				dbutil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//用户修改密码
	private void updateUserPsw(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		user.setPassword(password);
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			int num=userdao.modifyUserPsw(conn, user);
			if(num!=0){
				response.sendRedirect("User?userId="+userId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				dbutil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
