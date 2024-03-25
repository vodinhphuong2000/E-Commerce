package com.r2s.javabackend09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.UserRoleRequestDTO;
import com.r2s.javabackend09.service.UserRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/userroles")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@PostMapping("")
	public ResponseEntity<?> addRole(@RequestBody UserRoleRequestDTO dto) {
		return BaseResponseController.success(this.userRoleService.addRole(dto));
	}

}
