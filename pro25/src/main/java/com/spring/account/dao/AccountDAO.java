package com.spring.account.dao;

import org.springframework.dao.DataAccessException;

public interface AccountDAO {
	
	public void updateBalance1() throws DataAccessException;
	public void updateBalance2() throws DataAccessException;
}
