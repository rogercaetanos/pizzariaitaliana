package com.itb.mif3an.pizzariaitaliana.controller;

import com.itb.mif3an.pizzariaitaliana.auth.AuthenticationResponse;
import com.itb.mif3an.pizzariaitaliana.auth.AuthenticationService;
import com.itb.mif3an.pizzariaitaliana.auth.RegisterRequest;
import com.itb.mif3an.pizzariaitaliana.model.entity.Role;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/funcionario")
@AllArgsConstructor
public class FuncionarioController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        request.setRole(Role.FUNCIONARIO);
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
