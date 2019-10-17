package com.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.BlogDao;
import com.java.dao.CommentDao;
import com.java.dao.UserDao;
import com.java.model.Blog;
import com.java.util.DbUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DiggServlet
 */
public class DiggServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbutil=new DbUtil();
	BlogDao blogDao=new BlogDao();
	CommentDao commentDao=new CommentDao();
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
			String action=request.getParameter("action");
			String blogId=request.getParameter("blogId");
			if(action.equals("addLikes")){
				Connection conn=null;
				try {
					conn=dbutil.getConnection();	
					Blog blog=blogDao.getBlogById(conn, Integer.parseInt(blogId));
					blog.setLikes(blog.getLikes()+1);
					blogDao.updateBlogLikes(conn,blog);
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out=response.getWriter();;
					JSONObject resultJson=new JSONObject();
					resultJson.put("likes", blog.getLikes());
					out.println(resultJson);
					out.flush();
					out.close();
				} catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	
}
