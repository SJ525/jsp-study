package control.common;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//�����˳���½
@WebServlet("/stu/handleStuExit")
public class HandleStuExit extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      HttpSession session=request.getSession(true); 
      session.invalidate();              //�����û���session����
      response.sendRedirect("/examOnline/index.jsp"); //������ҳ 
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      doPost(request,response);
   }
}
