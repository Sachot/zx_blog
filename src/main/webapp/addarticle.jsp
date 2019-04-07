<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>写文章</title>
</head>
<body>
     <form action="/zx_blog/AddArticleServlet" method="post" enctype="multipart/form-data">
          标题：<input type="text" class="input1" name="title">
      <label>正文：</label>
      <textarea name="content" rows="100" cols="200">
      </textarea>
      <input type="submit" value="提交" id="submit-btn">
     </form>
     <script type="text/javascript">
               document.getElementById("submit-btn").onclick = fuction() {
            	   alert("提交成功！");
               }
               </script>
</body>
</html>