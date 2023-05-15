package com.example.contestapp.auth.controller;

import com.example.contestapp.auth.dto.JwtRequest;
import com.example.contestapp.auth.dto.JwtResponse;
import com.example.contestapp.auth.dto.RefreshJwtRequest;
import com.example.contestapp.auth.service.AuthService;
import com.example.contestapp.auth.service.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ClientService clientService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.register(authRequest);
        return ResponseEntity.ok(token);
    }

    @PatchMapping("{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void setAdminRole(@PathVariable("id") String id) throws Exception {
        clientService.setAdminRole(id);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}