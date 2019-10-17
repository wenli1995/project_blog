<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jsp标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<div class="container-fluid">
  <div class="row">
   		<div class="col-md-12 info">
			<p>代码改变世界<p>
			<div class="link">
				<c:choose>
    				<c:when test="${loginUser==null}">
						<span>[<a href="login.jsp">登录</a>&nbsp;<a href="signup.jsp">注册</a>]</span>
    				</c:when>
   					 <c:otherwise>
  						<span><a href="User?userId=${loginUser.userId}">${loginUser.userName}</a>.<a href="myBlog?userId=${loginUser.userId}">我的博客</a>.<a href="Login?req=logout" >退出</a></span>
    				</c:otherwise>
				</c:choose>			
			</div>
		</div>
  </div>
</div>
<!-- 每一个div都是一个流式布局 -->
<div class="container-fluid">
  <div class="row">
   		<div class="col-md-12 logo">
			<img src="./images/logoImg.png"/>
		</div>
  </div>
</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12 lead">
			<ul>
  				<li><a href="Home">博客</a></li>
  				<li><a href="#">新闻</a></li>
  				<li><a href="#">博问</a></li>
  				<li><a href="#">闪存</a></li>
  				<li><a href="#">小组</a></li>
  				<li><a href="#">收藏</a></li>
  				<li><a href="#">招聘</a></li>
  				<li><a href="#">班级</a></li>
  				<li><a href="#">找找看</a></li>
			</ul>
      	</div>
     </div>
</div>



