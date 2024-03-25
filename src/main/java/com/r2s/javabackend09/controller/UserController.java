package com.r2s.javabackend09.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.UserRequestDTO;
import com.r2s.javabackend09.dto.response.UserResponseDTO;
import com.r2s.javabackend09.exception.UserAlreadyExistsException;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.exception.ValidationException;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.service.UserService;
import com.r2s.javabackend09.utils.ResponseCode;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	// controller -> service -> repository -> db
//	@RequestMapping(path = "/", method = RequestMethod.GET)
	@GetMapping(path = "/hello") // /users/hello
	public String hello() {
		return "Hello, World!";
	}

	/**
	 * API get all users
	 * 
	 * @return users
	 */
	@GetMapping(path = "")
	public ResponseEntity<?> getAll() {
		return BaseResponseController.success(this.userService.getAll());
	}

	// get user by id
	// /users/id/<id>/age/.... -> path variable: template bat buoc, k thay doi duoc,
	// yeu cau truyen dung thu tu va du cac variable
	// /users?id=... -> request param -> khong yeu cau phai truyen het tat ca param,
	// khong yeu cau thu tu
	// ResponseEntity -> set status -> 1 cach de BE va FE giao tiep

//	@GetMapping(path = "/{id}/age/{age}")
//	public ResponseEntity<User> getByPathVariable(@PathVariable(name = "id") int id, int age) {
//		for (User user : users) {
//			if (user.getId() == id) {
//				return ResponseEntity.ok(user);
//			}
//		}
//
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
	@GetMapping(path = "/findById")
	public ResponseEntity<?> getByRequestParam(
			@RequestParam(name = "id", required = false, defaultValue = "0") int id) {
		try {
			User foundUser = this.userService.findById(id);
			return BaseResponseController.success(foundUser);
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	private Direction convertOrder(String value) {
		if (value.equalsIgnoreCase("ASC")) {
			return Direction.ASC;
		} else {
			return Direction.DESC;
		}
	}

	@GetMapping(path = "/searchByName")
	public ResponseEntity<?> search(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable, @RequestParam(name = "sortBy", required = false) String[] sorts) {
		try {
			Pageable sort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
			if (Objects.nonNull(sorts)) {
				if (sorts.length % 2 == 0) {
					List<Order> orders = new ArrayList<>();
					// age,DESC, id,DESC
					for (int i = 0; i < sorts.length; i += 2) {
						orders.add(new Order(convertOrder(sorts[i + 1]), sorts[i]));
					}
					sort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
				} else {
					return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
							"invalid sort params");
				}
			}

			return BaseResponseController.success(this.userService.findByName(name, sort));
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

//
	@PostMapping("")
	public ResponseEntity<?> addUser(@RequestBody UserRequestDTO user) {
		try {
			User addedUser = this.userService.saveUser(user);
			return BaseResponseController.success(new UserResponseDTO(addedUser));
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

//
	@PutMapping(path = "")
	public ResponseEntity<?> updateUser(@RequestBody User newUser) {
		try {
			User updatedUser = this.userService.updateUser(newUser);
			return BaseResponseController.success(updatedUser);
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

//
//	@PutMapping(path = "/{id}")
//	public ResponseEntity<User> updateUser2(@PathVariable(name = "id") int id, @RequestBody User newUser) {
//		for (User user : users) {
//			if (user.getId() == id) {
//				user.setAge(newUser.getAge());
//				user.setName(newUser.getName());
//				return ResponseEntity.ok(user);
//			}
//		}
//
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
		try {
			return BaseResponseController.success(this.userService.deleteUser(id));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		}
	}
}
