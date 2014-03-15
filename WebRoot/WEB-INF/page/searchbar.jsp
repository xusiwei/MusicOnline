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
	</head>

	<body>
		<div align="right">
			<s:form action="search" method="post">
				匹配方式：
				<input name="way" type="radio" value="head" />
				前方
				<input name="way" type="radio" value="midd" />
				中间&nbsp;
				
				<input name="value" type="text" />
				<input type="submit" />
				<br />
				
				搜索方式：
				<input name="mode" type="radio" value="name" />
				歌名
				<input name="mode" type="radio" value="singer" />
				歌手
				<input name="mode" type="radio" value="description" />
				简介
				<input name="mode" type="radio" value="all" />
				全部 &nbsp;&nbsp;&nbsp;&nbsp;
				<br />
				<s:token/>
			</s:form>
		</div>
	</body>
</html>
