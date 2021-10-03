package com.ers.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="reim")
public class Reimbursement {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reim_id")
	private int reimId;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="author_id")
	private Employee author;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="resolver_id")
	private Employee resolver;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="status_id")
	private Status status;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimId, Status statusId) {
		this.reimId= reimId;
		this.status=statusId;
		
	}
	public Reimbursement(int amount, String description) {
		this.amount=amount;
		this.description=description;
	}
	public Reimbursement(int amount, String description, Employee author, Employee resolver,
			Status status) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
	}
	public Reimbursement(int reimId, int amount, String description, Employee author, Employee resolver,
			Status status) {
		super();
		this.reimId = reimId;
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
	}
	public int getReimId() {
		return reimId;
	}
	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Employee getAuthor() {
		return author;
	}
	public void setAuthor(Employee author) {
		this.author = author;
	}
	public Employee getResolver() {
		return resolver;
	}
	public void setResolver(Employee resolver) {
		this.resolver = resolver;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, reimId, resolver, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return amount == other.amount && Objects.equals(author, other.author)
				&& Objects.equals(description, other.description) && reimId == other.reimId
				&& Objects.equals(resolver, other.resolver) && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", amount=" + amount + ", description=" + description + ", author="
				+ author + ", resolver=" + resolver+ ", status=" + status + "]";
	}
	
	
}
