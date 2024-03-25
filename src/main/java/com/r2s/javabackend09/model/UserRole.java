package com.r2s.javabackend09.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "table_authorizations")
@Data
public class UserRole {
//	@EmbeddedId
//	private UserRoleId id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
//	@MapsId(value = "userId")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@JsonBackReference
//	@MapsId(value = "roleId")
	private Role role;

	@Column(name = "issued_date")
	private Date issuedDate;
}

//@Embeddable
//@Data
//class UserRoleId {
//	@Column(name = "user_id")
//	private Integer userId;
//
//	@Column(name = "role_id")
//	private Integer roleId;
//}
