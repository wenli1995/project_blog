<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/blog.css">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
	function diggit(blogId){
		var xmlhttp;
		if(window.XMLHttpRequest){
			xmlhttp=new XMLHttpRequest();
		}else{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.status==200&&xmlhttp.readyState==4){
				//alert(xmlhttp.responseText);
				var dataObj=eval("("+xmlhttp.responseText+")");
				//alert(document.getElementById("likesNum").innerHTML);
				document.getElementById("likesNum").innerHTML=dataObj.likes;			
			}
		};
		xmlhttp.open("post","Digg?action=addLikes&blogId="+blogId,true);
		xmlhttp.send();
	}
</script>
</head>
<body>
<div class="blogDetail">
  <div class="content">
	<h2>${blog.title}</h2>
	<p>
		${blog.content}
	</p>
    <div  class="info">
    	<span>分类：${blog.blogType}</span>
    	 <button class="btn btn-default btn-sm" id="digg" type="button" onclick="diggit(${blog.blogId})"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>推荐<label id="likesNum">${blog.likes}</label></button>
    	
    </div>
  </div>  <!-- content -->
  <div class="footer">
	  <span>post @</span><fmt:formatDate value="${blog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;&nbsp;${blog.userName}&nbsp;&nbsp;评论(${blog.comments})&nbsp;&nbsp;阅读(${blog.views})  
  </div>  
</div>

 <div class="commentList">
  	<div class="list_header"><span>评论列表</span></div>
  	<div class="list_data">
  	<c:forEach var="comment" items="${commentList}" varStatus="status">
  	   <div class="list_item">
  	  	 <div>#${status.index+1}楼&nbsp;<fmt:formatDate value="${comment.reviewTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;${comment.userName}</div>
  	  	 <div class="commentContent">
  	   		${comment.content}
  	   	</div>
  	   </div>
  	</c:forEach>

  	</div>
 
  </div>	
  
  <div class="commentAdd">
  	<c:choose>
    	<c:when test="${loginUser==null}">
			<div><label>注册用户登录后才能发表评论，请先[<a href="login.jsp">登录</a>]</label></div>
    	</c:when>
   		 <c:otherwise>
  			<div class="commentheader"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;发表评论</div>
  			<form method="post" action="Comment?action=addComment&userId=${blog.userId}&blogId=${blog.blogId}">
  				 <div class="divblock">
   					 <span>昵称:</span>
   	 				 <input type="text" class="form-control" value=${loginUser.userName} name="nickname" id="nicknane"/>
   				</div>
  			    <div class="divblock">
   				 	<div><span>评论内容:</span></div>
   	 			 	<textarea class="form-control" rows="8" name="comment"></textarea>
   			    </div>
  			    <div class="divblock">
   					<input class="btn btn-default" type="submit" value="提交评论"/>
   			    </div>
   			</form>
    	</c:otherwise>
	</c:choose>	
  
  </div>
  </body>
  </html>
  