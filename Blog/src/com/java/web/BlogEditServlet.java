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
				//修改操作
			}else{
				addBlog(request,response);
				//添加操作
			}
			
		}else if(edtype.equals("delete")){
			   deleteBlog(request,response);
		}else{
			
		}
		//showPanelContent(request,response,userId);  //显示链接面板信息
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
				blog.setStatus(1);  //已发布
				servStr="myBlog?userId="+userId;
				//如果是发布，就跳到个人博客主页
			}else if(oper.equals("save")){
				blog.setStatus(0);  //草稿
				servStr="myBlog?action=manageBlog&userId="+userId;
				//如果是草稿，就跳到博客管理页面
			}else{

			}
			
			int num=blogdao.addBlog(conn, blog);
			if(num!=0){
				request.setAttribute("msg", "添加成功");
				//response.sendRedirect("myBlog?userId="+userId);  //这里直接重定位，因为不在需要之前的参数
				response.sendRedirect(servStr);  //这里直接重定位，因为不在需要之前的参数
			}else{
				request.setAttribute("msg", "添加失败");
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
				blog.setStatus(1);  //已发布
				servStr="myBlog?userId="+userId;
				//如果是发布，就跳到个人博客主页
			}else if(oper.equals("save")){
				blog.setStatus(0);  //草稿
				servStr="myBlog?action=manageBlog&userId="+userId;
				//如果是草稿，就跳到博客管理页面
			}else{
	
			}
			
			int num=blogdao.modifyBlog(conn, blog);
			if(num!=0){
				request.setAttribute("msg", "修改成功");
				//request.getRequestDispatcher("myBlog").forward(request, response);
				response.sendRedirect(servStr);  //这里直接重定位，因为不在需要之前的参数
			}else{
				request.setAttribute("msg", "修改失败");
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
				request.setAttribute("msg", "删除成功");
		        //response.sendRedirect("myBlog?userId="+userId);
				response.sendRedirect("myBlog?action=manageBlog&userId="+userId);
			}else{
				request.setAttribute("msg", "删除失败");
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
