package control.manageChoice;
import model.ChoiceModel;//导入数据模型ChoiceModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/alterChoice")
public class AlterChoice extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//用来查询
	PreparedStatement sql2;//用来更新
	String tempId="";
	//创建ChoiceModel的javaBean对象
	ChoiceModel choiceBean1=new ChoiceModel();
	ChoiceModel choiceBean2=new ChoiceModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
      try{ 
             Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
   }
	
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	   String titleId=request.getParameter("titleId");//获取要查找而输入的题目序号   
	   request.setAttribute("choiceBean1",choiceBean1);//请求域里面加了一个请求的参数choiceBean1
	   try {
		con=DriverManager.getConnection(uri);
		//查询语句：查询题序为titleId的所有选择题信息
		String queryString="select * from tb_choice where titleId="+titleId;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//结果集rs不为空，则取出rs的信息,设置choiceBean1里面的信息
		if(rs.next()) {
			tempId=rs.getString("titleId");//将数据表中的titleId赋给全局变量tempId
			choiceBean1.setTitleId(rs.getString("titleId"));
			choiceBean1.setTitleStem(rs.getString("titleStem"));
			choiceBean1.setOptionA(rs.getString("optionA"));
			choiceBean1.setOptionB(rs.getString("optionB"));
			choiceBean1.setOptionC(rs.getString("optionC"));
			choiceBean1.setOptionD(rs.getString("optionD"));
			choiceBean1.setAnswerRight(rs.getString("answerRight"));
			choiceBean1.setAnswerExplain(rs.getString("answerExplain"));
			choiceBean1.setBackNews("查询成功，查询结果如下：");
			choiceBean1.setSuccess(true);
		}else {
			choiceBean1.setBackNews("查询失败:您要查询的题序不存在，请重新输入");
			choiceBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		choiceBean1.setBackNews("查询失败:错误为"+e.toString());
		choiceBean1.setSuccess(false);
	}
	   
	   //转发查询结果到alterChoice.jsp页面
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterChoice.jsp");
       dispatcher.forward(request,response);
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	String titleId=tempId.trim();//将tempId赋值给titleId
	String titleStem=request.getParameter("titleStem").trim();//选择题题干
	String optionA=request.getParameter("optionA").trim();//选项A
	String optionB=request.getParameter("optionB").trim();//选项B
	String optionC=request.getParameter("optionC").trim();//选项C
	String optionD=request.getParameter("optionD").trim();//选项D
	String answerRight=request.getParameter("answerRight").trim();//正确答案
	String answerExplain=request.getParameter("answerExplain").trim();//答案解释
	request.setAttribute("choiceBean2",choiceBean2);//请求域里面加了一个请求的参数choiceBean2
	try{
		con=DriverManager.getConnection(uri);    
    	//更新部分：根据titleId更新其它字段
       String updateCondition="UPDATE tb_choice set titleStem="
       		+ titleStem
       		+",optionA="+ optionA+",optionB="+ optionB
       		+",optionC="+ optionC+",optionD="+ optionD
       		+",answerRight="+ answerRight
       		+",answerExplain="+answerExplain
       		+" where titleId="+titleId;
       sql2=con.prepareStatement(updateCondition);
       sql2.executeUpdate(); 
	   //查询部分：根据titleId查询修改后的该选择题所有信息
       String queryString="select * from tb_choice where titleId="+titleId;
	   sql1=con.createStatement();
       ResultSet rs=sql1.executeQuery(queryString); 
       if(rs.next()) {
    	   choiceBean2.setTitleId(rs.getString("titleId"));
           choiceBean2.setTitleStem(rs.getString("titleStem"));
           choiceBean2.setOptionA(rs.getString("optionA"));
           choiceBean2.setOptionB(rs.getString("optionB"));
           choiceBean2.setOptionC(rs.getString("optionC"));
           choiceBean2.setOptionD(rs.getString("optionD"));
           choiceBean2.setAnswerRight(rs.getString("answerRight"));
           choiceBean2.setAnswerExplain(rs.getString("answerExplain"));
           choiceBean2.setBackNews("修改成功，修改结果如下：");
           choiceBean2.setSuccess(true);
       }
	   con.close();
	}
	catch(SQLException exp){
		choiceBean2.setBackNews("修改失败:错误为"+exp.toString());
		choiceBean2.setSuccess(false);
	}
		//转发修改结果到alterChoice.jsp页面
		RequestDispatcher dispatcher=
	    request.getRequestDispatcher("alterChoice.jsp");
	    dispatcher.forward(request,response);
   }	
}
