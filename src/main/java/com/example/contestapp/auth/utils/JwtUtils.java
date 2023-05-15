package com.example.contestapp.auth.utils;

import com.example.contestapp.auth.entity.JwtAuthentication;
import com.example.contestapp.auth.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final String roleString = claims.get("role", String.class);
        Role role = Role.valueOf(roleString);
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

}