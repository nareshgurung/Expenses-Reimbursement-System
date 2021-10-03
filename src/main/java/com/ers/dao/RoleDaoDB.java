package com.ers.dao;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.Role;
import com.ers.utils.HibernateUtil;

public class RoleDaoDB {

	public RoleDaoDB() {
		
	}
	public void createRole(Role role) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(role);
		tx.commit();
	}
	
}
