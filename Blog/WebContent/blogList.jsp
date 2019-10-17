<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  		 <c:forEach var="blog" items="${blogList}">
  		 	<div class="blogByDate">
				<div class="blogTitle">
					<span><fmt:formatDate value="${blog.createTime}" type="date" pattern="yyyy年MM月dd日"/></span>
				</div>
			<div class="blogItem">
  				<div class="title"><a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a></div>
  				<div class="abstract"><p><span>摘要:</span><span>${fn:substring(blog.content,0,200)}</span>&nbsp;<a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">阅读全文</a></p></div>
  				<div class="footer"><span>post @</span><fmt:formatDate value="${blog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;&nbsp;${blog.userName}&nbsp;&nbsp;评论(${blog.comments})&nbsp;&nbsp;阅读(${blog.views})</div>  		
  			</div>
  			</div>
  		 </c:forEach>