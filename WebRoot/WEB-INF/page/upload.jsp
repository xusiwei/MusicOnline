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
		<script type="text/javascript">
    	function refresh(obj) {
	        var shijian = new Date().getTime();
	        obj.src="imageAction?d="+shijian;
   		}
  		</script>
  		<title>上传歌曲页</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
		<div align="center">
			<h1>上传歌曲</h1>  <br/>
			<s:form action="song_upload" theme="simple" method="post" enctype="multipart/form-data">
				<table width="443" border="0" height="263">
					<tbody>
						<tr>
							<td>
								歌曲文件：
							</td>
							<td colspan="2">
								<s:file name="song"></s:file>							
								<s:fielderror fieldName="song" cssStyle="color:red" /> 
							</td>
						</tr>
						<tr>
							<td>
								歌曲名称：
							</td>
							<td>
								<s:textfield name="name" required="true"></s:textfield> 
							</td>
							<td>
								 <s:fielderror fieldName="name" cssStyle="color:red" />
							</td>
						</tr>
						<tr>
							<td>
								演唱歌手：
							</td>
							<td>
								<s:textfield name="singer" required="true"></s:textfield> 
							</td>
							<td>
								<s:fielderror fieldName="singer" cssStyle="color:red" /> 
							</td>
						</tr>
						<tr>
							<td>
								歌曲风格：
							</td>
							<td>
								<s:textfield name="style" required="true"></s:textfield> 
							</td>
							<td>
								 <s:fielderror fieldName="style" cssStyle="color:red" />
							</td>
						</tr>
						<tr>
							<td>
								歌曲简介： 
							</td>
							<td>
								 <s:textarea name="description" rows="3" cols="17" required="true"></s:textarea> 
							</td>
							<td>
								 <s:fielderror fieldName="description" cssStyle="color:red" />
							</td>
						</tr>
						<tr>
							<td>
								验证码：
							</td>
							<td>
								<s:textfield name="validateNum"></s:textfield>
							</td>
							<td>
								<div>
									<img src="imageAction" align="left" onclick="refresh(this);" />
									<s:fielderror fieldName="validateNum" cssStyle="color:red" ></s:fielderror>
								</div>
							</td>
						</tr>
					</tbody>
				</table> 
					看不清，请点图片 <br/>
					<s:submit value="上传"></s:submit>
					<s:reset value="重置"></s:reset>
				<s:token />
			</s:form>
		</div>
	</body>
</html>
