package control.handleTest;
import model.TestBean; //引入Javabean模型
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ReadTestServlet1 extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
	   TestBean testBean2=null;
	      HttpSession session=request.getSession(true);
	      try{  testBean2=(TestBean)session.getAttribute("testBean2");
	            if(testBean2==null){
	                testBean2=new TestBean(); //创建Javabean对象
	                session.setAttribute("testBean2",testBean2);
	            }
	      }
	      catch(Exception exp){
	            testBean2=new TestBean();  //创建Javabean对象
	            session.setAttribute("testBean2",testBean2);
	      } 
	     try{  Class.forName("com.mysql.jdbc.Driver");
	     }
	     catch(Exception e){} 
	     request.setCharacterEncoding("gb2312");
	     String id=request.getParameter("id");
	     testBean2.setId(id); 
	     testBean2.setTextAmount(5);//设置考题数量为3
	     int testAmount = testBean2.getTextAmount();  //获取考题数量
	     Connection con;
	     Statement sql; 
	     ResultSet rs;
	     try{ 
	          String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
	                      "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	          con=DriverManager.getConnection(uri);
	          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	                                 ResultSet.CONCUR_READ_ONLY);
	          rs=sql.executeQuery("SELECT * FROM tb_completion");
	          rs.last();    
	          int recordAmount=rs.getRow();  //得到记录数
	          testAmount = Math.min(recordAmount,testAmount);
	          LinkedList<Integer> list2=(LinkedList<Integer>)session.getAttribute("list2");
	          if(list2==null||list2.size()==0){
	             list2 = new LinkedList<Integer>();
	             for(int i=1;i<=recordAmount;i++) {
	               list2.add(i);
	             }
	             session.setAttribute("list2",list2); 
	          }
	          int m= -1;
	          int index=-1;
	          if(list2.size()>=1) {
	             m= (int)(Math.random()*list2.size());
	             index=list2.get(m);
	             list2.remove(m);
	             session.setAttribute("list2",list2); 
	             int tihao=testBean2.getNumber();
	             if(tihao<testAmount) {
	                 //首先判断上一题是否正确，给出分数：
	                String studentAnswer=testBean2.getAnswer();
	                if(studentAnswer!=null&&studentAnswer.length()>=1) {
	                   if(studentAnswer.equalsIgnoreCase(testBean2.getCorrectAnswer())){
	                      float score= testBean2.getScore();
	                      score++;     
	                     testBean2.setScore(score);
	                   }
	                }
	                //随机抽取下一题目：
	                tihao++;
	                testBean2.setNumber(tihao); //题号
	                rs.absolute(index); //随机抽取题目
	                testBean2.setQuestions(rs.getString("titleStem"));//题目内容
	                testBean2.setCorrectAnswer(rs.getString("answerRight").trim());//题目的答案
	                testBean2.setMess("现在是第"+tihao+"题");
	                con.close(); 
	            }
	            else {
	                testBean2.setMess("答题结束，单击提交选择题答案");
	                String studentAnswer=testBean2.getAnswer(); //判断最后一题
	                if(studentAnswer!=null&&studentAnswer.length()>=1) {
	                   if(studentAnswer.equals(testBean2.getCorrectAnswer())){
	                      float score= testBean2.getScore();
	                      score++;
	                     testBean2.setScore(score);
	                   }
	                }
	                testBean2.setAnswer(null);
	                testBean2.setNumber(0);
	                testBean2.setQuestions(null); 
	            }
	          }
	          else {
	             testBean2.setMess("没有抽到题目");
	          }
	          response.sendRedirect("/examOnline/stu/takeTest2.jsp");
	     }
	     catch(SQLException e){
	    	 testBean2.setBackNews("失败，错误为"+e.toString());
	    	 response.sendRedirect("/examOnline/stu/takeTest2.jsp");
	     }  
   }
}
