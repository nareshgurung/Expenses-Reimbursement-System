package com.ers.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ers.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name="employee_role", unique=true)
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	@OneToMany(mappedBy ="roleId")
	@JsonIgnore
	private List<Employee> empList;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int roleId, RoleEnum role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	
	public Role(RoleEnum role) {
		super();
		this.role = role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public RoleEnum getRole() {
		return role;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(role, roleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(role, other.role) && roleId == other.roleId;
	}
	@Override
	public String toString() {
		return "Role- roleId=" + roleId + "\n, role=" + role;
	}
	
	
}
