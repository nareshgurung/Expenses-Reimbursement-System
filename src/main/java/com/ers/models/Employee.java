package com.ers.models;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "employee_id")
	private int employeeId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="role_id", nullable =false)
	private Role roleId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username", unique=true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Role roleId, String firstName, String lastName, String username, String password) {
		super();
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public Employee(int employeeId, Role roleId, String firstName, String lastName, String username, String password) {
		super();
		this.employeeId = employeeId;
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Role getRoleId() {
		return roleId;
	}
	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employeeId, firstName, lastName, password, roleId, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return employeeId == other.employeeId && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(roleId, other.roleId) && Objects.equals(username, other.username);
	}
//	@Override
//	public String toString() {
//		return "employeeId=" + employeeId + "\n roleId=" + roleId.getRole()  + "\n firstName=" + firstName + ",\n lastName="
//				+ lastName + "\n username=" + username + "\n password=" + password;
//	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", roleId=" + roleId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", username=" + username + ", password=" + password + "]";
	}
		   
}
