package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Article;

/**
 * Servlet implementation class AddArticleServlet
 */
@WebServlet("/AddArticleServlet")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PreparedStatement statement=null;
   	    ResultSet result = null;
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
		
		       //��װ
		//Article article = new Article();
		 @SuppressWarnings("resource")
		ApplicationContext context = 
			         new ClassPathXmlApplicationContext("Beans.xml");
		 Article article = (Article)context.getBean("article");
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		//article.setTitle(title);
		//article.setContent(content);
		
		try {
			
			//���������ļ��������
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			
			//�������Ľ�����
			ServletFileUpload sful = new ServletFileUpload(dfif);
			
			//����request���󣬷�����һ��List���ϣ����FileItem����
			List<FileItem> list = sful.parseRequest(request);
			
			//����һ�����ڽ��ձ���ֵ�����ݵ�Map
			Map<String, String> map = new HashMap<String, String>();
			
			
			for(FileItem f : list) {
				//�ж��Ǳ�������ļ��ϴ���
				if(f.isFormField()) {
					//��ͨ����
					
					//��ñ���name���Ե�ֵ
					String name = f.getFieldName();
					//���value���Ե�ֵ
					String value = f.getString("utf-8");
										
				    map.put(name, value);							
				}
			}
			
			article.setTitle(map.get("title"));
			article.setContent(map.get("content"));
			
			
			
			//table: article
			Class.forName(strMysqlDriver);
			con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
			String sql="insert into article values(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getContent());
			ps.executeUpdate();
			ps.close();
			con.close();
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Article> articles = (List<Article>) this.getServletContext().getAttribute("articles");
		articles.add(article);
		this.getServletContext().setAttribute("articles", articles);
		response.sendRedirect(request.getContextPath()+"/adminview.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
