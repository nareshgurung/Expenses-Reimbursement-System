import com.ers.controller.ErsController;
import com.ers.dao.EmployeeDaoDB;
import com.ers.dao.ReimbursementDaoDB;
import com.ers.dao.RoleDaoDB;
import com.ers.dao.StatusDaoDB;
import com.ers.enums.RoleEnum;
import com.ers.models.Employee;
import com.ers.models.Role;
import com.ers.services.EmployeeService;
import com.ers.services.ReimService;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {
	
	private static RoleDaoDB rDao = new RoleDaoDB();
	private static EmployeeDaoDB eDao = new EmployeeDaoDB();
	private static StatusDaoDB sDao = new StatusDaoDB();	
	private static EmployeeService eServ = new EmployeeService(eDao);
	private static ReimbursementDaoDB rmDao = new ReimbursementDaoDB();
	private static ReimService rServ = new ReimService(rmDao, eDao, sDao);
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);
		ErsController.init(app);
		app.after(ctx ->{
			ctx.res.addHeader("access-Control-Allow-Origin", "*");	
		});
		
		app.config.addStaticFiles("/static", Location.CLASSPATH);
		
//	    System.out.println(rmDao.avgAmount() + " average ");
			
//		Role role = new Role(RoleEnum.employee);
//		rDao.createRole(role);
//		
////		Role role;
////		
//		Employee emp = new Employee(role, "dilip", "Rai", "dlpr", "password");
//		eDao.createEmployee(emp);
////		
//		Role role1 = new Role(RoleEnum.manager);
//		rDao.createRole(role1);
//////		
//		Employee emp1 = new Employee(role, "Rick", "Ray", "rick01", "password");
//		eDao.createEmployee(emp1);
//		
//		Status st = new Status(StatusEnum.painding);
//		Status stapp = new Status(StatusEnum.declined);
		
		
//		
//		System.out.println(eServ.getAllEmployee());
//		System.out.println(rmDao.getAllReim());
//		System.out.println(eDao.getByUsername("gigrm"));
//		eServ.logIn("dlpr", "passworde");
//		Reimbursement rr = new Remibursement(1450, "food"); 
	
//		rServ.addReim(1522, "Hotel", 2, 4, 9);
//		
//		rServ.update(14, 10);
//		System.out.println(rmDao.getById(12));
		
//		Reimbursement reim = new Reimbursement(500, "food", emp, emp1, st);
//		rmDao.createReim(reim);
//		
//		Reimbursement reim1 = new Reimbursement(5000, "travel", emp, emp1, st);
//		rmDao.createReim(reim1);
//		
//		Reimbursement reim2 = new Reimbursement(500, "food", emp, emp1, stapp);
//		rmDao.createReim(reim2);
	}
}
