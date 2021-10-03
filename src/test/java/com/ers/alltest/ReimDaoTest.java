package com.ers.alltest;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ers.dao.ReimbursementDaoDB;
import com.ers.enums.RoleEnum;
import com.ers.enums.StatusEnum;
import com.ers.models.Employee;
import com.ers.models.Reimbursement;
import com.ers.models.Role;
import com.ers.models.Status;
import com.ers.utils.HibernateUtil;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ReimDaoTest {

@InjectMocks	
private static ReimbursementDaoDB rDao;
//	
@Mock
Session ses = HibernateUtil.getSession();

@Mock
private ReimbursementDaoDB reimDao;

@Mock
Role role = new Role(1, RoleEnum.employee);
Role role1 = new Role(2, RoleEnum.manager);
@Mock
Employee emp = new Employee(1, role, "naresh", "gurung", "grg01", "password");	
Employee emp1 = new Employee(2, role1, "dilip", "Rayamajhi", "dilrg", "password");
Status sts = new Status(1, StatusEnum.approved);

Reimbursement obj = new Reimbursement(1, 500, "travel", emp, emp1, sts);


@BeforeClass
public static void setUp() {
	rDao = new ReimbursementDaoDB();
}


	@Test
	public void getAllReim(){
		Session ses = HibernateUtil.getSession();
//		Query<Reimbursement> reim = ses.createQuery("FROM Reimbursement", Reimbursement.class);
		Mockito.when(reimDao.getAllReim()).thenReturn(Arrays.asList( 
						new Reimbursement(1, 500, "travel", emp, emp1, sts),
						new Reimbursement(2, 1254,"nnn", emp, emp1, sts)
										)
							);
		
		List<Reimbursement> lReim = reimDao.getAllReim();
		Assert.assertEquals("list sizid should be 2", 2, lReim.size());
	}
	@Test
	public void createReim() throws Exception {
		Session ses = HibernateUtil.getSession();
		Reimbursement reim = new Reimbursement();
		reimDao.createReim(obj);
		verify(reimDao, times(1)).createReim(obj);	
	}
	@Test
	public void getById() {
		Session ses = HibernateUtil.getSession();
		Mockito.when(reimDao.getById(1)).thenReturn(
				new Reimbursement(1, 500, "travel", emp, emp1, sts));
		Reimbursement reim = reimDao.getById(1);
		Assert.assertEquals("they should be equal", 1, reim.getReimId());
	}
	
	@Test
	public void updateReim() {
		int id = 1;
		Reimbursement obj = new Reimbursement(1, 500, "travel", emp, emp1, sts);
		reimDao.updatereim(obj, id);
		
		List<Reimbursement> all = reimDao.getAllReim();
//	    Reimbursement updatedProduct = ses.find(Reimbursement.class, 1);
////	     
		Assert.assertEquals(1, obj.getReimId());
	}
	@Test
	public void getByAuthorId() {
		Mockito.when(reimDao.getByAuthorId(1)).thenReturn(Arrays.asList(
				new Reimbursement(1, 500, "travel", emp, emp1, sts),
				new Reimbursement(2, 148, "travel", emp, emp1, sts))
				);
		
		List<Reimbursement> authid = reimDao.getByAuthorId(1);
		Assert.assertEquals("name should match", 500, authid.get(0).getAmount());
	}
	@Test
	public void getByStatus(){
		Mockito.when(reimDao.getByStatus(1)).thenReturn(Arrays.asList( 
						new Reimbursement(1, 500, "travel", emp, emp1, sts),
						new Reimbursement(2, 1254,"nnn", emp, emp1, sts)
										)
							);
		
		List<Reimbursement> stid = reimDao.getByStatus(1);
		System.out.println(stid);
		Assert.assertEquals("status should match", StatusEnum.approved, stid.get(0)
				.getStatus().getStatus());
	}

}























