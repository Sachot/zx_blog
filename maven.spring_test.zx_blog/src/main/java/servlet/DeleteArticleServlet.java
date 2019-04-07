package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Article;

/**
 * Servlet implementation class DeleteArticleServlet
 */
@WebServlet("/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("flag");
		request.setCharacterEncoding("utf-8");
		List<Article> articles = (List<Article>) this.getServletContext().getAttribute("articles");
		String title = articles.get(Integer.parseInt("flag")).getTitle();
		String content = articles.get(Integer.parseInt("flag")).getContent();
		
        Connection con = null;
		
   	    //table:article
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
		       
		       
		       try {
					//table: article
					Class.forName(strMysqlDriver);
					con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
					String sql="delete from article where title=(select title from article where title='"+title+"')";
					PreparedStatement statement = con.prepareStatement(sql);
					statement.executeUpdate();
					statement.close();
					con.close();
					response.sendRedirect(request.getContextPath()+"/adminview.jsp");
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}catch(SQLException e){
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
		       
		 articles.remove(Integer.parseInt("flag"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
