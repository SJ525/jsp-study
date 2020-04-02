package control.common;

import model.LoginModel;//导入登陆数据模型
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//实现管理员的登陆功能
@WebServlet("/handleAdmLogin")
public class HandleAdmLogin extends HttpServlet {
	public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try{ 
             Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   //将s按照iso-8859-1标准重新编码，返回编码后的字符串
   public String handleString(String s){
      try{  byte bb[]=s.getBytes("iso-8859-1");
            s=new String(bb);
      }
      catch(Exception ee){} 
      return s;  
   }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
		String account=request.getParameter("account").trim();//获取表单输入的账号
		String password=request.getParameter("password").trim();//获取表单输入的密码
		
		Connection con; //
		Statement sql; 
		LoginModel loginBean=null;
		String backNews="";
		HttpSession session=request.getSession(true);
		  try{  loginBean=(LoginModel)session.getAttribute("login");
				if(loginBean==null){
				   loginBean=new LoginModel();  
				   session.setAttribute("login",loginBean);
				}
		  }
		  catch(Exception ee){
				loginBean=new LoginModel();  
				session.setAttribute("login",loginBean);
		  }
		
		boolean ok=loginBean.getSuccess();
		account=handleString(account);
		password=handleString(password);
		
		if(ok==true&&account.equals(loginBean.getAccount())){
           backNews=account+"已经登录了";
           loginBean.setBackNews(backNews);
      }
      else{
           String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
	                             "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
           boolean boo=(account.length()>0)&&(password.length()>0);  
           try{ 
                con=DriverManager.getConnection(uri);
                String condition="select * from tb_admin where account = '"+account+
                                 "' and password ='"+password+"'";
                sql=con.createStatement();  
                if(boo){
                   ResultSet rs=sql.executeQuery(condition);
                   boolean m=rs.next();
                   if(m==true){
                     backNews="恭喜！登录成功";
                     loginBean.setBackNews(backNews);
                     loginBean.setSuccess(true);
                     loginBean.setAccount(account);      
                   }
                   else{
                     backNews="您输入的用户名不存在，或密码不般配";
                     loginBean.setBackNews(backNews); 
                     loginBean.setSuccess(false); 
                   }
                }
                else{
                  backNews="请先填写完整登陆信息";
                  loginBean.setBackNews(backNews); 
                  loginBean.setSuccess(false); 
                }
                con.close();
           }
           catch(SQLException exp){
                backNews=""+exp;
                loginBean.setBackNews(backNews); 
                loginBean.setSuccess(false); 
           }
           if(loginBean.getSuccess()==true) {
        	   //登录成功，转发到管理员界面首页
               RequestDispatcher dispatcher=
               request.getRequestDispatcher("/adm/indexAdm.jsp");
               dispatcher.forward(request,response);
           }else {
        	   //登录失败，转发到管理员登陆首页
               RequestDispatcher dispatcher=
               request.getRequestDispatcher("loginAdm.jsp");
               dispatcher.forward(request,response);
           } 
      }
      
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      doPost(request,response);
   }
}
