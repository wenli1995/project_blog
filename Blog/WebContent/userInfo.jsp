<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/user.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script>
	function showModifyPanel(){
		document.getElementById("modify").style.visibility="visible";
	}
	function hideModifyPanel(){
		document.getElementById("modify").style.visibility="hidden";
	}
</script>
</head>
<body>
<div class="container-fluid">
<div class="row">
<div class="col-md-12" id="head">
	<label class="welcome">欢迎你，${user.userName}</label>
	<label class="homepage"><a href="Home">博客园</a></label>
</div>
</div>
 <div class="row">
 	<div class="col-md-2">
 	</div>
 	<div class="col-md-8">
 		<div class="info">
 		  <img src="./images/userImg.png"/> 	
 		  <div class="textInfo">
 		    <div><label>昵称：</label>${user.userName}</div>
 		    <div><label>圆龄：</label>23天</div>
 		    <div><label>签名：</label>${user.motto}</div>
 		    <div><label>积分：</label>${user.points}</div>
 		   <button type="button" class="btn btn-default" onclick="showModifyPanel()">
  			<span class="glyphicon glyphicon-edit"></span>&nbsp;修改资料
		  </button>
 		</div>
 	</div>
 	<div id="modify" style="visibility: hidden;">
 		<div class="title">个人资料修改</div>
 		<form method="post" action="User?userId=${user.userId}&action=modifyInfo">
 			<label>新签名：</label><input type="text" class="form-control" value=${user.motto} name="motto"/>
			<button type="submit" class="btn btn-default" onclick="hideModifyPanel()">
  				<span class="glyphicon glyphicon-ok"></span>&nbsp;保存
			</button>
			<button type="submit" class="btn btn-default" onclick="hideModifyPanel()">
  				<span class="glyphicon glyphicon-remove"></span>&nbsp;取消
			</button>
 		</form>
	</div>
	</div>
 	<div class="col-md-2">
 	
 	</div>
 </div>
</div>

</body>
</html>