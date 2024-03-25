package com.r2s.javabackend09.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.dto.request.UserRoleRequestDTO;
import com.r2s.javabackend09.model.Role;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.model.UserRole;
import com.r2s.javabackend09.repository.RoleRepository;
import com.r2s.javabackend09.repository.UserRepository;
import com.r2s.javabackend09.repository.UserRoleRepository;

@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public UserRole addRole(UserRoleRequestDTO dto) {
		// validation
		// ....
		Optional<User> foundUserOptional = this.userRepository.findById(dto.getUserId());
		if (foundUserOptional.isEmpty()) {
			// throw
		}
		User foundUser = foundUserOptional.get();

		Optional<Role> foundRoleOptional = this.roleRepository.findById(dto.getRoleId());
		if (foundRoleOptional.isEmpty()) {
			// throw...
		}
		Role foundRole = foundRoleOptional.get();

		// build UserRole
		UserRole userRole = new UserRole();
		userRole.setUser(foundUser);
		userRole.setRole(foundRole);
		userRole.setIssuedDate(new Date());

		// 1. luu theo user
//		foundUser.getUserRoles().add(userRole);
//		this.userRepository.save(foundUser);
//		return userRole;

		// 2. luu theo role

		// 3. luu theo userrole
		return this.userRoleRepository.save(userRole);
	}
}
