package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Blog;
import com.java.model.BlogType;
import com.java.model.PagingInfo;
import com.java.util.DateUtil;

public class BlogDao {
	public List<Blog> queryBlog(Connection conn,String sql) throws Exception{
		ArrayList<Blog> blogList=new ArrayList<>();
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			Blog blog=new Blog();
			blog.setBlogId(rs.getInt("blogId"));  //根据列名获取列数据
			blog.setBlogType(rs.getString("blogType"));
			blog.setContent(rs.getString("content"));
			blog.setLikes(rs.getInt("likes"));
			blog.setTitle(rs.getString("title"));
			blog.setUserId(rs.getInt("userId"));
			blog.setUserName(rs.getString("userName"));  
			blog.setViews(rs.getInt("views"));
			blog.setStatus(rs.getInt("status"));
			blog.setCreateTime(DateUtil.String2Date(rs.getString("createTime"),"yyyy-MM-dd HH:mm:ss"));  //将数据库日期类型转换成java日期类型
			blogList.add(blog);
		}
		return blogList;
		
	}
	
	//分页查询功能
	public List<Blog> queryBlogForPage(Connection conn,String sql,PagingInfo pageInfo) throws Exception{
		int start=(pageInfo.getWhichPage()-1)*pageInfo.getSize();
		int end=pageInfo.getSize();
		String qrySql=sql+" limit ?,?";
		PreparedStatement stat=conn.prepareStatement(qrySql);
		stat.setInt(1, start);
		stat.setInt(2,end);
		ResultSet rs=stat.executeQuery();
		ArrayList<Blog> blogList=new ArrayList<>();
		while(rs.next()){
			Blog blog=new Blog();
			blog.setBlogId(rs.getInt("blogId"));  //根据列名获取列数据
			blog.setBlogType(rs.getString("blogType"));
			blog.setContent(rs.getString("content"));
			blog.setLikes(rs.getInt("likes"));
			blog.setTitle(rs.getString("title"));
			blog.setUserId(rs.getInt("userId"));
			blog.setUserName(rs.getString("userName"));  
			blog.setViews(rs.getInt("views"));
			blog.setStatus(rs.getInt("status"));
			blog.setCreateTime(DateUtil.String2Date(rs.getString("createTime"),"yyyy-MM-dd HH:mm:ss"));  //将数据库日期类型转换成java日期类型
			blogList.add(blog);
		}
		return blogList;
		
	}
	
	//添加博客
	public int addBlog(Connection conn,Blog blog) throws Exception{
		String sql="insert into blog(blogId,userId,userName,title,content,blogType,createTime,views,likes,status) values(null,?,?,?,?,?,now(),0,0,?)";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1,blog.getUserId());
		stat.setString(2, blog.getUserName());
		stat.setString(3, blog.getTitle());
		stat.setString(4, blog.getContent());
		stat.setString(5, blog.getBlogType());
		stat.setInt(6, blog.getStatus());
		int  num=stat.executeUpdate();
		return num;
	}
	//修改博客
	public int modifyBlog(Connection conn,Blog blog) throws Exception{
		String sql="update blog set title=?,content=?,blogType=?,status=? where blogId=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1, blog.getTitle());
		stat.setString(2, blog.getContent());
		stat.setString(3, blog.getBlogType());
		stat.setInt(4, blog.getStatus());
		stat.setInt(5, blog.getBlogId());
		return stat.executeUpdate();
	}
	//删除博客
	public int deleteBlog(Connection conn,int blogId) throws Exception{
		String sql="delete from blog where blogId=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1,blogId);
		return stat.executeUpdate();
	}
	//更新博客阅读数
	public int updateBlogViews(Connection conn,Blog blog) throws Exception{
		int views=blog.getViews();
		int blogId=blog.getBlogId();
		String sql="update blog set views=? where blogid=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, views);
		stat.setInt(2, blogId);
		return stat.executeUpdate();
		
	}
	//更新博客推荐数
	public int updateBlogLikes(Connection conn,Blog blog) throws Exception{
		int likes=blog.getLikes();
		int blogId=blog.getBlogId();
		String sql="update blog set likes=? where blogid=?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, likes);
		stat.setInt(2, blogId);
		return stat.executeUpdate();
		
	}
	//展示博客信息，按评论数多寡排序
	public List<Blog> queryOrderByCommentNum(Connection conn,int userId,int num) throws SQLException{
		ArrayList<Blog> blogList=new ArrayList<>();
		String sql="SELECT a.userId,a.blogId,a.title,COUNT(commentId) commentnum FROM blog a ,COMMENT b WHERE a.blogId=b.blogId   AND a.userId=?   and status=1 GROUP BY a.blogId ORDER BY commentnum DESC LIMIT 0,?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, userId);
		stat.setInt(2, num);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			Blog qryblog=new Blog();
			qryblog.setUserId(rs.getInt("userId"));
			qryblog.setBlogId(rs.getInt("blogId"));  //根据列名获取列数据
			qryblog.setTitle(rs.getString("title"));
			qryblog.setComments(rs.getInt("commentnum"));
			blogList.add(qryblog);
		}
		return blogList;
	}
	
	//展示博客类别信息，按该类别下博客数量排序
	public List<BlogType> queryGroupByBlogType(Connection conn,int userId) throws Exception{
		ArrayList<BlogType> blogTypeList=new ArrayList<>();
		String sql="SELECT blogType,COUNT(blogId) blognum FROM blog WHERE  userId=? and status=1 GROUP BY blogType ORDER BY blognum DESC;";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, userId);
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			BlogType qryblogType=new BlogType();
			qryblogType.setTypeName(rs.getString("blogType"));
			qryblogType.setBlogNum(rs.getInt("blognum"));
			blogTypeList.add(qryblogType);
		}
		return blogTypeList;
	}
	//获取总记录数
	public int getBlogNum(Connection conn,String sql) throws SQLException{
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		int blogNum=0;
		while(rs.next()){
			blogNum++;
		}
		return blogNum;
		
	}
	
	public Blog getBlogById(Connection conn,int blogId) throws Exception{
		String sql="select * from blog where blogId="+blogId;
		Blog blog=new Blog();
		PreparedStatement stat=conn.prepareStatement(sql);
		ResultSet rs=stat.executeQuery();
		if(rs.next()){
			blog.setBlogId(rs.getInt("blogId"));  //根据列名获取列数据
			blog.setBlogType(rs.getString("blogType"));
			blog.setContent(rs.getString("content"));
			blog.setLikes(rs.getInt("likes"));
			blog.setTitle(rs.getString("title"));
			blog.setUserId(rs.getInt("userId"));
			blog.setUserName(rs.getString("userName"));  
			blog.setViews(rs.getInt("views"));
			blog.setStatus(rs.getInt("status"));
			blog.setCreateTime(DateUtil.String2Date(rs.getString("createTime"),"yyyy-MM-dd HH:mm:ss"));
		}
		return blog;

	}
	
}
