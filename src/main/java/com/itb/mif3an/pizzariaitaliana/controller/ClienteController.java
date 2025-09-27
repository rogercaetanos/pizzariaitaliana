package com.itb.mif3an.pizzariaitaliana.controller;

import com.itb.mif3an.pizzariaitaliana.auth.AuthenticationResponse;
import com.itb.mif3an.pizzariaitaliana.auth.AuthenticationService;
import com.itb.mif3an.pizzariaitaliana.auth.RegisterRequest;
import com.itb.mif3an.pizzariaitaliana.model.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cliente")
@AllArgsConstructor
public class ClienteController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        request.setRole(Role.CLIENTE);
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
