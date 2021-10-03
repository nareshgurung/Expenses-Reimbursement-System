package com.ers.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;

import com.ers.dao.EmployeeDaoDB;
import com.ers.dao.ReimbursementDaoDB;
import com.ers.dao.RoleDaoDB;
import com.ers.dao.StatusDaoDB;
import com.ers.logging.LoggingErs;
import com.ers.models.Employee;
import com.ers.models.Reimbursement;
import com.ers.services.EmployeeService;
import com.ers.services.ReimService;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class ErsController {
	private static RoleDaoDB rDao = new RoleDaoDB();
	private static EmployeeDaoDB eDao = new EmployeeDaoDB();
	private static StatusDaoDB sDao = new StatusDaoDB();	
	private static EmployeeService eServ = new EmployeeService(eDao);
	private static ReimbursementDaoDB rmDao = new ReimbursementDaoDB();
	private static ReimService rServ = new ReimService(rmDao, eDao, sDao);
	private static Javalin javalin;
	
	public static void init(Javalin app) {
		javalin = app;
		
		app.get("client/employees", ErsController::getAllEmplyee);
		app.get("reim/getbyId/:id", ErsController::getReimById);
		app.get("reim/getallreim",ErsController::getAllReim);
		app.post("reim/addreim", ErsController::addReim);
		app.post("reim/login", ErsController::login);
		app.post("reim/update", ErsController::update);
		app.get("reim/logout", ErsController::logout);
		app.get("reim/authorReim", ErsController::authorReim);
		app.get("rem/getByStatus/:id", ErsController::getByStatus);
		app.post("reim/delete/:id", ErsController::deleteReim);
		app.get("reim/getAvg", ErsController::getAvg);
		////
	
	}
	public static void getAvg(Context ctx){
		
		HttpSession ses = ctx.req.getSession(false);
		String uname = (String)ses.getAttribute("username");
		Employee empDetails = eDao.getByUsername(uname);
		String role = empDetails.getRoleId().getRole().toString();
		if(role.equalsIgnoreCase("manager")) {			
			List<Double> avg = rServ.avgAmount();
			System.out.println(avg);
			ctx.json(avg);
			LoggingErs.logger.info("succesfully retrieved the average amount. ");
		}else {
			ctx.html("<!DOCTYPE> <html>"
					+ "<head><title>bad Credential</title>"
					+ "<body><h3>Please login by valid credentials</h3>"
					+ "</body>"
					+ "</html>");
			LoggingErs.logger.warn("Some one tried to login with bad credentials");
		}
		
	}
	
	public static void deleteReim(Context ctx) {
		int id =Integer.parseInt(ctx.pathParam("id"));
		rServ.deleteReim(id);
	}
	
	public static void getByStatus(Context ctx) {
		
		HttpSession ses = ctx.req.getSession(false);
		int id = Integer.parseInt(ctx.pathParam("id"));
//		System.out.println(id);
		List<Reimbursement> reimStatus = rServ.getByStatus(id);
		
		if(ses != null) {
		List<String> rList =new ArrayList<>();
		for(int i = 0; i<reimStatus.size(); i++) {
			rList.add(Integer.toString(reimStatus.get(i).getReimId()));
			rList.add(Integer.toString(reimStatus.get(i).getAmount()));
			rList.add(reimStatus.get(i).getDescription());
			rList.add(reimStatus.get(i).getAuthor().getFirstName());
			rList.add(reimStatus.get(i).getResolver().getFirstName());
			rList.add(reimStatus.get(i).getStatus().getStatus().toString());
			}
//		
		 ctx.json(rList);
		 LoggingErs.logger.info("infor retrieved succesfully by status");
		}
		LoggingErs.logger.info("there is no user");
	}
	public static void authorReim(Context ctx) {
		HttpSession ses = ctx.req.getSession(false);
		
		if(ses != null) {
			
			String uname = (String)ses.getAttribute("username");
			Employee ename = eDao.getByUsername(uname);
			int id = ename.getEmployeeId();
		
			List<Reimbursement> authorReim = rServ.getByAuthorId(id);
			
			
			List<String> rList =new ArrayList<>();
			for(int i = 0; i<authorReim.size(); i++) {
				rList.add(Integer.toString(authorReim.get(i).getReimId()));
				rList.add(Integer.toString(authorReim.get(i).getAmount()));
				rList.add(authorReim.get(i).getDescription());
				rList.add(authorReim.get(i).getAuthor().getFirstName());
				rList.add(authorReim.get(i).getResolver().getFirstName());
				rList.add(authorReim.get(i).getStatus().getStatus().toString());
			}

			ctx.json(rList);
			 LoggingErs.logger.info("User reimbursement succesfully retrieved. ");

//			ctx.redirect("/empreim.html");
		}
	}
	public static void logout(Context ctx) {
		HttpSession session = ctx.req.getSession();
		if(session != null) session.invalidate();
		 LoggingErs.logger.info("Yor are logged out ");

	}
	
	public static void update(Context ctx) {
		
		HttpSession ses = ctx.req.getSession(false);
		int reimId = Integer.parseInt(ctx.req.getParameter("reimId"));
		System.out.println(reimId);
		int statusId = Integer.parseInt(ctx.req.getParameter("status"));
		System.out.println(statusId);
		
		if(ses != null) {
			String uname = (String)ses.getAttribute("username");	
			Employee empDetails = eDao.getByUsername(uname);
			String role = empDetails.getRoleId().getRole().toString();
			System.out.println(role);
			if(role.equalsIgnoreCase("manager")) {				
				rServ.update(reimId, statusId);		
				ctx.redirect("/mandash.html");
				 LoggingErs.logger.info(" Your have updated " + role + "reimbursement by" );
			}
		}else {
			ctx.html("<!DOCTYPE> <html>"
					+ "<head><title>bad Credential</title>"
					+ "<body><h3>Go to login and try again!!!</h3>"
					+ "</body>"
					+ "</html>");
		}
		 LoggingErs.logger.warn("No user credentials ");
		
	}
	public static void login(Context ctx) {
//		Employee emp= ctx.bodyAsClass(Employee.class);
		String uname = ctx.req.getParameter("username");
		String pword = ctx.req.getParameter("password");
		String frontRole = ctx.req.getParameter("role");
		System.out.println(frontRole);
			
//		System.out.println(emp.getUsername());
//		Employee ename = eDao.getByUsername(emp.getUsername());
		Employee ename = eDao.getByUsername(uname);
		String role = ename.getRoleId().getRole().toString();
		System.out.println(role);
		
		try {
			if((ename.getUsername().equals(uname)) && (ename.getPassword().equals(pword))) {
					HttpSession ses = ctx.req.getSession();
					ses.setAttribute("username", uname);
					eServ.logIn(uname, pword);
					
					 LoggingErs.logger.info("User was logged in. ");
					 
					if(frontRole.equalsIgnoreCase("employee"))
						ctx.redirect("/empreim.html");

					else if(frontRole.equalsIgnoreCase("manager"))
						ctx.redirect("/mandash.html");
			}
		}catch(Exception e) {
			System.out.println("wrong validation");
			ctx.status(403);
//			ctx.html("<script>alert(wrong credentials)</script>");
//			ctx.redirect("/emplogin.html");
			e.printStackTrace();
			LoggingErs.logger.warn("User tried to logged in by wrong credentials. ");
			
		}
		
		
	}
	public static void addReim(Context ctx) {
		
		HttpSession session = ctx.req.getSession(false);
		if(session != null) {
			
			String uname = (String)session.getAttribute("username");
			Employee ename = eDao.getByUsername(uname);
			int id = ename.getEmployeeId();
			int amount = Integer.parseInt(ctx.req.getParameter("amount"));
			System.out.println(amount);
			String desc = ctx.req.getParameter("description");
			
			Reimbursement reim = new Reimbursement(amount, desc);
			System.out.println(reim.getAmount());
			reim.getDescription();
			rServ.addReim(reim, id);
			ctx.redirect("/empreim.html");
			
			 LoggingErs.logger.info(" You have succesfully created the Reimbursement requested. ");
		}
	}
	
	public static void getAllReim(Context ctx) throws IOException {
		

		HttpSession session = ctx.req.getSession(false);
		List<Reimbursement> reim = rServ.getAllReim();		
//		session.setAttribute("username", username);
	
		
		if(session != null) {
			
			System.out.println(reim.size());
			List<String> rList = new ArrayList<>();
			System.out.println(rList.size());
////		
		for(int i = 0; i<reim.size(); i++) {
			rList.add(Integer.toString(reim.get(i).getReimId()));
			rList.add(Integer.toString(reim.get(i).getAmount()));
			rList.add(reim.get(i).getDescription());
			rList.add(reim.get(i).getAuthor().getFirstName());
			rList.add(reim.get(i).getResolver().getFirstName());
			rList.add(reim.get(i).getStatus().getStatus().toString());
		}
		System.out.println(rList);
		ctx.json(rList);
		
		 LoggingErs.logger.info(" You have succesfully retrieved all the reimbursement. ");
		}
	}
	public static void getAllEmplyee(Context ctx) {
		ctx.status(200);
		ctx.result("you are in");
//		System.out.println(eServ.getAllEmployee());
		List<Employee> emp = eServ.getAllEmployee();
		System.out.println(emp);
		ctx.result(emp.toString());
		
	}
	public static void getReimById(Context ctx) {
	int id = Integer.parseInt(ctx.pathParam("id"));
	
	Reimbursement reim = rServ.getById(id);
	List<String>rList = new ArrayList<>();
	rList.add(Integer.toString(reim.getReimId()));
	rList.add(reim.getAuthor().getFirstName().toString());
	rList.add(reim.getResolver().getFirstName().toString());
	rList.add(reim.getStatus().getStatus().toString());
	rList.add(reim.getDescription());
	rList.add(Integer.toString(reim.getAmount()));
	ctx.json(rList);
//	ctx.result(reim.toString());
	
	}
}