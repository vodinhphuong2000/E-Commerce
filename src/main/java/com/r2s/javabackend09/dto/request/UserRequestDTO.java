package com.r2s.javabackend09.dto.request;

import java.util.Date;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.r2s.javabackend09.model.Identification;
import com.r2s.javabackend09.model.User;

import lombok.Data;

@Data
public class UserRequestDTO {
	private String name;
	private Long age;
	private String userName;
	@NonNull
	private String identificationName;
	@NonNull
	private Date expiredDate;

	public User toUser() {
		User user = new User();

		user.setName(this.name);
		user.setAge(this.age);
		user.setUserName(this.userName);
		if (Objects.nonNull(identificationName) && Objects.nonNull(expiredDate)) {
			Identification identification = new Identification();
			identification.setName(identificationName);
			identification.setExpiredDate(expiredDate);

			user.setIdentification(identification);
		}

		return user;
	}
}
