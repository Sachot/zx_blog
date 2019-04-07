package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Article;


/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() throws ServletException {
		
		System.out.println("Init!!!!");
		PreparedStatement statement=null;
   	    ResultSet result = null;
   	    Connection con = null;
		
   	    //table:admintb
		String strMysqlDriver = "com.mysql.cj.jdbc.Driver";
		String strMysqlAddr   = "jdbc:mysql://localhost:3306/";
		String strMysqlParam  = "characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		String strDbName      = "zxblogdb";
		String strTableName1   = "admintb";
		String strAccount     = "root";
		String strPassword    = "cx$981753Zz";
		
		String strMysqlUrl    = strMysqlAddr + strDbName;
		       strMysqlUrl   += "?";
		       strMysqlUrl   += strMysqlParam;
		List<Article> articles = new ArrayList<Article>();
		
		
		//Article article = new Article();
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");
		Article article = (Article)context.getBean("article");
		
		//getServletContext().setAttribute("articles", articles);
		
		try {
			//table: article
			Class.forName(strMysqlDriver);
			con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
			String sql="select* from article";
			statement=con.prepareStatement(sql);
			result = statement.executeQuery(sql);
			
			while(result.next()) {
				// 通过字段检索
				String content = result.getString("content");
				String title = result.getString("title");
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
	}


}
