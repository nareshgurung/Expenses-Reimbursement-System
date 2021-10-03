package com.ers.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.Employee;
import com.ers.utils.HibernateUtil;

public class EmployeeDaoDB {
	
	public EmployeeDaoDB() {
		
	}
	public void createEmployee(Employee obj) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(obj);
		tx.commit();
	}
	
	public List<Employee> getAllEmployee(){
		Session ses = HibernateUtil.getSession();
		Query<Employee> emp = ses.createQuery("FROM Employee", Employee.class);
		return emp.list();
	}
	public Employee getByUsername(String username) {
		
		Session ses = HibernateUtil.getSession();
		@SuppressWarnings("deprecation")
		Employee user = ses.createQuery("FROM Employee where username=:username", Employee.class).setString("username", username).uniqueResult();
		System.out.println("something");
		return user;
	}
	public Employee getById(int id) {
		Session ses = HibernateUtil.getSession();
		Employee emp = ses.createQuery("from Employee where id=" +id, Employee.class).uniqueResult();
		return emp;
	}
	
}
