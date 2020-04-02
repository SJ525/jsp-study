package control.handleTest;
import model.TestBean; //����Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//@WebServlet("/readTestServlet")
public class ReadTestServlet extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
	  
      
      HttpSession session=request.getSession(true);
	  TestBean testBean1=null;
      try{  testBean1=(TestBean)session.getAttribute("testBean1");
            if(testBean1==null){
                testBean1=new TestBean(); //����Javabean����
                session.setAttribute("testBean1",testBean1);
            }
      }
      catch(Exception exp){
            testBean1=new TestBean();  //����Javabean����
            session.setAttribute("testBean1",testBean1);
      } 
     try{  Class.forName("com.mysql.jdbc.Driver");
     }
     catch(Exception e){} 
     request.setCharacterEncoding("gb2312");
     String id=request.getParameter("id");
     testBean1.setId(id); 
     testBean1.setTextAmount(15);
     int testAmount = testBean1.getTextAmount();   //��������
     Connection con;
     Statement sql; 
     ResultSet rs;
     try{ 
          String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
                      "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
          con=DriverManager.getConnection(uri);
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_READ_ONLY);
          rs=sql.executeQuery("SELECT * FROM tb_choice");
          rs.last();    
          int recordAmount=rs.getRow();  //�õ���¼��
          testAmount = Math.min(recordAmount,testAmount);
          LinkedList<Integer> list1=(LinkedList<Integer>)session.getAttribute("list1");
          if(list1==null||list1.size()==0){
             list1 = new LinkedList<Integer>();
             for(int i=1;i<=recordAmount;i++) {
               list1.add(i);
             }
             session.setAttribute("list1",list1); 
          }
          int m= -1;
          int index=-1;
          if(list1.size()>=1) {
             m= (int)(Math.random()*list1.size());
             index=list1.get(m);
             list1.remove(m);
             session.setAttribute("list1",list1); 
             int tihao=testBean1.getNumber();
             if(tihao<testAmount) {
                 //�����ж���һ���Ƿ���ȷ
                String studentAnswer=testBean1.getAnswer();
                if(studentAnswer!=null&&studentAnswer.length()>=1) {
                   if(studentAnswer.equalsIgnoreCase(testBean1.getCorrectAnswer().substring(0, 1))){
                      float score= testBean1.getScore();
                      score++;
                     testBean1.setScore(score);
                   }
                }
                //�����ȡ��һ��Ŀ��
                tihao++;
                testBean1.setNumber(tihao); //���
                rs.absolute(index); //�����ȡ��Ŀ
                testBean1.setQuestions(rs.getString("titleStem"));//��Ŀ����
                testBean1.setChoiceA(rs.getString("optionA"));  //��Ŀ��ѡ��a
                testBean1.setChoiceB(rs.getString("optionB"));  //��Ŀ��ѡ��b
                testBean1.setChoiceC(rs.getString("optionC"));  //��Ŀ��ѡ��c
                testBean1.setChoiceD(rs.getString("optionD"));  //��Ŀ��ѡ��d
                testBean1.setCorrectAnswer(rs.getString("answerRight").trim());//��Ŀ�Ĵ�
                testBean1.setMess("�����ǵ�"+tihao+"��");
                con.close(); 
            }
            else {
                testBean1.setMess("��������������ύѡ�����");
                String studentAnswer=testBean1.getAnswer(); //�ж����һ��
                if(studentAnswer!=null&&studentAnswer.length()>=1) {
                   if(studentAnswer.equalsIgnoreCase(testBean1.getCorrectAnswer().substring(0, 1))){
                      float score= testBean1.getScore();
                      score++;
                     testBean1.setScore(score);
                   }
                }
                testBean1.setAnswer(null);
                testBean1.setNumber(0);
                testBean1.setQuestions(null);
                testBean1.setChoiceA(null);  
                testBean1.setChoiceB(null);  
                testBean1.setChoiceC(null);  
                testBean1.setChoiceD(null);  
            }
          }
          else {
             testBean1.setMess("û�г鵽��Ŀ");
          }
          response.sendRedirect("/examOnline/stu/takeTest.jsp");
     }
     catch(SQLException e){
    	 testBean1.setBackNews("ʧ�ܣ�����Ϊ"+e.toString());
    	 response.sendRedirect("/examOnline/stu/takeTest.jsp");
     }  
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
	  
   }
}
