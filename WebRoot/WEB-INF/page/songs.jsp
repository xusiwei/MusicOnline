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
		<s:include value="title.jsp"/>
		
		<div align="center">
			<h1>我上传的歌曲</h1>
			<s:form action="song_addToList" theme="simple" method="post">
				<table border="0" width="63%">
					<tr bgcolor="#7f7fff">
						<td>
							播放
						</td>
						<td>
							歌名
						</td>
						<td>
							歌手
						</td>
						<td>
							风格
						</td>
						<td>
							上传时间
						</td>
					</tr>

					<s:iterator value="#session.MY_SONG_LIST" status="st">
						<s:if test="#st.odd">
							<s:set var="color" value="'#cccccc'" />
						</s:if>
						<s:else>
							<s:set var="color" value="'#eeeeee'" />
						</s:else>

						<tr bgcolor="<s:property value="color"/>">
							<td>
								<input type="checkbox" name="songIdList" value="<s:property value="id"/>"/>
								<s:property value="#st.count" />
								<!-- 
								<s:checkbox name="selectList" value="#st.count"></s:checkbox>
								<s:property value="id"/>
								-->								
							</td>
							<td>
								<a href="song_playSong?audioSrc=<s:property value="savepath"/>">
									<s:property value="name" />
								</a>
							</td>
							<td>
								<s:property value="singer" />
							</td>
							<td>
								<s:property value="style" />
							</td>
							<td>
								<s:date name="uploadTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</s:iterator>
				</table>
				<div align="center">
					<s:fielderror fieldName="hint" cssStyle="color:red">选择歌曲后点击播放</s:fielderror>
				</div>
				<s:checkbox name="uniqueAdd">不重复</s:checkbox>
				<input type="submit" value="添加到播放列表"/> &nbsp; <input type="reset" value="取消"/>
				<s:token></s:token>
			</s:form>
		</div>	
	</body>
</html>
