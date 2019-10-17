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
import com.java.dao.CommentDao;
import com.java.dao.UserDao;
import com.java.model.Blog;
import com.java.model.PagingInfo;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	DbUtil dbutil=new DbUtil();
	BlogDao blogDao=new BlogDao();
	UserDao userDao=new UserDao();
	CommentDao commentDao=new CommentDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		showNavContent(request,response);//显示列表面板的内容
		//根据action的不同分页显示不同的页面主体内容
		String  whichPage=request.getParameter("page");
		if(StringUtil.isEmpty(whichPage)){
			whichPage="1";
		}
		int pageSize=6;
		PagingInfo pageinfo=new PagingInfo(Integer.parseInt(whichPage),pageSize);
		
		String action=request.getParameter("action");
		String sql="";
		String servString="";
		if(StringUtil.isNotEmpty(action)){
			if(action.equals("showByViews")){
				sql="SELECT * FROM blog where status=1 ORDER BY views DESC";  // //mySQL不支持top语法
				servString="Home?action=showByViews&";
			}else if(action.equals("showByLikes")){
				sql="SELECT * FROM blog where status=1 ORDER BY likes DESC";
				servString="Home?action=showByLikes&";
			}	
		}else{
			sql="SELECT * FROM blog  where status=1 ORDER BY createTime DESC"; 
			servString="Home?";
		}
		showBlogs(request,response,sql,pageinfo);   
		//分页页面功能实现，显示分页链接栏
		int BlogNum=0;
		Connection conn=null;
		try {
			conn = dbutil.getConnection();
			BlogNum = blogDao.getBlogNum(conn,sql);
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
		String PaginationCode=getPaginationCode(BlogNum,pageinfo,servString);
		request.setAttribute("PaginationCode", PaginationCode);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private void showNavContent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			//左边面板显示的列表信息
			Connection conn=null;			
			try {
				conn=dbutil.getConnection();
				String sql="SELECT * FROM blog WHERE status=1 and createTime BETWEEN SUBDATE(LOCALTIME(),2)  AND LOCALTIME() ORDER BY views DESC LIMIT 0,10 ";
				List<Blog> viewsMoreblogList=blogDao.queryBlog(conn,sql);
				request.setAttribute("viewsMoreblogList", viewsMoreblogList);
				
				sql="SELECT * FROM blog WHERE status=1 and   createTime BETWEEN SUBDATE(LOCALTIME(),10)  AND LOCALTIME() ORDER BY likes DESC LIMIT 0,10 ";
				List<Blog> likesMoreblogList=blogDao.queryBlog(conn,sql);
				request.setAttribute("likesMoreblogList", likesMoreblogList);
				
				sql="SELECT * FROM USER ORDER BY points DESC LIMIT 0,20";
				List<User> userList=userDao.queryUser(conn,sql);
				request.setAttribute("userList", userList);			
				
				
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
	private void showBlogs(HttpServletRequest request,HttpServletResponse response,String sql,PagingInfo pageInfo){
		Connection conn=null;
		try {
			conn=dbutil.getConnection();
			List<Blog> blogList=blogDao.queryBlogForPage(conn, sql,pageInfo);
			for(Blog blog:blogList){
				int num=commentDao.getCommentNumByBlogId(conn,blog.getBlogId());
				blog.setComments(num);
				//评论数
			}
			request.setAttribute("blogList", blogList);
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
	
	private String getPaginationCode(int blogNum,PagingInfo pageInfo,String servString){
			StringBuilder code=new StringBuilder();
			int currentPage=pageInfo.getWhichPage();
			int pageSize=pageInfo.getSize();
			int pageNum=(blogNum%pageSize)>0?blogNum/pageSize+1:blogNum/pageSize;   //总分页数
			if(currentPage==1){
				code.append("<li  class='disabled'><a href='#'>上一页</a></li>");
			}else{
				code.append("<li><a href='"+servString+"page="+(currentPage-1)+"'>上一页</a></li>");
			}
			
			for(int i=currentPage-2;i<=currentPage+2;i++){
				//最多显示5个页码，当页面是1和pageNum时,这里需要特殊处理，否则会显示-1，0，1，2，3的情况；
				if(i<1||i>pageNum){
					continue;  //调出循环使得i继续加1
				}
				if(i==currentPage){
					code.append("<li  class='active'><a href='Home?page="+i+"'>"+i+"</a></li>");
				}else{
					code.append("<li><a href='"+servString+"page="+i+"'>"+i+"</a></li>");
				}			
			}
			if(currentPage==pageNum){
				code.append("<li class='disabled'><a href='#'>下一页</a></li>");
			}else{
				code.append("<li><a href='"+servString+"page="+(currentPage+1)+"'>下一页</a></li>");
			}
			
			return code.toString();

	}

}
