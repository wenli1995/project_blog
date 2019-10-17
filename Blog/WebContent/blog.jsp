<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/blog.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script>
	function clearForm(){
		var beginDate=document.getElementById("BgnDate").value="";
		var endDate=document.getElementById("EndDate").value="";
		//重置
	}
	
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row"  >
		<div class="col-md-12" id="blogHeader">
			<div class="blogTitle">
				<h3>${user.userName}</h3>
			</div>
			<div class="navigator">
				<ul>
					<li><a href="Home">博客园</a></li>
					<li><a href="myBlog?userId=${user.userId}">博客首页</a></li>
					<c:choose>
    					<c:when test="${loginUser==null}">
  							<li><a href="login.jsp">创作博客</a></li>
							<li><a href="login.jsp">博客管理</a></li>
    					</c:when>
   					 	<c:otherwise>
  							<li><a href="myBlog?userId=${user.userId}&action=addBlog">创作博客</a></li>
							<li><a href="myBlog?userId=${user.userId}&action=manageBlog">博客管理</a></li>
    					</c:otherwise>
					</c:choose>	

				</ul>
				<div class="blogInfo">
					<span>博客-6 </span>
					<span>评论-9 </span>&nbsp;
				</div>
			</div>

		</div>
	</div>
</div>
<div class="container-fluid">
  <div class="row">
		<div class="col-md-3">
		 <div class="blogPanel">
			<div class="List" id="userInfo">
				<ul>
					<li>昵称：${user.userName}</li>
					<li>园龄：1天</li>
					<li>签名：${user.motto}</li>
					<li>积分：${user.points}</li>
				</ul>
			</div>
			<div class="queryForm">
				<div class="list_header">查询</div>
				<form method="post" action="myBlog?userId=${loginUser.userId}">
					<div>开始日期：<input type="input" name="BgnDate" value="${BgnDate}" id="BgnDate"/></div>
					<div>结束日期：<input type="input" name="EndDate" value="${EndDate}" id="EndDate"/></div>
					<div><input type="submit" value="查询">&nbsp;<input type="button" value="重置" onclick="clearForm()"></div>	
				</form>

			</div>
			<div  class="List" id="classifyInfo"> 
				<div class="list_header">博客分类</div>
				<ul>
					<c:forEach var="blogType" items="${blogNumOfType}">
						<li><a href="myBlog?userId=${user.userId}&action=showBlogListByType&blogType=${blogType.typeName}">${blogType.typeName}</a><span>(${blogType.blogNum})</span></li>
					</c:forEach>
				</ul>
			</div>
			<div class="List"">
				<div class="list_header">阅读排行榜</div>
				<ul>
					<c:forEach var="blog" items="${myVblogList}">
						<li><a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a><span>(${blog.views})</span></li>
					</c:forEach>
				</ul>
			</div>
			<div class="List"">
				<div class="list_header">评论排行榜</div>
				<ul>
					<c:forEach var="blog" items="${myCblogList}">
						<li><a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a><span>(${blog.comments})</span></li>
					</c:forEach>
				</ul>
			</div>
			<div class="List"">
				<div class="list_header">推荐排行榜</div>
				<ul>
					<c:forEach var="blog" items="${myLblogList}">
						<li><a  href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a><span>(${blog.likes})</span></li>
					</c:forEach>
				</ul>
			</div>
			</div>
		</div>
		<div class="col-md-9">
			<jsp:include page="${blogPage}"/>
	  </div>
  </div>
</div>
</body>

</html>