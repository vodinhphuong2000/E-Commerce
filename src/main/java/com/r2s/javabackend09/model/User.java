package com.r2s.javabackend09.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "col_name")
	private String name;

	@Column(name = "col_age", nullable = true)
	private Long age;

	@Column(columnDefinition = " bit default 0")
	private Boolean isDeleted = false;

	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "identification_id", referencedColumnName = "id", nullable = false)
//	@JsonManagedReference
//	@JsonBackReference
	private Identification identification;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();

//	// 1. su dung jointable
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "table_authorizations", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserRole> userRoles;
}
