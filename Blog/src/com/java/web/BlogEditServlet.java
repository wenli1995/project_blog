package com.java.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.BlogDao;
import com.java.dao.UserDao;
import com.java.model.Blog;
import com.java.model.BlogType;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

/**
 * Servlet implementation class BlogEditServlet
 */
public class BlogEditServlet extends HttpServlet {
	
	UserDao userDao=new UserDao();
	BlogDao blogdao=new BlogDao();
	DbUtil dbUtil=new DbUtil();
	private static final long serialVersionUID = 1L;

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
		String edtype=request.getParameter("edtype");
		if(edtype.equals("edit")){
			String blogId=request.getParameter("blogId");
			if(StringUtil.isNotEmpty(blogId)){
				updateBlog(request,response);
				//�޸Ĳ���
			}else{
				addBlog(request,response);
				//��Ӳ���
			}
			
		}else if(edtype.equals("delete")){
			   deleteBlog(request,response);
		}else{
			
		}
		//showPanelContent(request,response,userId);  //��ʾ���������Ϣ
	}

	private void addBlog(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String blogTyle=request.getParameter("blogtype");
		String oper=request.getParameter("oper");
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			User user=userDao.queryUserInfoById(conn,Integer.parseInt(userId));
			Blog blog=new Blog();
			blog.setUserId(Integer.parseInt(userId));
			blog.setUserName(user.getUserName());
			blog.setTitle(title);
			blog.setContent(content);
			blog.setBlogType(blogTyle);
			String servStr="";
			if(oper.equals("publish")){
				blog.setStatus(1);  //�ѷ���
				servStr="myBlog?userId="+userId;
				//����Ƿ��������������˲�����ҳ
			}else if(oper.equals("save")){
				blog.setStatus(0);  //�ݸ�
				servStr="myBlog?action=manageBlog&userId="+userId;
				//����ǲݸ壬���������͹���ҳ��
			}else{

			}
			
			int num=blogdao.addBlog(conn, blog);
			if(num!=0){
				request.setAttribute("msg", "��ӳɹ�");
				//response.sendRedirect("myBlog?userId="+userId);  //����ֱ���ض�λ����Ϊ������Ҫ֮ǰ�Ĳ���
				response.sendRedirect(servStr);  //����ֱ���ض�λ����Ϊ������Ҫ֮ǰ�Ĳ���
			}else{
				request.setAttribute("msg", "���ʧ��");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateBlog(HttpServletRequest request, HttpServletResponse response){
		String userId=request.getParameter("userId");
		String blogId=request.getParameter("blogId");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String blogTyle=request.getParameter("blogtype");
		String oper=request.getParameter("oper");
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			Blog blog=new Blog();
			blog.setBlogId(Integer.parseInt(blogId));
			blog.setTitle(title);
			blog.setContent(content);
			blog.setBlogType(blogTyle);
			String servStr="";
			if(oper.equals("publish")){
				blog.setStatus(1);  //�ѷ���
				servStr="myBlog?userId="+userId;
				//����Ƿ��������������˲�����ҳ
			}else if(oper.equals("save")){
				blog.setStatus(0);  //�ݸ�
				servStr="myBlog?action=manageBlog&userId="+userId;
				//����ǲݸ壬���������͹���ҳ��
			}else{
	
			}
			
			int num=blogdao.modifyBlog(conn, blog);
			if(num!=0){
				request.setAttribute("msg", "�޸ĳɹ�");
				//request.getRequestDispatcher("myBlog").forward(request, response);
				response.sendRedirect(servStr);  //����ֱ���ض�λ����Ϊ������Ҫ֮ǰ�Ĳ���
			}else{
				request.setAttribute("msg", "�޸�ʧ��");
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
	//BlogEdit?edtype=delete&blogId=${mblog.blogId}
	public void deleteBlog(HttpServletRequest request, HttpServletResponse response){
		String blogId=request.getParameter("blogId");
		String userId=request.getParameter("userId");
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			int num=blogdao.deleteBlog(conn,Integer.parseInt(blogId));
			if(num!=0){
				request.setAttribute("msg", "ɾ���ɹ�");
		        //response.sendRedirect("myBlog?userId="+userId);
				response.sendRedirect("myBlog?action=manageBlog&userId="+userId);
			}else{
				request.setAttribute("msg", "ɾ��ʧ��");
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
