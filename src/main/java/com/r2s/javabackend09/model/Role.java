package com.r2s.javabackend09.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "col_name")
	private String name;

//	@ManyToMany(mappedBy = "roles")
//	private List<User> users;

	@OneToMany(mappedBy = "role")
	@JsonManagedReference
	private List<UserRole> userRoles;
}
