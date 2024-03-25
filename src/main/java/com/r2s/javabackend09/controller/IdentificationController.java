package com.r2s.javabackend09.controller;

import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.service.IdentificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/identifications")
public class IdentificationController {
	@Autowired
	private IdentificationService identificationService;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return BaseResponseController.success(this.identificationService.getAll());
	}

}
