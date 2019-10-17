package com.java.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
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
		request.setCharacterEncoding("utf-8");
		String req=request.getParameter("req");
		if(req.equals("login")){
			loginBlog(request,response);
		}else if(req.equals("logout")){
			logoutBlog(request,response);
		}else if(req.equals("signup")){
			signupBlog(request,response);
		}
		
		
		
	}

	private void signupBlog(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			if(userDao.existUserWithName(conn, userName)){
				request.setAttribute("msg", "�û����ѱ�ʹ��");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}else{
				User user=new User(userName,password);
				int num=userDao.addUser(conn, user);
				if(num!=0){
					response.sendRedirect("Home");
				}else{
					request.setAttribute("msg", "ע��ʧ��");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void logoutBlog(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();//��ȡ��request������session
		session.removeAttribute("loginUser");
		try {
			request.getRequestDispatcher("Home").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void loginBlog(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		User user=new User(userName,password);
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			User loginUser=userDao.queryUser(conn, user);
			if(loginUser!=null){
				request.setAttribute("message", "��¼�ɹ�");
				HttpSession session=request.getSession();//��ȡ��request������session
				session.setAttribute("loginUser", loginUser);   //����½�û�����Ϊ�Ự�������Ա�����ҳ��ʾ�û���Ϣ
				response.sendRedirect("Home");
			}else{
				request.setAttribute("message", "��¼ʧ�ܣ��û������������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
