package com.spring.account.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.account.dao.AccountDAO;

@Transactional(propagation=Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {
	private AccountDAO accDAO;
	
	public void setAccDAO(AccountDAO accDAO) {
	this.accDAO = accDAO;
	}
	
	@Override
	public void sendMoney() throws Exception {
		accDAO.updateBalance1();
		accDAO.updateBalance2();
		System.out.println("sendMoney");
	}
}

	
