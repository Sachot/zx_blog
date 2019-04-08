<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin Regist</title>
<link rel="stylesheet" href="./css/reg.css">
</head>
<body>
<div class="reg">
		<div class="header">
			<h1>
				<a href="./adminlogin.jsp">admin登录</a> <a href="./index.jsp">主页</a>
			</h1>
		</div>
		<!-- 
			文件上传的条件
			* 表单必须是post提交方式
			* 表单中必须有文件上传项，文件上传项必须有name属性和值
			* 表单的enctype属性必须设置为multipart/form-data
		 -->
		<%  //显示报错信息
			String msg = "";
			if(request.getAttribute("msg")!=null){
				msg = (String)request.getAttribute("msg");
			}
		%>
		<h3><font color="red"><%= msg %></font></h3>
		<form action="/maven.spring_test.zx_blog/AdminRegistServlet" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="td1">用户名</td>
					<td><input type="text" class="input1" name="username"></td>
				</tr>
				<tr>
					<td class="td1">密码</td>
					<td><input type="password" class="input1" name="passwd"></td>
				</tr>
				<tr>
					<td class="td1">上传头像</td>
					<td><input type="file" id="photo" name="upload"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn-red">
							<input type="submit" value="注册" id="reg-btn">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>