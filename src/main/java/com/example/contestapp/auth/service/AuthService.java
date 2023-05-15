package com.example.contestapp.auth.service;

import com.example.contestapp.auth.dto.JwtRequest;
import com.example.contestapp.auth.dto.JwtResponse;
import com.example.contestapp.auth.entity.Client;
import com.example.contestapp.auth.entity.JwtAuthentication;
import com.example.contestapp.auth.providers.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientService clientService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final Client client = clientService.getByLogin(authRequest.getLogin());
        if (client.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(client);
            final String refreshToken = jwtProvider.generateRefreshToken(client);
            refreshStorage.put(client.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong credentials");
        }
    }

    public JwtResponse register(@NonNull JwtRequest authRequest) throws AuthException {
        final Client client = clientService.create(authRequest.getLogin(), authRequest.getPassword());
        final String accessToken = jwtProvider.generateAccessToken(client);
        final String refreshToken = jwtProvider.generateRefreshToken(client);
        refreshStorage.put(client.getLogin(), refreshToken);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Client client = clientService.getByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(client);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Client client = clientService.getByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(client);
                final String newRefreshToken = jwtProvider.generateRefreshToken(client);
                refreshStorage.put(client.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid Jwt token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}