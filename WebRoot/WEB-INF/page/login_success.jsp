<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>登陆成功</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
		
		<div align="center">
			<h1>登陆成功！</h1> <br/>
			用户名：<s:property value="username"/>  <br/>
			密码：<s:property value="password"/>  <br/>
			<a href="go_upload">上传歌曲</a>
			<a href="go_songs">我上传的歌曲</a>
		</div>
	</body>

</html>
