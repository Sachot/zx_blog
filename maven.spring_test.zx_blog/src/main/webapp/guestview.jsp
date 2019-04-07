<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="domain.Admin, java.util.List, domain.Article, java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Blog</title>
</head>
<body>
<%
		
		/* PreparedStatement statement=null;
  	    ResultSet result = null;
  	    Connection con = null;
  	    String content = null;
  	    String title = null;
		
  	    
		String strMysqlDriver = "com.mysql.cj.jdbc.Driver";
		String strMysqlAddr   = "jdbc:mysql://localhost:3306/";
		String strMysqlParam  = "characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		String strDbName      = "zxblogdb";
		String strTableName1   = "article";
		String strAccount     = "root";
		String strPassword    = "cx$981753Zz";
		
		String strMysqlUrl    = strMysqlAddr + strDbName;
		       strMysqlUrl   += "?";
		       strMysqlUrl   += strMysqlParam;
		       
		       
				List<Article> articles = (List<Article>) this.getServletContext().getAttribute("articles");
				Article article = new Article();
				
				try {
					//table: article
					Class.forName(strMysqlDriver);
					con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
					String sql="select* from article";
					statement=con.prepareStatement(sql);
					result = statement.executeQuery(sql);
					
					while(result.next()) {
						// 通过字段检索
						content = result.getString("content");
						title = result.getString("title");
						article.setContent(content);
						article.setTitle(title);
						
						articles.add(article);							
					}
					result.close();
					statement.close();
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//把Articles传回去
				this.getServletContext().setAttribute("articles", articles);
				
				
				if(application.getAttribute("articles")!=null) {
		        	 int i=1;
				     //List<Article> articlesList = (List<Article>)application.getAttribute("articles");
				     for(Article a : articles) {
				    	 i++;
				    	 title = a.getTitle();
				    	 content = a.getContent(); */
				    	 
				    	 
				    	 String title=null;
				         String content=null;
				         String comment=null;
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
            <%
               List<String> comments = a.getCommentList();
               for(String c : comments) {
            %>
               <p>评论：</p>
               <p><%=c %></p>   
              <%
               }
              %> 
               
    
               <p>添加评论:</p>
               <form action="/zx_blog/CommentServlet>" method="get">
               <input type="text" name="flag" value="<%=i %>" oninput="value=value.replace(/[^\d]/g,'')"/>
               <input type="text" name="comment"/>
               <input id="tj" type="submit" value="提交"/>
               </form>
               <script type="text/javascript">
               document.getElementById("tj").onclick = fuction() {
            	   alert("提交成功！");
               }
               </script>
<%
				     }
				}
%>


</body>
</html>