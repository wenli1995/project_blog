<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的博客</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/home.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	window.onload=function(){
		//var currentPath=location.pathname;   /*获取的是连接地址/Blog/Home
		var paramsObj=location.search;       /*获取的是链接参数 ？action=action=showByLikes*/
		var navAs=document.getElementById("navUrl").getElementsByTagName("a");
		if(paramsObj!=null&&paramsObj!=""){
			var paramsStr=paramsObj.substring((paramsObj.indexOf("?")+1));   /*截取问号之后的字符串action=showByLikes*/
			var paramsArr=new Array();
			paramsArr=paramsStr.split("&");  /*多个参数会以&分隔开*/
			var actionParam=paramsArr[0].split("=")[1];    /*获取第一个参数action=showByLikes并以"="号分离出showByLikes*/
			var find=0;	
			for(var i=0;i<navAs.length;i++){
				if(navAs[i].href.indexOf(actionParam)!=-1){
					navAs[i].parentNode.className="active";
					find=true;
					break;    /*遍历导航标签的所有链接找到包含当前url的actio参数值的，找到就调出循环*/
				}
			}
			if(!find){
				navAs[0].parentNode.className="active";//默认首页标签是active状态
			}
		}else{
			navAs[0].parentNode.className="active";
		}
		
	};  //脚本用于点击不同链接是导航栏标签背景色的变化
</script>
</head>
<body>
<div class="container-fluid">
<!--  container-fluid表示占据屏幕100%-->
<jsp:include page="/common/head.jsp"/>
</div>
<div class="container-fluid">
<!--  container-fluid表示占据屏幕100%-->
 <div class="row">
 <div class="col-md-3">
 	<div class="navPanel">
 	<div class="data_list">
 		<div class="list_header"><a  href="Home?action=showByViews">48小时阅读排行榜</a></div>
 		<div class="list">
 			<ul>
 				<c:forEach var="vblog" items="${viewsMoreblogList}">
 					<li><a href="myBlog?userId=${vblog.userId}&action=showBlogDetial&blogId=${vblog.blogId}">${fn:substring(vblog.title,0,20)}</a></li>
 				</c:forEach>
 			</ul>
 		</div>
 	</div>
 	 <div class="data_list">
 	 	<div class="list_header"><a  href="Home?action=showByLikes">10天推荐排行榜</a></div>
 		<div class="list">
 			<ul>
 				<c:forEach var="lblog" items="${likesMoreblogList}">
 					<li><a href="myBlog?userId=${lblog.userId}&action=showBlogDetial&blogId=${lblog.blogId}">${fn:substring(lblog.title,0,20)}</a></li>
 				</c:forEach>
 			</ul>
 		</div>
 	</div>
 	 <div class="user_data_list">
 	 	<div class="user_list_header"><a href="#">博客排行</a></div>
 		<div class="user_list">
 			<ul>
	 			<c:forEach var="user" items="${userList}">
 					<li><a href="myBlog?userId=${user.userId}">${user.userName}</a></li>
 				</c:forEach>
 			</ul>
 		</div>
 	</div>
   </div>  <!-- navPanel -->
 </div>
  <div class="col-md-9">
    <div class="blogNav">
   	 <ul class="nav nav-tabs" id="navUrl">
  		<li role="presentation"><a href="Home">首页</a></li>
  		<li role="presentation"><a href="Home?action=showByViews">阅读排行榜</a></li>
 		<li role="presentation"><a href="Home?action=showByLikes">推荐排行榜</a></li>
	</ul>
    </div>
  	<div class="blogContent">
		<c:forEach var="blog" items="${blogList}">
		  <div class="container blogItem">
		   <div class="row">
			<div class="col-md-1 digg">
    			<div class="diggit"> 
					<span class="diggnum">&nbsp;&nbsp;&nbsp;${blog.likes}</span>   <!-- 推荐数 -->
				</div>
				<div class="clear"></div>   <!-- 中间隔开的空白 -->
				<div class="digg_tip"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;推荐</div>
				<div class="digg_text" id="message"></div>
			 </div>
			  <div class="col-md-11 item_body">
  				<div class="title"><a href="myBlog?userId=${blog.userId}&action=showBlogDetial&blogId=${blog.blogId}">${blog.title}</a></div>
  				<div class="abstract"><span>${fn:substring(blog.content,0,200)}</span>...</div>
  				<div class="footer"><a>${blog.userName}</a>&nbsp;&nbsp;<fmt:formatDate value="${blog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;评论(${blog.comments})&nbsp;&nbsp;<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>&nbsp;阅读(${blog.views})</div>  
  				<!-- 注意格式化串一定要正确 -->				
  			 </div>
  			</div>
  		  </div>
		</c:forEach>
  	</div>
  	<div class="blogPagination">
  		 <nav aria-label="Page navigation">
			<ul class="pagination">
				${PaginationCode}
			</ul>
		</nav>
  	</div>
 </div>
 </div>  <!--row-->
</div>  <!--container-fluid-->
</body>
</html>