package com.java.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.CommentDao;
import com.java.model.Comment;
import com.java.util.DbUtil;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbutil=new DbUtil();
	CommentDao commentDao=new CommentDao();
       
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
		String action=request.getParameter("action");
		if(action.equals("addComment")){
			addComment(request,response);
		}
	}

	private void addComment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String blogId=request.getParameter("blogId");
		String commentContent=request.getParameter("comment");
		Comment comment=new Comment();
		comment.setUserId(Integer.parseInt(userId));
		comment.setBlogId(Integer.parseInt(blogId));
		comment.setContent(commentContent);
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			int num=commentDao.addComment(conn, comment);
			if(num!=0){
				//评论添加成功
				response.sendRedirect("myBlog?action=showBlogDetial&userId="+userId+"&blogId="+blogId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbutil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
}
