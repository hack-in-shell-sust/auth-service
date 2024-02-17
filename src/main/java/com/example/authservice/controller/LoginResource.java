package com.example.authservice.controller;

import com.example.authservice.common.utils.ApiResponse;
import com.example.authservice.common.utils.ResponseUtils;
import com.example.authservice.domain.dto.LoginRequest;
import com.example.authservice.domain.dto.LoginResponse;
import com.example.authservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/login")
public class LoginResource {
	private final LoginService loginService;

    @PostMapping("/login")
	public ApiResponse<LoginResponse> registerUser(@RequestBody LoginRequest loginRequest) {
		LoginResponse response = loginService.loginUser(loginRequest);
		return ResponseUtils.createSuccessResponseObject("Success", response);
	}
}
