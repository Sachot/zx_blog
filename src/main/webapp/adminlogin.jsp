<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin Login</title>
<link rel="stylesheet" href="./css/login.css">
</head>
<body>
	<div class="login">
		<div class="header">
			<h1>
				<a href="./guestlogin.jsp">guest登录</a> <a href="./guestregist.jsp">guest注册</a>
				<a href="./adminlogin.jsp">admin登录</a>
			</h1>

		</div>
		<%
		String msg = "";
		if(request.getAttribute("msg")!=null){     
			msg = (String)request.getAttribute("msg");
		}
		%>
		
		<h3><font color="red"><%=msg %></font></h3>
		<form action="/zx_blog/AdminLoginServlet" method="post">
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
					<td colspan="2">
						<div class="btn-red">
							<input type="submit" value="登录" id="login-btn">
						</div>
					</td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>