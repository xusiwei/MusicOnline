<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>用户登陆</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
		
		<div align="center">
			<s:form action="user_login" method="post" theme="simple">
				用户名：<s:textfield name="username" /> <s:fielderror fieldName="username" cssStyle="color:red" /> <p />
				密码：    <s:password name="password" /> <s:fielderror fieldName="password" cssStyle="color:red" /> <p />	
				<hr />
				<s:submit value="登录" />
				<s:a href="go_register">没有账号？</s:a>
				<s:token />
			</s:form>
		</div>
	</body>

</html>
