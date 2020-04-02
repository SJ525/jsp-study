package control.handleTest;
import model.TestBean; //引入Javabean模型
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
                testBean1=new TestBean(); //创建Javabean对象
                session.setAttribute("testBean1",testBean1);
            }
      }
      catch(Exception exp){
            testBean1=new TestBean();  //创建Javabean对象
            session.setAttribute("testBean1",testBean1);
      } 
     try{  Class.forName("com.mysql.jdbc.Driver");
     }
     catch(Exception e){} 
     request.setCharacterEncoding("gb2312");
     String id=request.getParameter("id");
     testBean1.setId(id); 
     testBean1.setTextAmount(15);
     int testAmount = testBean1.getTextAmount();   //考题数量
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
          int recordAmount=rs.getRow();  //得到记录数
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
                 //首先判断上一题是否正确
                String studentAnswer=testBean1.getAnswer();
                if(studentAnswer!=null&&studentAnswer.length()>=1) {
                   if(studentAnswer.equalsIgnoreCase(testBean1.getCorrectAnswer().substring(0, 1))){
                      float score= testBean1.getScore();
                      score++;
                     testBean1.setScore(score);
                   }
                }
                //随机抽取下一题目：
                tihao++;
                testBean1.setNumber(tihao); //题号
                rs.absolute(index); //随机抽取题目
                testBean1.setQuestions(rs.getString("titleStem"));//题目内容
                testBean1.setChoiceA(rs.getString("optionA"));  //题目的选择a
                testBean1.setChoiceB(rs.getString("optionB"));  //题目的选择b
                testBean1.setChoiceC(rs.getString("optionC"));  //题目的选择c
                testBean1.setChoiceD(rs.getString("optionD"));  //题目的选择d
                testBean1.setCorrectAnswer(rs.getString("answerRight").trim());//题目的答案
                testBean1.setMess("现在是第"+tihao+"题");
                con.close(); 
            }
            else {
                testBean1.setMess("答题结束，单击提交选择题答案");
                String studentAnswer=testBean1.getAnswer(); //判断最后一题
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
             testBean1.setMess("没有抽到题目");
          }
          response.sendRedirect("/examOnline/stu/takeTest.jsp");
     }
     catch(SQLException e){
    	 testBean1.setBackNews("失败，错误为"+e.toString());
    	 response.sendRedirect("/examOnline/stu/takeTest.jsp");
     }  
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
	  
   }
}
