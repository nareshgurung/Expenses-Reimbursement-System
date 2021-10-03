package com.ers.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hamcrest.Matcher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.Reimbursement;
import com.ers.utils.HibernateUtil;

public class ReimbursementDaoDB {

	public List<Reimbursement> getAllReim(){
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		Query<Reimbursement> reim = ses.createQuery("FROM Reimbursement", Reimbursement.class);
		tx.commit();
		return reim.list();
	}
	
	public void createReim(Reimbursement obj) {
		
		Transaction tx = null;
		try {
			
			Session ses = HibernateUtil.getSession();
			tx = ses.beginTransaction();
			ses.save(obj);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();	
		}
	}
	public Reimbursement getById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement reim = ses.createQuery("from Reimbursement where id=" +id, Reimbursement.class).uniqueResult();
		return reim;
	}
	
	public void updatereim(Reimbursement obj, int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		Reimbursement reim = ses.load(Reimbursement.class, id);
		reim.setStatus(obj.getStatus());
		ses.update(reim);
		tx.commit();
	}
	public List<Reimbursement> getByAuthorId(int id) {
		List<Reimbursement> retrievereim = null;
		
			Session ses = HibernateUtil.getSession();
			Transaction tx = ses.beginTransaction();
			CriteriaBuilder cb =ses.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			
			Root<Reimbursement> root = cq.from(Reimbursement.class);
			
			cq.select(root).where(cb.equal(root.get("author"), id));
			
			Query<Reimbursement> query =ses.createQuery(cq);
		
			retrievereim = query.list();
			tx.commit();
		
		return retrievereim;
	}
	public List<Reimbursement> getByStatus(int id){
		List<Reimbursement> retrievereim = null;
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		CriteriaBuilder cb =ses.getCriteriaBuilder();
		CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
		
		Root<Reimbursement> root = cq.from(Reimbursement.class);
		
		cq.select(root).where(cb.equal(root.get("status"), id));
		
		Query<Reimbursement> query =ses.createQuery(cq);
		
		retrievereim = query.list();
		tx.commit();
		
		System.out.println(retrievereim);
		return retrievereim;
		
	}
	
	public List<Reimbursement> filterAmout(int amount){
		List<Reimbursement> filteredAmount= null;
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
		
		Root<Reimbursement> root = cq.from(Reimbursement.class);
		
		cq.select(root).where(cb.greaterThanOrEqualTo(root.get("amount"), amount));
		
		Query<Reimbursement> query = ses.createQuery(cq);
		
		filteredAmount =query.list();
		
		return filteredAmount;	
	}
	
	public List<Double> avgAmount() {
		List<Double> rm = null;
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		
		Root<Reimbursement> root = cq.from(Reimbursement.class);
		
		cq.select(cb.avg(root.get("amount"))).groupBy(root.get("author"));
		
		Query<Double> query = ses.createQuery(cq);
		
		rm =query.list();
		
		tx.commit();
		
		return rm;
	}
	public void deleteReim(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		Query query = ses.createQuery("delete Reimbursement where reimId="+ id, Reimbursement.class);
		query.executeUpdate();
		tx.commit();
	}

}








