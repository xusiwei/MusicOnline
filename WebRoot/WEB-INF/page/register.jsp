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
	        obj.src="imageAction.action?d="+shijian;
   		}
  		</script>
  		<title>用户注册</title>
	</head>

	<body>
		<s:include value="title.jsp"/>
		<h1 align="center">
			用户注册
		</h1>
		<div align="center">
			<br>
			<s:form action="user_register" theme="simple" method="post">
				<table border="0" style="width: 501px; height: 342px;">
					<tr>
						<td align="right">账号：	</td>

						<td width="16">
							<s:textfield name="username"></s:textfield>
						</td>

						<td width="40" align="left">
							<s:fielderror fieldName="username" cssStyle="color:red" theme="simple"></s:fielderror>
						</td>
					</tr>

					<tr>
						<td align="right">
							密码：
						</td>
						<td>
							<s:password name="password"></s:password>
						</td>

						<td>
							<s:fielderror fieldName="password" cssStyle="color:red"></s:fielderror>							
						</td>
					</tr>

					<tr>
						<td align="right">
							重复密码：
						</td>
						<td>
							<s:password name="password2"></s:password>
						</td>
						<td>
							<s:fielderror fieldName="password2" cssStyle="color:red"></s:fielderror>
						</td>
					</tr>

					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<s:radio name="sex" list="{'男','女','保密'}" value="保密" theme="simple"></s:radio>
						</td>
						<td>
							<s:fielderror fieldName="sex" cssStyle="color:red"></s:fielderror>
						</td>
					</tr>

					<tr>
						<td align="right">
							电子邮箱：
						</td>

						<td>
							<s:textfield name="email"></s:textfield>
						</td>

						<td>
							<s:fielderror fieldName="email" cssStyle="color:red"></s:fielderror>
						</td>
					</tr>
					
					<tr>
						<td align="right" colspan="2">
							<s:checkbox name="agreeLicense"></s:checkbox>
							我已阅读并同意<a href="LICENSE.txt">用户条款</a>？
						</td>					
						<td>
							<s:fielderror fieldName="agreeLicense" cssStyle="color:red"></s:fielderror>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							验证码：
						</td>
						<td>
							<s:textfield name="validateNum" cssStyle="width=4"></s:textfield>
						</td>
						<td>
							<img src="imageAction" align="left" alt="点击图片"
								onClick="refresh(this)" />
							<div left>
							<s:fielderror fieldName="validateNum" cssStyle="color:red"></s:fielderror>
							</div>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<s:submit value="提交"></s:submit>
							<s:reset value="重置"></s:reset>
						</td>
					</tr>
					<s:token />
				</table>
			</s:form>
		</div>
	</body>
</html>
