package control.manageStuMsg;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.StuModel;
@WebServlet("/adm/alterStuMsg")
public class AlterStuMsg extends HttpServlet{
	
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;
	PreparedStatement sql2;
	String tempAccount="";
	//创建ChoiceModel的javaBean对象
	StuModel stuBean1=new StuModel();
	StuModel stuBean2=new StuModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	    try{ 
	           Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){} 
	 }
	
	public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
            throws ServletException,IOException{
	   String account=request.getParameter("account");//获取要查找而输入的考生账号   
	   request.setAttribute("stuBean1",stuBean1);//请求域里面加了一个请求的参数stuBean1
	   try {
		con=DriverManager.getConnection(uri);
		//查询语句：查询考生账号为account的所有考生信息
		String queryString="select * from tb_student where account="+account;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//结果集rs不为空，则取出rs的信息,设置stuBean1里面的信息
		if(rs.next()) {
			tempAccount=rs.getString("account");//将数据表中的account赋给全局变量tempAccount
			stuBean1.setName(rs.getString("name"));
			stuBean1.setMajor(rs.getString("major"));
			stuBean1.setGrade(rs.getString("grade"));
			stuBean1.setAccount(rs.getString("account"));
			stuBean1.setPassword(rs.getString("password"));
			stuBean1.setBackNews("查询成功，查询结果如下：");
			stuBean1.setSuccess(true);
		}else {
			stuBean1.setBackNews("查询失败:您要查询的考生账号不存在，请重新输入");
			stuBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		stuBean1.setBackNews("查询失败:错误为"+e.toString());
		stuBean1.setSuccess(false);
	}
	   
	   //转发查询结果到alterStuMsg.jsp页面
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterStuMsg.jsp");
       dispatcher.forward(request,response);
  }
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		String account=tempAccount.trim();//将tempId赋值给titleId
		String name=request.getParameter("name").trim();//考生姓名
		String major=request.getParameter("major").trim();//专业
		String grade=request.getParameter("grade").trim();//班级
		String password=request.getParameter("password").trim();//密码
		request.setAttribute("stuBean2",stuBean2);//请求域里面加了一个请求的参数stuBean2
		try{
			con=DriverManager.getConnection(uri);    
	    	//更新部分：根据titleId更新其它字段
	       String updateCondition="UPDATE tb_student set name="
	       		+ name
	       		+",major="+ major+",grade="+ grade
	       		+",password="+password
	       		+" where account="+account;
	       sql2=con.prepareStatement(updateCondition);
	       sql2.executeUpdate(); 
		   //查询部分：根据tempAccount查询修改后的该账号的所有信息
	       String queryString="select * from tb_student where account="+account;
		   sql1=con.createStatement();
	       ResultSet rs=sql1.executeQuery(queryString); 
	       if(rs.next()) {
	    	   stuBean2.setName(rs.getString("name"));
	    	   stuBean2.setMajor(rs.getString("major"));
	    	   stuBean2.setGrade(rs.getString("grade"));
	    	   stuBean2.setAccount(rs.getString("account"));
	    	   stuBean2.setPassword(rs.getString("password"));
	           stuBean2.setBackNews("修改成功，修改结果如下：");
	           stuBean2.setSuccess(true);
	       }
		   con.close();
		}
		catch(SQLException exp){
			stuBean2.setBackNews("修改失败:错误为"+exp.toString());
			stuBean2.setSuccess(false);
		}
			//转发修改结果到alterStuMsg.jsp页面
			RequestDispatcher dispatcher=
		    request.getRequestDispatcher("alterStuMsg.jsp");
		    dispatcher.forward(request,response);
    
	}
		
}
