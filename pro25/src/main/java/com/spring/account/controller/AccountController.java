package com.spring.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface AccountController {
	
	public ModelAndView sendMoney(HttpServletRequest request, HttpServletResponse repsonse)
	throws Exception;
}
