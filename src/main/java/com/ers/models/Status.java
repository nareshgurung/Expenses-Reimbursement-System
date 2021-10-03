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

import com.ers.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="status_id")
	private int statusId;
	
	@Column(name="status", unique=true)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@OneToMany(mappedBy="status")
	@JsonIgnore
	private List<Reimbursement> reim;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(StatusEnum status) {
		super();
		this.status = status;
	}
	
	public Status(int statusId, StatusEnum status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public List<Reimbursement> getReim() {
		return reim;
	}
	public void setReim(List<Reimbursement> reim) {
		this.reim = reim;
	}
	@Override
	public int hashCode() {
		return Objects.hash(status, statusId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return status == other.status && statusId == other.statusId;
	}
	@Override
	public String toString() {
		return "Status - statusId=" + statusId + "\n, status=" + status;
	}
	
	
}
