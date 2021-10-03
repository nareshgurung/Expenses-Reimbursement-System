package com.ers.services;

import java.util.ArrayList;
import java.util.List;

import com.ers.dao.EmployeeDaoDB;
import com.ers.models.Employee;
public class EmployeeService {
	
	private EmployeeDaoDB eDao;
	
	public EmployeeService(EmployeeDaoDB eDao) {
		this.eDao =eDao;
	}
	public List<Employee> getAllEmployee(){
			List<Employee> elist = eDao.getAllEmployee();
				return elist;
	}
	public Employee logIn(String username, String password) {
		
		Employee uname = eDao.getByUsername(username);
		if(uname.getUsername() != null) {
			if(uname.getPassword().equals(password)) {
				System.out.println("you are logged in as " + uname.getUsername()+ " "+ uname.getPassword());	
			}
			else {
				System.out.println("wrong credentials");
			}
		}
		
		return uname;
	}
}
