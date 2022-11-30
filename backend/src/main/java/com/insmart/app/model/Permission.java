package com.insmart.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="permission_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permission;
	@Column(name="edit")
	private Boolean edit;

	@ManyToOne(targetEntity = Resource.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name="resource_id")
	@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
	private Resource resource;

	@ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "code","name","email","password","roles","permissions","description","handle","organizations","username"})
	private User user;

	@Column(name="view")
	private Boolean view;
	@Column(name="remove")
	private Boolean delete;

	public Long getPermission() {
		return permission;
	}

	public void setPermission(Long permission) {
		this.permission = permission;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
}