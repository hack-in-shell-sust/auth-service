package com.example.authservice.controller;

import com.example.authservice.domain.dto.CreateUserRequest;
import com.example.authservice.domain.dto.CreateUserResponse;
import com.example.authservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/register")
public class RegistrationResource {
	private final RegistrationService registrationService;

    @PostMapping("/user")
	public ResponseEntity<CreateUserResponse> registerUser(@RequestBody CreateUserRequest createRequest) {
		CreateUserResponse response =  registrationService.registerUser(createRequest);
		return ResponseEntity.ok(response);
	}
}
