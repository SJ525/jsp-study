package control.manageStuMsg;
import model.StuModel;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/dropStuMsg")
public class DropStuMsg extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//查询
	PreparedStatement sql2;//删除
	String tempAccount="";
	//创建StuModel的javaBean对象
	StuModel stuBean=new StuModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	    try{ 
	           Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){} 
	 }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		   String account=request.getParameter("account");//获取要查找而输入的考生账号   
		   request.setAttribute("stuBean",stuBean);//请求域里面加了一个请求的参数stuBean
		   try {
			con=DriverManager.getConnection(uri);
			//查询语句：查询考生账号为account的所有考生信息
			String queryString="select * from tb_student where account="+account;
			
			sql1=con.createStatement();
	        ResultSet rs=sql1.executeQuery(queryString);
			//结果集rs不为空，则取出rs的信息,设置stuBean里面的信息
			if(rs.next()) {
				tempAccount=rs.getString("account");//将数据表中的account赋给全局变量tempAccount
				stuBean.setName(rs.getString("name"));
				stuBean.setMajor(rs.getString("major"));
				stuBean.setGrade(rs.getString("grade"));
				stuBean.setAccount(rs.getString("account"));
				stuBean.setPassword(rs.getString("password"));
				stuBean.setBackNews("查询成功，查询结果如下：");
				stuBean.setSuccess(true);
			}else {
				stuBean.setBackNews("查询失败:您要查询的考生账号不存在，请重新输入");
				stuBean.setSuccess(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stuBean.setBackNews("查询失败:错误为"+e.toString());
			stuBean.setSuccess(false);
		}
		   
		   //转发查询结果到dropStuMsg.jsp页面
		   RequestDispatcher dispatcher=
	       request.getRequestDispatcher("dropStuMsg.jsp");
	       dispatcher.forward(request,response);
    
	}
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
		 String account=tempAccount.trim();//将tempAccount赋值给account
		 
		 try {
			con=DriverManager.getConnection(uri);    
			//删除部分：根据account该行数据
		   String dropCondition="DELETE FROM tb_student WHERE account=" +account;
		   sql2=con.prepareStatement(dropCondition);
		   sql2.executeUpdate();
		   try {
		      	  response.setContentType("text/html;charset=gb2312");
				  PrintWriter out=response.getWriter();
				  out.println("<html><body>");
				  out.println("<div align=\"center\">");
				  out.println("<h2>"+"删除考生成功"+"</h2>");
				  out.println("<a href =dropStuMsg.jsp>返回删除页面</a><br>");
				  out.println("<br><a href =showStuMsg.jsp>查看剩余考生</a>");
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
			out.println("<h2>"+"删除考生失败！"+"</h2>") ;
			out.println("<h2>原因：</h2>"+backNews) ;
			out.println("<a href =dropStuMsg.jsp>返回重试</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
		catch(IOException exp){} 
	}
}
