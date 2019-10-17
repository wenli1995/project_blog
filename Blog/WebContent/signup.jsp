<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客登陆</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/login.css">
<script>
function checkForm(){
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	var password2=document.getElementById("password2").value;
	if(username==""){
		document.getElementById("tip1").innerHTML="请输入用户名";
		return false;
	}
	if(password==""){
		document.getElementById("tip2").innerHTML="请输入密码";
		return false;
	}
	if(password2!=password){
		document.getElementById("tip3").innerHTML="密码不一致";
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
<form class="loginForm" action="Login?req=signup" method="post" onsubmit="return checkForm()">
	<p class="login_header">用户注册</p>
	<div class="login_text">用户名:</div>
	<input type="text" name="userName" id="username"/>&nbsp;<span id="tip1"></span><br/>
	<div class="login_text">密码:</div>
	<input type="password" name="password" id="password"/>&nbsp;<span id="tip2"></span><br/>
	<div class="login_text">确认密码:</div>
	<input type="password"  id="password2"/>&nbsp;<span id="tip3"></span><br/>
	<input type="submit" name="signup" value="注册" class="loginBtn"/>
	<div class="message">${msg}</div>
</form>
</body>
</html>