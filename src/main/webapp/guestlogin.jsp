    <%@page import="utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Guest Login</title>
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
			String username="";
		    String passwd="";
			if(session.getAttribute("username")!=null){
				username = (String)session.getAttribute("username");
			}
			
			// 获得从客户端携带过来的所有的Cookie
			Cookie[] cookies = request.getCookies();
			// 从Cookie的数组中查找指定名称的Cookie
			Cookie usernameCookie = CookieUtils.findCookie(cookies, "username");
			if(usernameCookie != null){
				username = usernameCookie.getValue();
			}
			
			Cookie passwdCookie = CookieUtils.findCookie(cookies, "password");
			if(passwdCookie != null){
				passwd = passwdCookie.getValue();
			}
			
			String msg = "";
			if(request.getAttribute("msg")!=null){     
				msg = (String)request.getAttribute("msg");
			}
			
		%>
		<h3><font color="red"><%=msg %></font></h3>
		<form action="/maven.spring_test.zx_blog/GuestLogin" method="post">
			<table>
				<tr>
					<td class="td1">用户名</td>
					<td><input type="text" class="input1" name="username" value="<%=username %>"></td>
				</tr>
				<tr>
				<td class="td1">密码</td>
				<td><input type="password" class="input1" name="password" value="<%=passwd %>"></td>
				</tr>
				<tr>
				<td class="td1" colspan="2">
					<input type="checkbox" name="remember" value="true" checked="checked"> 记住用户名</td>
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