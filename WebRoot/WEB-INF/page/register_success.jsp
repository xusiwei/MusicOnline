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
		<title>注册成功</title>
	</head>

	<body >
		<div align="center">
			<s:include value="title.jsp"/>
			<h1>注册成功！</h1> <br/>
			用户名：<s:property value="username"/> 
			密码：<s:property value="password"/> 
			<s:a href="go_login">现在登陆</s:a>
		</div>
	</body>

</html>
