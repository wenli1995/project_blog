package com.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.BlogDao;
import com.java.dao.CommentDao;
import com.java.dao.UserDao;
import com.java.model.Blog;
import com.java.model.BlogType;
import com.java.model.Comment;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class BlogServlet
 */
public class BlogServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		String userId=request.getParameter("userId");
		showPanelContent(request,response,userId);  //��ʾ���������Ϣ
		
		String action=request.getParameter("action");
		if(StringUtil.isNotEmpty(action)){
			if(action.equals("showBlogDetial")){
				String blogId=request.getParameter("blogId");
				Connection conn=null;
				try {
					conn=dbutil.getConnection();
					Blog blog=blogDao.getBlogById(conn, Integer.parseInt(blogId));
					blog.setViews(blog.getViews()+1);   
					blogDao.updateBlogViews(conn, blog);  //�Ķ���
					showBlogDetail(request,response,blogId);  //��ʾ������ϸ����
					showComents(request,response,blogId);  //��ʾ�ò�����������

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
				request.setAttribute("blogPage", "blogDetail.jsp");	
				request.getRequestDispatcher("blog.jsp").forward(request, response); 
				//��ʾ����Ĳ��͵���ϸ����
			}else if(action.equals("showBlogListByType")){
				String blogTypeValue=request.getParameter("blogType");
				byte[] blogTypeBytes = blogTypeValue.getBytes("ISO-8859-1");
				String blogType = new String(blogTypeBytes,"UTF-8");  //���Ĵ���
				Connection conn=null;
				try {
					conn=dbutil.getConnection();
					String sql="select * from blog where userId="+userId+" and blogType=\'"+blogType+"\' order by  createTime";
					List<Blog> tBlogList=blogDao.queryBlog(conn, sql);
					request.setAttribute("tBlogList", tBlogList);
					request.setAttribute("blogType", blogType);
					request.setAttribute("blogPage", "blogListByType.jsp");	
					request.getRequestDispatcher("blog.jsp").forward(request, response); 
					
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
			}else if(action.equals("addBlog")){
				request.setAttribute("managePage", "blogEdit.jsp");
				String edBlogId=request.getParameter("edBlogId");
				if(StringUtil.isNotEmpty(edBlogId)){
					//�޸ĵĳ���
					Connection conn=null;
					try {
						conn = dbutil.getConnection();
						Blog blog=blogDao.getBlogById(conn, Integer.parseInt(edBlogId));
						request.setAttribute("edBlog", blog);
						//���ͷ�����Ϣ
						List<BlogType> blogNumOfType=blogDao.queryGroupByBlogType(conn, Integer.parseInt(userId));
						request.setAttribute("blogNumOfType", blogNumOfType);//���ͷ���
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
				request.getRequestDispatcher("blogManage.jsp").forward(request, response);
				//��Ӳ���
				
			}else if(action.equals("manageBlog")){
				String sql="SELECT * FROM blog WHERE userId="+userId+" ORDER BY createTime DESC";
				String blogTypeValue=request.getParameter("blogType");
				if(StringUtil.isNotEmpty(blogTypeValue)){
					byte[] blogTypeBytes = blogTypeValue.getBytes("ISO-8859-1");
					String blogType = new String(blogTypeBytes,"UTF-8");  //���Ĵ���
					sql="SELECT * FROM blog WHERE userId="+userId+" and blogType=\'"+blogType+"\' ORDER BY createTime DESC";
				}
				Connection conn=null;
				try {
					conn=dbutil.getConnection();
					List<Blog> mblogList=blogDao.queryBlog(conn, sql);
					for(Blog blog:mblogList){
						int num=commentDao.getCommentNumByBlogId(conn,blog.getBlogId());
						blog.setComments(num);
						//������
					}
					request.setAttribute("mblogList", mblogList);
					//���ͷ�����Ϣ
					List<BlogType> blogNumOfType=blogDao.queryGroupByBlogType(conn, Integer.parseInt(userId));
					request.setAttribute("blogNumOfType", blogNumOfType);//���ͷ���
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
				request.setAttribute("managePage", "blogUpdAndDel.jsp");
				request.getRequestDispatcher("blogManage.jsp").forward(request, response);
				//������
			}
		}
		else
		{
			//��ʾ�ò��������ѷ����Ĳ�������
			String sqlpre="SELECT * FROM blog WHERE status=1 and  userId="+userId;
			//�����ڲ�ѯ
			String BgnDate=request.getParameter("BgnDate");  //�����ʽ2010911
			String EndDate=request.getParameter("EndDate");
			StringBuffer sb=new StringBuffer(sqlpre);
			if(StringUtil.isNotEmpty(BgnDate)){
				sb.append(" AND DATE_FORMAT(createTime, '%Y%m%d')>="+BgnDate);
				request.setAttribute("BgnDate", BgnDate);
			}
			if(StringUtil.isNotEmpty(EndDate)){
				sb.append(" AND DATE_FORMAT(createTime, '%Y%m%d')<="+EndDate);
				request.setAttribute("EndDate", EndDate);
			}	
			String sql=null;
			Connection conn=null;
			try {
				conn=dbutil.getConnection();
				sql=sb.append(" ORDER BY createTime DESC").toString();
				List<Blog> blogList=blogDao.queryBlog(conn, sql);
				for(Blog blog:blogList){
					int num=commentDao.getCommentNumByBlogId(conn,blog.getBlogId());
					blog.setComments(num);
					//������
				}
				request.setAttribute("blogList", blogList);
				request.setAttribute("blogPage", "blogList.jsp");
				
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
			request.getRequestDispatcher("blog.jsp").forward(request, response); 
		}  
		
		 
		

	}
	
	private void showComents(HttpServletRequest request, HttpServletResponse response, String blogId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			Comment comment=new Comment();
			comment.setBlogId(Integer.parseInt(blogId));
			List<Comment> commentlst=commentDao.queryComment(conn, comment);
			for(Comment c:commentlst){
				User user=userDao.queryUserInfoById(conn, c.getUserId());
				c.setUserName(user.getUserName());
				//������
			}
			request.setAttribute("commentList", commentlst);
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

	private void showBlogDetail(HttpServletRequest request, HttpServletResponse response, String blogId) {
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			Blog blog=blogDao.getBlogById(conn, Integer.parseInt(blogId));
			blog.setComments(commentDao.getCommentNumByBlogId(conn,blog.getBlogId()));
			request.setAttribute("blog", blog);
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
		// TODO Auto-generated method stub

	}

	void showPanelContent(HttpServletRequest request, HttpServletResponse response,String userId){
		String sqlpre="SELECT * FROM blog WHERE status=1  and userId="+userId;
		String sql=null;
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			sql=sqlpre+"  ORDER BY views DESC LIMIT 0,5";  //�Ķ����а�
			List<Blog> myVblogList=blogDao.queryBlog(conn, sql);
			request.setAttribute("myVblogList", myVblogList);
			
			sql=sqlpre+" ORDER BY likes DESC  LIMIT 0,5";   //�Ƽ����а�
			List<Blog> myLblogList=blogDao.queryBlog(conn, sql);
			request.setAttribute("myLblogList", myLblogList);
			

			List<Blog> myCblogList=blogDao.queryOrderByCommentNum(conn,Integer.parseInt(userId),5);
			request.setAttribute("myCblogList", myCblogList);  //�������а�
			
			List<BlogType> blogNumOfType=blogDao.queryGroupByBlogType(conn, Integer.parseInt(userId));
			request.setAttribute("blogNumOfType", blogNumOfType);//���ͷ���
			
			User user=userDao.queryUserInfoById(conn, Integer.parseInt(userId));
			request.setAttribute("user", user);
			

			
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
