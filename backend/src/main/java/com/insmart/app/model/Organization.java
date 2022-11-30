package com.insmart.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GeneratorType;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the organizations database table.
 * 
 */
@Entity
@Table(name="organizations")
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ora_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oraId;

	@Column(name = "code")
	private String code;
	@Column(name = "description")
	@Lob
	private String description;
	@Column(name = "name")
	@Lob
	private String name;
	@Column(name = "orders")
	private Integer orders;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name="parent_id")
	@JsonIgnore
	private Organization organization;

	@ManyToMany(mappedBy="organizations")
	private Set<User> users ;

	public Organization() {
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Long getOraId() {
		return this.oraId;
	}

	public void setOraId(Long oraId) {
		this.oraId = oraId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@JsonIgnoreProperties("organizations")
	public Set<User> getUsers() {
		return this.users;
	}
}