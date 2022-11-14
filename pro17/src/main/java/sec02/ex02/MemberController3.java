package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec02.ex02.MemberDAO;
import sec02.ex02.MemberVO;


@WebServlet("/member3/*")
public class MemberController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      MemberDAO memberDAO; 
     
 
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);

	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action= request.getPathInfo();
		System.out.println("action:" + action);
		
		if(action == null || action.equals("/listMembers.do"))
		{
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
			System.out.println("if");
		} else if (action.equals("/addMember.do"))
		{
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member3/listMembers.do";
		} else if(action.equals("/memberForm.do"))
		{
			nextPage="/test03/MemberForm.jsp";
		
		} else if (action.equals("/modMemberForm.do"))
		{
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);
			request.setAttribute("memInfo", memInfo);
			System.out.println("iii");
			nextPage = "/test03/modMemberForm.jsp";
			
		} else if (action.equals("/modMember.do"))
		{
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			request.setAttribute("msg", "modified");
			nextPage = "/member3/listMembers.do";
		} else if(action.equals("/delMember.do"))
		{
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage= "/member3/listMembers.do";
		} else
		{
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
			System.out.println("else");
		}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
}

		
