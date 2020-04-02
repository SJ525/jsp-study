package control.common;

import model.AdmModel;//导入管理员数据模型
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//实现管理员的注册
@WebServlet("/handleAdmRegister")
public class HandleAdmRegister extends HttpServlet{
	public void init(ServletConfig config) throws ServletException { 
	      super.init(config);
	      try {  Class.forName("com.mysql.jdbc.Driver");
	      }
	      catch(Exception e){} 
	   }

	   public String handleString(String s)
	   {   try{ byte bb[]=s.getBytes("iso-8859-1");
	            s=new String(bb);
	       }
	       catch(Exception ee){} 
	       return s;  
	   }

	   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
	                        throws ServletException,IOException {
	      String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
	                             "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	      Connection con; 
	      PreparedStatement sql; 
	      AdmModel admBean=new AdmModel();  //创建的Javabean模型
	      request.setAttribute("admBean",admBean);//请求域里面加了一个请求的参数admBean
	      String account=request.getParameter("account").trim();
	      String password=request.getParameter("password").trim();
	      String repassword=request.getParameter("repassword").trim();
	      String name=request.getParameter("name").trim();
	      if(account==null)
	           account="";
	      if(password==null)
	           password="";
	      if(!password.equals(repassword)) { 
	         admBean.setBackNews("两次密码不同，注册失败，");
	         RequestDispatcher dispatcher= 
	         request.getRequestDispatcher("registerAdm.jsp");
	         dispatcher.forward(request, response);//转发
	         return;
	      } 
	      boolean boo=account.length()>0&&password.length()>0;
	      String backNews="";
	      try{   con=DriverManager.getConnection(uri);
	             String insertCondition="INSERT INTO tb_admin VALUES (?,?,?)";
	             sql=con.prepareStatement(insertCondition);
	             if(boo)
	             { sql.setString(1,handleString(name));
	               sql.setString(2,handleString(account));
	               sql.setString(3,handleString(password));
	               int m=sql.executeUpdate();
	               if(m!=0){
	                  backNews="注册成功";
	                  admBean.setBackNews(backNews);
	                  admBean.setAccount(account);
	                  admBean.setName(handleString(name));
	               }
	             }
	             else {
	                 backNews="信息填写不完整";
	                 admBean.setBackNews(backNews);  
	             }
	             con.close();
	      }
	      catch(SQLException exp){
	             backNews="该账号名已被使用，请您更换名字"+exp;
	             admBean.setBackNews(backNews); 
	      }
	      RequestDispatcher dispatcher= 
	      request.getRequestDispatcher("registerAdm.jsp");
	      dispatcher.forward(request, response);//转发
	   }
	   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
	                        throws ServletException,IOException {
	      doPost(request,response);
	   }
}
