package control.common;

import model.LoginModel;//�����½����ģ��
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//ʵ�ֹ���Ա�ĵ�½����
@WebServlet("/handleAdmLogin")
public class HandleAdmLogin extends HttpServlet {
	public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try{ 
             Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   //��s����iso-8859-1��׼���±��룬���ر������ַ���
   public String handleString(String s){
      try{  byte bb[]=s.getBytes("iso-8859-1");
            s=new String(bb);
      }
      catch(Exception ee){} 
      return s;  
   }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
		String account=request.getParameter("account").trim();//��ȡ��������˺�
		String password=request.getParameter("password").trim();//��ȡ�����������
		
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
           backNews=account+"�Ѿ���¼��";
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
                     backNews="��ϲ����¼�ɹ�";
                     loginBean.setBackNews(backNews);
                     loginBean.setSuccess(true);
                     loginBean.setAccount(account);      
                   }
                   else{
                     backNews="��������û��������ڣ������벻����";
                     loginBean.setBackNews(backNews); 
                     loginBean.setSuccess(false); 
                   }
                }
                else{
                  backNews="������д������½��Ϣ";
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
        	   //��¼�ɹ���ת��������Ա������ҳ
               RequestDispatcher dispatcher=
               request.getRequestDispatcher("/adm/indexAdm.jsp");
               dispatcher.forward(request,response);
           }else {
        	   //��¼ʧ�ܣ�ת��������Ա��½��ҳ
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
