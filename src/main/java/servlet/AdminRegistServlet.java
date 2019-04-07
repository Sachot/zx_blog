package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import domain.Admin;
import domain.Guest;
import utils.UploadUtils;

/**
 * Servlet implementation class GuestRegistServlet
 */
@WebServlet("/AdminRegistServlet")
public class AdminRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		//table:admintb
		String strMysqlDriver = "com.mysql.cj.jdbc.Driver";
		String strMysqlAddr   = "jdbc:mysql://localhost:3306/";
		String strMysqlParam  = "characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		String strDbName      = "zxblogdb";
		String strTableName   = "admintb";
		String strAccount     = "root";
		String strPassword    = "cx$981753Zz";
		
		String strMysqlUrl    = strMysqlAddr + strDbName;
		       strMysqlUrl   += "?";
		       strMysqlUrl   += strMysqlParam;
		       
		       try{
		    	   //创建磁盘文件项工厂对象
					DiskFileItemFactory dfif = new DiskFileItemFactory();
					
					//创建核心解析类
					ServletFileUpload sful = new ServletFileUpload(dfif);
					
					//解析request请求，返回是一个List集合，存放FileItem对象
					List<FileItem> list = sful.parseRequest(request);
					
					//定义一个用于接收表单键值对数据的Map
					Map<String, String> map = new HashMap<String, String>();
		    	   
					//上传文件，存放路径
					String url = null;
					
					for(FileItem f : list) {
						//判断是表单项，还是文件上传项
						if(f.isFormField()) {
							//普通表单项
							
							//获得表单项name属性的值
							String name = f.getFieldName();
							//获得value属性的值
							String value = f.getString("utf-8");
												
						    map.put(name, value);							
						}else {
							//接收上传文件项
							
							//接收上传文件的文件名(注意:不是getFieldName!!!!)
							String fileName = f.getName();
							
							if((!fileName.equals(""))&&fileName!=null) {
								String uuidFileName = UploadUtils.getUUIDFileName(fileName);
								
								//接收上传文件的数据(输入流)
								InputStream ips = f.getInputStream();
								
								//获取上传到服务器上的文件路径
								String filePath = this.getServletContext().getRealPath("/upload");
								
								//将输入流对接到输出流
								url = filePath+"\\"+uuidFileName;
								OutputStream ops = new FileOutputStream(url);
								byte[] b = new byte[1024];
								int len = 0;
								
								//https://blog.csdn.net/qq_37835596/article/details/76559572
								while((len = ips.read(b))!=-1) {
									ops.write(b, 0, len);
								}
								ips.close();
								ops.close();
							}		
						}
					}
		    	   
				   //封装到guest中
		    	   //Admin admin = new Admin();
					@SuppressWarnings("resource")
					ApplicationContext context = 
							new ClassPathXmlApplicationContext("Beans.xml");
					Admin admin = (Admin)context.getBean("admin");
		    	   admin.setUsername(map.get("username"));
		    	   admin.setPasswd(map.get("passwd"));
		    	   admin.setPath(url);
		    	   
		    	   
		    	   System.out.println(map.get("username"));
		    	   System.out.println(map.get("passwd"));
		    	   System.out.println(url);
		    	   
					
					
		    	   //录入数据库
					Class.forName(strMysqlDriver);
					Connection con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
					String sql = "insert into admintb values(?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, admin.getUsername());
					ps.setString(2, admin.getPasswd());
					ps.setString(3, admin.getPath());
					
					//执行更新操作
					ps.executeUpdate();
					ps.close();
					con.close();
					
					//返回index
					response.sendRedirect(request.getContextPath()+"/adminlogin.jsp");
					
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}catch(SQLException e){
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
			
		       
		       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
