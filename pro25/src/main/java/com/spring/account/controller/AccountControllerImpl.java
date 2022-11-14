package com.spring.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.account.service.AccountService;

public class AccountControllerImpl extends MultiActionController implements AccountController 
{
	private AccountService accService ;
	public void setAccService(AccountService accService) {
		this.accService = accService;
	}
	
	@Override
	public ModelAndView sendMoney(HttpServletRequest request, HttpServletResponse repsonse)
	throws Exception{
		ModelAndView mav = new ModelAndView();
		accService.sendMoney();
		mav.setViewName("result");
		return mav;
	}
}
