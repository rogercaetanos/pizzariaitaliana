package com.itb.mif3an.pizzariaitaliana.auth;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {
	
	private final AuthenticationService service;

	public AuthenticationController(AuthenticationService service) {
		this.service = service;
	}


	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		
		return ResponseEntity.ok(service.authenticate(request));
		
	}

}
