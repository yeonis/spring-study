package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {
//	 public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//		String userID = "";
//		String passwd = "";
//		String viewName=getViewName(request);
//		ModelAndView mav = new ModelAndView();
//		request.setCharacterEncoding("utf-8");
//		userID=request.getParameter("userID");
//		passwd=request.getParameter("passwd");
//		mav.addObject("userID",userID);
//		mav.addObject("passwd",passwd);
//		//mav.setViewName("result");
//		mav.setViewName(viewName);
//		System.out.println("ViewName:"+viewName);
//		return mav;
//	} 


 public ModelAndView login(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
	String userID = "";
	String passwd = "";
	ModelAndView mav = new ModelAndView();
	request.setCharacterEncoding("UTF-8");
	userID = request.getParameter("userID");
	passwd = request.getParameter("passwd");
	String viewName= getViewName(request);
	
	mav.addObject("userID",userID);
	mav.addObject("passwd",passwd);
	//mav.setViewName("result");
	mav.setViewName(viewName);
	System.out.println("ViewName:" + viewName);
	return mav;
}

public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
	request.setCharacterEncoding("utf-8");
	ModelAndView mav=new ModelAndView();
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	
	mav.addObject("id",id);
	mav.addObject("pwd",pwd);
	mav.addObject("name",name);
	mav.addObject("email",email);
	mav.setViewName("memberInfo");
	return mav;
	
}

private String getViewName(HttpServletRequest request) throws Exception {
	String contextPath = request.getContextPath();
	String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	if(uri == null || uri.trim().equals("")) {
		uri = request.getRequestURI();
	}
	//http://localhost:8080/member/listMember.do로 요청시
	int begin= 0; //
	if(!((contextPath==null) || ("".equals(contextPath)))) {
		begin = contextPath.length(); //전체 요청명의 길이 "/pro21" = 6
	}
	int end;
	if(uri.indexOf(";")!=-1) {
		end=uri.indexOf(";"); //요청 uri에 ;가 있을 경우 ; 문자 위치를 구함
	} else if(uri.indexOf("?")!=-1) {
		end=uri.indexOf("?"); //요청 uri에 ?가 있을 경우 ? 문자 위치를 구함
	}else {
		end=uri.length();
	}
	//http://localhost:8080/member/listMember.do 로 요청시 '.do'를 제거한
	//http://localhost:8080/member/listMember를 구한 후
	//다시 http://localhost:8080/member/listMember에서 역순으로 첫번째
	// '/' 위치를 구한 후 그 뒤 listMember구함
	
	String fileName=uri.substring(begin, end);
	if(fileName.indexOf(".")!=-1) {
		fileName=fileName.substring(0,fileName.lastIndexOf("."));
		//요청명에서 역순으로 최초 '.' 위치를 구한 후 '.do' 앞까지 문자열 구함
		
	} if(fileName.lastIndexOf("/")!=-1) {
		fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
		
	} return fileName;
}
}
