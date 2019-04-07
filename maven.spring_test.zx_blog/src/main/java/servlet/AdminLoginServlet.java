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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Admin;
import domain.Article;

/**
 * Servlet implementation class GuestLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        //���յ�¼ҳ����û���������
				String username = request.getParameter("username");
				String passwd = request.getParameter("passwd");
				Admin admin = new Admin();
				boolean loginFlag = false;
				PreparedStatement statement1=null;
				//PreparedStatement statement2=null;
		   	    ResultSet result1 = null;
		   	    //ResultSet result2 = null;
		   	    Connection con1 = null;
		   	    //Connection con2 = null;
				
		   	    //table:guestregist
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
				
				try {
					Class.forName(strMysqlDriver);
					con1 = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
					String sql="select* from admintb where username=  '"+username+"'"+"and passwd= '"+passwd+"'";
					statement1=con1.prepareStatement(sql);
					result1 = statement1.executeQuery(sql);
					
					if(result1.next()) 
		            {
		            	loginFlag=true;
		            	admin.setUsername(result1.getString("username"));
		            	admin.setPasswd(result1.getString("passwd"));
		            	admin.setPath(result1.getString("path"));
		            	}
		            else
		            {
		            	loginFlag=false;
		            	}
					
					
					
		        } catch (Exception e)
		        {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
			 finally{ 
		            try { 
		                if(result1 != null) 
		                	result1.close(); 
		                if(statement1 != null) 
		                	statement1.close(); 
		                if(con1 != null) 
		                    con1.close(); 
		            } catch (SQLException e) { 
		                // TODO Auto-generated catch block 
		                e.printStackTrace(); 
		            } 
		        }
				
				
			   if(loginFlag) {
				   
				 //�����ѡ���ͼ�¼��¼�����û���������
					/*if(request.getParameter("remember").equals("true")) {
						Cookie usernameCookie = new Cookie("username", username);
						Cookie passwdCookie = new Cookie("passwd", passwd);
						//����cookie��Ч�ķ��ʵ�ַ(ֻҪ����������̵Ķ���������cookie)
						usernameCookie.setPath("/zx_blog");
						passwdCookie.setPath("/zx_blog");
						
						//����cookie��Ч��ʱ��
						usernameCookie.setMaxAge(3600*24);
						passwdCookie.setMaxAge(3600*24);
						
						//��cookie��д���������(��������response���������д)
						response.addCookie(usernameCookie);
						response.addCookie(passwdCookie);						
					}*/
					
					//List<Article> articles = (List<Article>) this.getServletContext().getAttribute("articles");
					//Article article = new Article();
					
					
					
					
				    //�û��������붼�ɹ�ƥ��Ͱѵ�¼��Ϣ���浽session��
					request.getSession().setAttribute("admin", admin);
					response.sendRedirect(request.getContextPath()+"/adminview.jsp");
					return;
			   }
			   
			   
			    //��¼ʧ��
				request.setAttribute("msg", "�û�����������󣬵�¼ʧ�ܣ�");
				request.getRequestDispatcher("/adminlogin.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
