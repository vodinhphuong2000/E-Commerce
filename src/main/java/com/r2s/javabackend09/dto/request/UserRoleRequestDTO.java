package com.r2s.javabackend09.dto.request;

import lombok.Data;

@Data
public class UserRoleRequestDTO {
	private Integer userId;
	private Integer roleId;
}
