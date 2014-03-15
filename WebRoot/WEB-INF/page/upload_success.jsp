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
		<title>上传成功页</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
		<div align="center">
			<h1>上传成功！</h1>  <br/>
				歌曲文件：<s:property value="songFileName"/> <br/>
				歌曲名称：<s:property value="name"/>  <br/>
				演唱歌手：<s:property value="singer"/> <br/>
				歌曲风格：<s:property value="style"/> <br/>
				<div dir="ltr">
					歌曲简介： <s:property value="description"/> 
				</div>
			<a href="song_playSong?audioSrc=<s:property value="serverFilePath"/>">现在播放</a>
			<a href="go_upload">继续上传</a>
			<a href="go_songs">我上传的全部歌曲</a>
			<!-- -->
		</div>
	</body>
</html>
