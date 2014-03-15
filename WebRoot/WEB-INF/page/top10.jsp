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
		<title>AHPU听吧</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
	 
	<div align="center"> 
		<hr/>
		<div align="center"> <!-- 播放次数最多 -->
			<h2>播放最多</h2> 
			<s:form action="song_addToList" theme="simple" method="post">
				<table border="0" width="50%">
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
							上传者
						</td>
						<td>
							播放次数
						</td>						
					</tr>

					<s:iterator value="#session.TOP10_POPULAR_SONGS" status="st">
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
								<s:property value="user.username" />
							</td>	
							<td>
								<s:property value="playcount" />
							</td>							
						</tr>
					</s:iterator>
				</table>
				<s:checkbox name="uniqueAdd">不重复</s:checkbox>
				<input type="submit" value="添加到播放列表"/> &nbsp; <input type="reset" value="取消"/>
				<s:token></s:token>
			</s:form>
		</div>	

		<hr/>	
		<div align="center"> <!-- 最新上传 -->
			<h2>最近新上传</h2> 
			<s:form action="song_addToList" theme="simple" method="post">
				<table border="0" width="50%">
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
							上传者
						</td>
						<td>
							上传时间
						</td>
					</tr>

					<s:iterator value="#session.TOP10_RECENTLY_SONGS" status="st">
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
								<s:property value="user.username" />
							</td>	
							<td>
								<s:property value="uploadTime" />
							</td>							
						</tr>
					</s:iterator>
				</table>
				<s:checkbox name="uniqueAdd">不重复</s:checkbox>
				<input type="submit" value="添加到播放列表"/> &nbsp; <input type="reset" value="取消"/>
				<s:token></s:token>
			</s:form>
		</div>		
		
		<div align="center"> <!-- 各种风格最受欢迎的 -->
			<s:iterator value="#session.TOP10_EACH_STYLE_SONG_LISTS" id="entry">
			<hr/>
				<h2><s:property value="key"/>&nbsp;TOP10</h2>
				<s:form action="song_addToList" theme="simple" method="post">
					<table border="0" width="50%">
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
						</tr>
	
						<s:iterator value="value" status="st">
							<s:if test="#st.odd">
								<s:set var="color" value="'#cccccc'" />
							</s:if>
							<s:else>
								<s:set var="color" value="'#eeeeee'" />
							</s:else>
	
							<tr bgcolor="<s:property value="color"/>">
								<td>
									<input type="checkbox" name="songIdList" value="<s:property value="value.id"/>"/>
									<s:property value="#st.count" />
									<!-- 
									<s:property value="id"/>
									-->								
								</td>
								<td>
									<a href="song_playSong?audioSrc=<s:property value="value.savepath"/>">
										<s:property value="name" />
									</a>
								</td>
								<td>
									<s:property value="singer" />
								</td>							
							</tr>
						</s:iterator>
					</table>
					<s:checkbox name="uniqueAdd">不重复</s:checkbox>
					<input type="submit" value="添加到播放列表"/> &nbsp; <input type="reset" value="取消"/>
					<s:token></s:token>
				</s:form>
			</s:iterator>
		</div>
		<hr/>
		
	</div>
	
	</body>
</html>
