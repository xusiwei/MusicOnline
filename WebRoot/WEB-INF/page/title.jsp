<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- by xu(xusiwei1236@163.com) -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>AHPU听吧</title>
	</head>

	<body>
		<div>
			<div align="left" style="float: left; width=auto;">
				<a href="top10">首页</a>
			</div>
			<div align="right">
				<s:if test="#session.LOGINED_USER==null">
					<a href="go_login">登陆</a>
					<a href="go_register">注册</a>
				</s:if>
				<s:else>
					欢迎&nbsp; ${LOGINED_USER.username}！&nbsp; 
					<a href="go_songs">我上传的歌曲</a>
					<a href="go_upload">上传歌曲</a>
					<a href="user_logout">安全退出</a> 
				</s:else>
			</div>
		</div>
		<br/>
		
		<!-- 搜索条 -->
		<div align="center"	style="margin-left: 10px;">
			<s:include value="searchbar.jsp"/>
		</div>
		
	</body>
</html>
