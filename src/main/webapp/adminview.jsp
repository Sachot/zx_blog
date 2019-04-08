<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="domain.Admin, java.util.List, domain.Article"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
.box{width:100px;font-size:30px;}.box img {width:80%}
</style>
</head>
<body>
    <%
        String title=null;
        String content=null;
        String comment=null;
		if(session.getAttribute("admin")!=null){
			Admin admin = (Admin)session.getAttribute("admin");
			System.out.println(admin.getPath());
			// 获得绝对路径最后一个\的位置
			int idx = admin.getPath().lastIndexOf("\\");
			// 获得文件上传的唯一文件名：
			String fileName = admin.getPath().substring(idx+1);
		
		
	%>
	<div class="login">
		<div class="header">
		<p>登录成功</p>
		<div class="box"><img src="/maven.spring_test.zx_blog/upload/<%= fileName %>" /><%= admin.getUsername() %></div>
		<div>
		<p><a href="./addarticle.jsp">写博文</a></p>
		</div>			
		</div>
		<div class="content">
		
			
	<%			
		}else{
	%>
	<div class="login">
		<div class="header">
			<h1>您还没有登录！请先去<a href="/maven.spring_test.zx_blog/adminlogin.jsp">登录</a>！</h1>
		</div>
	</div>
	<div class="content">
	<%		
		}
         if(application.getAttribute("articles")!=null) {
        	 int i=1;
		     List<Article> articles = (List<Article>)application.getAttribute("articles");
		     for(Article a : articles) {
		    	 i++;
		    	 
		    	 title = a.getTitle();
		    	 content = a.getContent();
		    	 
    %>
         <h3><%= title %></h3>
        <p><%= content %></p>
        <form action="/maven.spring_test.zx_blog/DeleteArticleServlet?flag=<%=i %>" method="get">
        <input type="submit" value="删除">
        </form>
        
    <%
               List<String> comments = a.getCommentList();
               for(String c : comments) {
            	   comment=c;
     %>
         <p>评论：</p>
         <p><%=comment %></p>
     <% 
               }
		     }
	     }
	%>
		
	</div>	
</body>
</html>