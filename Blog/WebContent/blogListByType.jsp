<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="blogOrdByType">
<div class="list_header">
	<span>当前分类：${blogType}</span>
</div>
<ul>
	<c:forEach var="blog" items="${tBlogList}">
		<li><a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a>&nbsp;${blog.userName} <fmt:formatDate value="${blog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> 阅读:${blog.views} 评论:${blog.comments}</li>
	</c:forEach>
</ul>
</div>