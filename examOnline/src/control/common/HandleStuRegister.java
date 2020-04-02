package control.common;

import model.StuModel;//导入考生数据模型
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//实现考生的注册
@WebServlet("/handleStuRegister")
public class HandleStuRegister extends HttpServlet {
	
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
      StuModel stuBean=new StuModel();  //创建的Javabean对象
      request.setAttribute("stuBean",stuBean);
      String account=request.getParameter("account").trim();
      String password=request.getParameter("password").trim();
      String repassword=request.getParameter("repassword").trim();
      String name=request.getParameter("name").trim();
      String major=request.getParameter("major").trim();
      String grade=request.getParameter("grade").trim();
      if(account==null)
           account="";
      if(password==null)
           password="";
      if(!password.equals(repassword)) { 
         stuBean.setBackNews("两次密码不同，注册失败，");
         RequestDispatcher dispatcher= 
         request.getRequestDispatcher("registerStu.jsp");
         dispatcher.forward(request, response);//转发
         return;
      } 
      boolean boo=account.length()>0&&password.length()>0;
      String backNews="";
      try{   con=DriverManager.getConnection(uri);
             String insertCondition="INSERT INTO tb_student VALUES (?,?,?,?,?)";
             sql=con.prepareStatement(insertCondition);
             if(boo)
             { sql.setString(1,handleString(name));
               sql.setString(2,handleString(major));
               sql.setString(3,handleString(grade));
               sql.setString(4,handleString(account));
               sql.setString(5,handleString(password));
               int m=sql.executeUpdate();
               if(m!=0){
                  backNews="注册成功";
                  stuBean.setBackNews(backNews);
                  stuBean.setAccount(account);
                  stuBean.setName(handleString(name));
                  stuBean.setMajor(handleString(major));
                  stuBean.setGrade(handleString(grade));
               }
             }
             else {
                 backNews="信息填写不完整";
                 stuBean.setBackNews(backNews);  
             }
             con.close();
      }
      catch(SQLException exp){
             backNews="该账号名已被使用，请您更换名字"+exp;
             stuBean.setBackNews(backNews); 
      }
      RequestDispatcher dispatcher= 
      request.getRequestDispatcher("registerStu.jsp");
      dispatcher.forward(request, response);//转发
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
}
