package control.handleTest;
import model.TestBean; //����Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EndTestServlet1 extends HttpServlet{

   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
	   TestBean testBean1=null;
	   TestBean testBean2=null;
      HttpSession session=request.getSession(true);
      try{ 
    	  testBean1=(TestBean)session.getAttribute("testBean1");
    	  testBean2=(TestBean)session.getAttribute("testBean2");
      }
      catch(Exception exp){
    	 exp.printStackTrace(); 
         response.sendRedirect("/examOnline/stu/takeTest2.jsp");   
      } 
      try{  Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){
    	  e.printStackTrace();
      } 
      request.setCharacterEncoding("gb2312"); 
      Connection con;
      PreparedStatement sql; 
      float total=testBean1.getScore()+testBean2.getScore();
      String condition = "update tb_score set cho_score="+testBean1.getScore()
      					+",com_score="+testBean2.getScore()
      					+",tot_score="+total
      					+" where account="+testBean1.getId();
      try{ 
          String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
                      "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
          con=DriverManager.getConnection(uri);
          sql=con.prepareStatement(condition);
          sql.executeUpdate();
          float score = testBean2.getScore();
          testBean2.setBackNews("��ϲ���ύ�ɹ������ڿ���ȥ��ѯ�ɼ���");
          response.sendRedirect("/examOnline/stu/takeTest2.jsp");
      }
      catch(SQLException exp){
    	  exp.printStackTrace();
    	  testBean2.setBackNews("�ύʧ�ܣ�"+exp.toString());
          response.sendRedirect("/examOnline/stu/takeTest2.jsp");
      } 
   }
}
