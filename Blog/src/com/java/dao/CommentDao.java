package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Comment;
import com.java.util.DateUtil;

public class CommentDao {
	public int getCommentNumByBlogId(Connection conn,int blogId) throws Exception{
		String sql="SELECT * FROM COMMENT WHERE blogId=?; ";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, blogId);
		ResultSet rs=stat.executeQuery();
		int num=0;
		while(rs.next()){
			num++;
		}
		return num;
		
	}
	
	public List<Comment> queryComment(Connection conn,Comment comment) throws Exception{
		String sql="select * from comment where blogId=? order by reviewTime";
		ArrayList<Comment> commentList=new ArrayList<>();
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, comment.getBlogId());
		ResultSet rs=stat.executeQuery();
		while(rs.next()){
			Comment qryComment=new Comment();
			qryComment.setBlogId(rs.getInt("blogId"));
			qryComment.setCommentId(rs.getInt("commentId"));
			qryComment.setUserId(rs.getInt("userId"));
			qryComment.setContent(rs.getString("content"));
			qryComment.setReviewTime(DateUtil.String2Date(rs.getString("reviewTime"),"yyyy-MM-dd HH:mm:ss"));
			commentList.add(qryComment);
		}
		return commentList;
		
	}
	
	public int addComment(Connection conn,Comment comment) throws Exception{
		String sql="insert into comment(commentId,userId,blogId,content,reviewTime) values(null,?,?,?,now())";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1, comment.getUserId());
		stat.setInt(2, comment.getBlogId());
		stat.setString(3, comment.getContent());
		int num=stat.executeUpdate();
		return num;
	}
}
