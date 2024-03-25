package com.r2s.javabackend09.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS("success", "success"), 
	USER_ALREADY_EXISTS("user.exists", "user already exists"),
	VALIDATION_EXCEPTION("validation", null),
	USER_NOT_FOUND("user.notfound", "user not found");

	private String code;
	private String message;
}
