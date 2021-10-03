package com.ers.dao;

import org.hibernate.Session;

import com.ers.models.Employee;
import com.ers.models.Status;
import com.ers.utils.HibernateUtil;

public class StatusDaoDB {

	public StatusDaoDB() {
		super();
	}
	public Status getById(int id) {
		Session ses = HibernateUtil.getSession();
		Status emp = ses.createQuery("from Status where id=" +id, Status.class).uniqueResult();
		return emp;
	}
	

}
