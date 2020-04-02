package control.handleTest;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.TestBean;

@WebServlet("/stu/newTest")
public class NewTest extends HttpServlet{

   public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      HttpSession session=request.getSession(true);
	      //Ïú»Ùsession¶ÔÏó
	      session.removeAttribute("testBean1");
	      session.removeAttribute("testBean2");
	      response.sendRedirect("/examOnline/stu/indexStu.jsp");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
