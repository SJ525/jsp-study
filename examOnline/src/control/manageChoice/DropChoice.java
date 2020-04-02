package control.manageChoice;

import model.ChoiceModel;//导入数据模型ChoiceModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/dropChoice")
public class DropChoice extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//查询
	PreparedStatement sql2;//删除
	String tempId="";
	//创建ChoiceModel的javaBean对象
	ChoiceModel choiceBean=new ChoiceModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
    try{ 
           Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){} 
 }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
	   String titleId=request.getParameter("titleId").trim();//获取要查找而输入的题目序号   
	   request.setAttribute("choiceBean",choiceBean);//请求域里面加了一个请求的参数choiceBean
	   try {
		con=DriverManager.getConnection(uri);
		//查询语句：查询题序为titleId的所有选择题信息
		String queryString="select * from tb_choice where titleId="+titleId;	
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//结果集rs不为空，则取出rs的信息,设置choiceBean里面的信息
		if(rs.next()) {
			tempId=rs.getString("titleId");//将数据表中的titleId赋给全局变量tempId
			choiceBean.setTitleId(rs.getString("titleId"));
			choiceBean.setTitleStem(rs.getString("titleStem"));
			choiceBean.setOptionA(rs.getString("optionA"));
			choiceBean.setOptionB(rs.getString("optionB"));
			choiceBean.setOptionC(rs.getString("optionC"));
			choiceBean.setOptionD(rs.getString("optionD"));
			choiceBean.setAnswerRight(rs.getString("answerRight"));
			choiceBean.setAnswerExplain(rs.getString("answerExplain"));
			choiceBean.setBackNews("查询成功，查询结果如下：");
			choiceBean.setSuccess(true);
		}else {
			choiceBean.setBackNews("查询失败:您要查询的题序不存在，请重新输入");
			choiceBean.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		choiceBean.setBackNews("查询失败:错误为"+e.toString());
		choiceBean.setSuccess(false);
	}   
	   //转发查询结果到dropChoice.jsp页面
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("dropChoice.jsp");
       dispatcher.forward(request,response);
    
 }
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
		 String titleId=tempId.trim();//将tempId赋值给titleId
		 
		 try {
			con=DriverManager.getConnection(uri);    
			//删除部分：根据titleId该行数据
		   String dropCondition="DELETE FROM tb_choice WHERE titleId=" +titleId;
		   sql2=con.prepareStatement(dropCondition);
		   sql2.executeUpdate();
		   try {
		      	  response.setContentType("text/html;charset=gb2312");
				  PrintWriter out=response.getWriter();
				  out.println("<html><body>");
				  out.println("<div align=\"center\">");
				  out.println("<h2>"+"删除题目成功"+"</h2>");
				  out.println("<a href =dropChoice.jsp>返回删除页面</a><br>");
				  out.println("<br><a href =showChoice.jsp>查看剩余选择题</a>");
				  out.println("</div>");
				  out.println("</body></html>");
				} catch (Exception e) {
					e.printStackTrace();
					fail(request,response,e.toString());
				}
		   con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			fail(request,response,e.toString());
		} 
	 }
	 public void fail(HttpServletRequest request,HttpServletResponse response,
	           String backNews) {
		response.setContentType("text/html;charset=gb2312");
		try {
			PrintWriter out=response.getWriter();
			out.println("<html><body align=\"center\">");
			out.println("<div align=\"center\">");
			out.println("<h2>"+"删除失败！"+"</h2>") ;
			out.println("<h2>原因：</h2>"+backNews) ;
			out.println("<a href =dropChoice.jsp>返回重试</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
		catch(IOException exp){} 
	}
}
