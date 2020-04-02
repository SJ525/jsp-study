package control.manageCompletion;
import model.CompletionModel;//导入数据模型CompletionModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/alterCompletion")
public class AlterCompletion extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//用来查询
	PreparedStatement sql2;//用来更新
	String tempId="";
	//创建CompletionModel的javaBean对象
	CompletionModel CompletionBean1=new CompletionModel();
	CompletionModel CompletionBean2=new CompletionModel();
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
	   request.setAttribute("CompletionBean1",CompletionBean1);//请求域里面加了一个请求的参数CompletionBean1
	   try {
		con=DriverManager.getConnection(uri);
		//查询语句：查询题序为titleId的所有填空题信息
		String queryString="select * from tb_Completion where titleId="+titleId;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//结果集rs不为空，则取出rs的信息,设置CompletionBean1里面的信息
		if(rs.next()) {
			tempId=rs.getString("titleId");//将数据表中的titleId赋给全局变量tempId
			CompletionBean1.setTitleId(rs.getString("titleId"));
			CompletionBean1.setTitleStem(rs.getString("titleStem"));
			CompletionBean1.setAnswerRight(rs.getString("answerRight"));
			CompletionBean1.setAnswerExplain(rs.getString("answerExplain"));
			CompletionBean1.setBackNews("查询成功，查询结果如下：");
			CompletionBean1.setSuccess(true);
		}else {
			CompletionBean1.setBackNews("查询失败:您要查询的题序不存在，请重新输入");
			CompletionBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		CompletionBean1.setBackNews("查询失败:错误为"+e.toString());
		CompletionBean1.setSuccess(false);
	}
	   
	   //转发查询结果到alterCompletion.jsp页面
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterCompletion.jsp");
       dispatcher.forward(request,response);
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	String titleId=tempId.trim();//将tempId赋值给titleId
	String titleStem=request.getParameter("titleStem").trim();//填空题题干
	String answerRight=request.getParameter("answerRight").trim();//正确答案
	String answerExplain=request.getParameter("answerExplain").trim();//答案解释
	request.setAttribute("CompletionBean2",CompletionBean2);//请求域里面加了一个请求的参数CompletionBean2
	try{
		con=DriverManager.getConnection(uri);    
    	//更新部分：根据titleId更新其它字段
       String updateCondition="UPDATE tb_Completion set titleStem="
       		+ titleStem
       		+",answerRight="+ answerRight
       		+",answerExplain="+answerExplain
       		+" where titleId="+titleId;
       sql2=con.prepareStatement(updateCondition);
       sql2.executeUpdate(); 
	   //查询部分：根据titleId查询修改后的该填空题所有信息
       String queryString="select * from tb_Completion where titleId="+titleId;
	   sql1=con.createStatement();
       ResultSet rs=sql1.executeQuery(queryString); 
       if(rs.next()) {
    	   CompletionBean2.setTitleId(rs.getString("titleId"));
           CompletionBean2.setTitleStem(rs.getString("titleStem"));
           CompletionBean2.setAnswerRight(rs.getString("answerRight"));
           CompletionBean2.setAnswerExplain(rs.getString("answerExplain"));
           CompletionBean2.setBackNews("修改成功，修改结果如下：");
           CompletionBean2.setSuccess(true);
       }
	   con.close();
	}
	catch(SQLException exp){
		CompletionBean2.setBackNews("修改失败:错误为"+exp.toString());
		CompletionBean2.setSuccess(false);
	}
		//转发修改结果到alterCompletion.jsp页面
		RequestDispatcher dispatcher=
	    request.getRequestDispatcher("alterCompletion.jsp");
	    dispatcher.forward(request,response);
   }	
}
