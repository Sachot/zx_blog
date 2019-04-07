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
		    	   //���������ļ��������
					DiskFileItemFactory dfif = new DiskFileItemFactory();
					
					//�������Ľ�����
					ServletFileUpload sful = new ServletFileUpload(dfif);
					
					//����request���󣬷�����һ��List���ϣ����FileItem����
					List<FileItem> list = sful.parseRequest(request);
					
					//����һ�����ڽ��ձ���ֵ�����ݵ�Map
					Map<String, String> map = new HashMap<String, String>();
		    	   
					//�ϴ��ļ������·��
					String url = null;
					
					for(FileItem f : list) {
						//�ж��Ǳ�������ļ��ϴ���
						if(f.isFormField()) {
							//��ͨ����
							
							//��ñ���name���Ե�ֵ
							String name = f.getFieldName();
							//���value���Ե�ֵ
							String value = f.getString("utf-8");
												
						    map.put(name, value);							
						}else {
							//�����ϴ��ļ���
							
							//�����ϴ��ļ����ļ���(ע��:����getFieldName!!!!)
							String fileName = f.getName();
							
							if((!fileName.equals(""))&&fileName!=null) {
								String uuidFileName = UploadUtils.getUUIDFileName(fileName);
								
								//�����ϴ��ļ�������(������)
								InputStream ips = f.getInputStream();
								
								//��ȡ�ϴ����������ϵ��ļ�·��
								String filePath = this.getServletContext().getRealPath("/upload");
								
								//���������Խӵ������
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
		    	   
				   //��װ��guest��
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
		    	   
					
					
		    	   //¼�����ݿ�
					Class.forName(strMysqlDriver);
					Connection con = DriverManager.getConnection(strMysqlUrl,strAccount,strPassword);
					String sql = "insert into admintb values(?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, admin.getUsername());
					ps.setString(2, admin.getPasswd());
					ps.setString(3, admin.getPath());
					
					//ִ�и��²���
					ps.executeUpdate();
					ps.close();
					con.close();
					
					//����index
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
