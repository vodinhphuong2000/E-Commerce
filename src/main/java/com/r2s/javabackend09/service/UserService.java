package com.r2s.javabackend09.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.dto.request.UserRequestDTO;
import com.r2s.javabackend09.dto.response.UserResponseDTO;
import com.r2s.javabackend09.exception.UserAlreadyExistsException;
import com.r2s.javabackend09.exception.UserNotFoundException;
import com.r2s.javabackend09.exception.ValidationException;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<UserResponseDTO> getAll() {
		return this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();
	}

	private void validateUser(User user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}

		if (Objects.isNull(user.getAge()) || user.getAge() < 0) {
			throw new ValidationException("user.age must be positive");
		}

		if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}
	}

	private void validateUser(UserRequestDTO user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null");
		}

		if (Objects.isNull(user.getAge()) || user.getAge() < 0) {
			throw new ValidationException("user.age must be positive");
		}

		if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
			throw new ValidationException("user.name cannot be blank");
		}

		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank");
		}
	}

	public User saveUser(UserRequestDTO user) throws UserAlreadyExistsException, ValidationException {
		// kiem tra gia tri dau vao
		this.validateUser(user);

		// kiem tra xem da ton tai userName chua
		Optional<User> foundUser = this.userRepository.findByUserName(user.getUserName());
		if (foundUser.isPresent()) {
			throw new UserAlreadyExistsException();
		}

		return this.userRepository.save(user.toUser());
	}

	public User findById(Integer id) throws ValidationException, UserNotFoundException {
		if (Objects.isNull(id) || id <= 0) {
			throw new ValidationException("user.id must be positive");
		}

		Optional<User> foundUser = this.userRepository.findById(id);
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		return foundUser.get();
	}

	public List<User> findByName(String name, Pageable pageable) throws ValidationException {
		if (Objects.isNull(name)) {
			throw new ValidationException("name cannot be null");
		}

		if (name.isBlank()) {
			return this.userRepository.findAll(pageable).stream().map(x -> x).toList();
		}

//		return this.userRepository.findByName(name);
		return this.userRepository.findByNameContains(name, pageable);
//		return this.userRepository.findByNameLike("%" + name + "%");
//		return this.userRepository.findByNameCoChua(name);
	}

	public User updateUser(User newUser) throws ValidationException, UserNotFoundException, UserAlreadyExistsException {
		validateUser(newUser);

		// kiem tra user nay ton tai chua
		Optional<User> foundUser = this.userRepository.findById(newUser.getId());
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}
//		User user = foundUser.get();
//		user.setAge(newUser.getAge());

		// kiem tra userName moi da duoc su dung chua
		foundUser = this.userRepository.findByUserName(newUser.getUserName());
		if (foundUser.isPresent() && !newUser.getId().equals(foundUser.get().getId())) {
			throw new UserAlreadyExistsException();
		}

		return this.userRepository.save(newUser);
	}

	public boolean deleteUser(Integer id) throws UserNotFoundException {
		// kiem tra user nay ton tai chua
		Optional<User> foundUser = this.userRepository.findById(id);
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}

		this.userRepository.deleteById(id);
		return true;
	}
}
