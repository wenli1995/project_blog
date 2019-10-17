<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	function deleteDiary(title){
		boolean agree=confirm("确定删除博客"+title+"吗?");
		if(agree==true){
		}
	}
</script>
<table class="table table-striped">
	<tr>
		<th>标题</th>
		<th>发布状态</th>
		<th>评论数</th>
		<th>阅读数</th>
		<th>操作</th>
		<th>操作</th>
	</tr>
	<c:forEach var="mblog" items="${mblogList}">
		<tr>
			<td><a class="blogTitle" href="myBlog?userId=${mblog.userId}&action=showBlogDetial&blogId=${mblog.blogId}">${mblog.title}</a></td>
			<td>
			<c:choose>
				<c:when test="${mblog.status==1}">
					发布
				</c:when>
				<c:otherwise>
					未发布
				</c:otherwise>
			</c:choose>
			</td>
			<td>${mblog.comments}</td>
			<td>${mblog.views}</td>
			<td><a href="myBlog?userId=${loginUser.userId}&edBlogId=${mblog.blogId}&action=addBlog">编辑</a></td>
			<td><a href="BlogEdit?userId=${loginUser.userId}&edtype=delete&blogId=${mblog.blogId}">删除</a></td>
		</tr>
	</c:forEach>	
	
</table>