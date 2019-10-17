<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<form method="post" action="BlogEdit?userId=${loginUser.userId}&edtype=edit" onsubmit="return checkForm()">
		<div class="form-group blogTitle">
			<label>标题</label>
			<input type="text"  class="form-control" value="${edBlog.title}" name="title" id="title"/>
		</div>
		<div class="blogContent">
			<label>内容</label>
			<!-- 步骤二：使用ckeditor -->
			<textarea class="ckeditor" name="content" id="content">${edBlog.content}</textarea>	
		</div>
		<div class="form-group blogType">
			<label>类别</label>
			<input type="text" class="form-control" value="${edBlog.blogType}" name="blogtype" id="blogtype"/>
		</div>
		<div>
			<input type="hidden" name="blogId" value="${edBlog.blogId}"/>
			<!-- 修改操作时blogId会有值 -->
		</div>
		<div class="blogBtn">
			<input type="submit" name="oper" value="publish" id="btn"/>&nbsp;&nbsp;
			<input type="submit" name="oper"  value="save" id="btn"/>&nbsp;&nbsp;
			<input type="button" value="取消" id="btn" onclick="javascript:history.back()"/>
		</div>
		<div id="message">${msg}</div>
	</form>