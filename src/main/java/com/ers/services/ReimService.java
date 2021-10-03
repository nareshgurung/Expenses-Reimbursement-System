package com.ers.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ers.dao.EmployeeDaoDB;
import com.ers.dao.ReimbursementDaoDB;
import com.ers.dao.StatusDaoDB;
import com.ers.models.Employee;
import com.ers.models.Reimbursement;
import com.ers.models.Status;

public class ReimService {
	
	private EmployeeDaoDB eDao;
	private ReimbursementDaoDB rDao;
	private StatusDaoDB sDao;
	
	public ReimService() {
	}
	public ReimService(ReimbursementDaoDB rDao, EmployeeDaoDB eDao, StatusDaoDB sDao) {
		this.rDao = rDao;
		this.sDao = sDao;
		this.eDao = eDao;
	}
	
	public void addReim(Reimbursement obj, int id) {
		
		Employee emp = eDao.getById(id);
		System.out.println(emp);
		Employee mn = eDao.getById(4);	
		Status st = sDao.getById(6);
		Reimbursement obj1 = new Reimbursement(obj.getAmount(), obj.getDescription(), emp, mn, st);
		rDao.createReim(obj1);
	}
	public void update(int reim_id, int status_id) {
		System.out.println(reim_id);
		Reimbursement rm = rDao.getById(reim_id);
		
		
		Status st = sDao.getById(status_id);
		
		Reimbursement obj = new Reimbursement();
		obj.setReimId(rm.getReimId());
		obj.setStatus(st);
		rDao.updatereim(obj, rm.getReimId());
	}
	
	public Reimbursement getById(int id) {
		return rDao.getById(id);
	}
	public List<Reimbursement> getAllReim() {
		return rDao.getAllReim();
	}
	public List<Reimbursement> getByAuthorId(int id) {
		return rDao.getByAuthorId(id);
	}
	public List<Reimbursement> getByStatus(int id) {
		return rDao.getByStatus(id);
	}
	public void deleteReim(int id) {
		Reimbursement checkReim = rDao.getById(id);
		int rId = checkReim.getReimId();
		if(rId == id) {
			rDao.deleteReim(id);
		}
		else {
			System.out.println("id does not match");
		}
	}
	public List<Double> avgAmount() {
		return rDao.avgAmount();
	}
	
	
	
}
