package com.r2s.javabackend09.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_identifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "col_name")
	private String name;

	@Column(name = "expired_date")
	private Date expiredDate;

	@OneToOne(mappedBy = "identification")
//	@JsonIgnore
//	@JsonBackReference
//	@JsonManagedReference
	private User user;
}
