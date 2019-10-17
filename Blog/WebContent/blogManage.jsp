<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理</title>
<!-- 步骤一：引入ckeditot的js脚本 -->
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/blogEdit.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script>
	function  checkForm(){
		var title=document.getElementById("title").value;
		var content=CKEDITOR.instances.content.getData();
		var type=document.getElementById("blogtype").value;
		var msg=document.getElementById("message");
		if(title==null||title==""){
			alert("请输入博客标题")
			//msg.innerHTML="请输入博客标题";
			return false;
		}
		//--步骤三：关于ckeditor判断是否为空
		if(content==null||content==""){
			alert("请输入博客内容")
			//msg.innerHTML="请输入博客内容";
			return false;
		}
		if(type==null||type==""){
			alert("请输入博客类别")
			//msg.innerHTML="请输入博客类别";
			return false;
		}
		return true;
	}

</script>
</head>
<body>
<div class="container-fluid">
<div class="row">
<div class="col-md-12" id="head">
	<label><a href="Home">博客园</a></label>
</div>
</div>
<div class="row">
<div class="col-md-2" id="showpanel">
	<div class="List">
		<div class="listTitle">分 类</div>
		<ul>
			<c:forEach var="blogType" items="${blogNumOfType}">
				<li><a href="myBlog?userId=${loginUser.userId}&action=manageBlog&blogType=${blogType.typeName}">${blogType.typeName}</a><span>(${blogType.blogNum})</span></li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="col-md-10"  id="editpanel">
	<div class="menuTitle">
		<label>博客管理</label>
	</div>
	<div>
		<jsp:include page="${managePage}"/>
	</div>

</div>

</div>
</div>



</body>
</html>