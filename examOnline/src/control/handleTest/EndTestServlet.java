package control.handleTest;
import model.TestBean; //����Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EndTestServlet extends HttpServlet{
	String tempId="";
	float tempScore;
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
      TestBean testBean1=null;
      HttpSession session=request.getSession(true);
      try{  testBean1=(TestBean)session.getAttribute("testBean1");
      }
      catch(Exception exp){
    	 exp.printStackTrace(); 
         response.sendRedirect("indexStu.jsp");   
      } 
      try{  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){
    	  e.printStackTrace();
      } 
      request.setCharacterEncoding("gb2312"); 
      String id=testBean1.getId();
      tempId=id;
      tempScore=testBean1.getScore();
      Connection con;
      Statement sql1;
      PreparedStatement sql2; 
      String searchCondition="select * from tb_score where account="+id;
      String insertCondition = "INSERT INTO tb_score(account) VALUES"+"("+"'"+id+"')";

      try{ 
          String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
                      "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
          con=DriverManager.getConnection(uri);
          sql1=con.createStatement();
          ResultSet rs=sql1.executeQuery(searchCondition);
          if(!rs.next()) {
        	  sql2=con.prepareStatement(insertCondition);
              sql2.executeUpdate();     
          }
          testBean1.setBackNews("��ϲ���ύ�ɹ���");
          response.sendRedirect("/examOnline/stu/takeTest.jsp");
      }
      catch(SQLException exp){
    	  exp.printStackTrace();
    	  testBean1.setBackNews("�ύʧ�ܣ�"+exp.toString());
          response.sendRedirect("/examOnline/stu/takeTest.jsp");
      }   
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
   }
}
