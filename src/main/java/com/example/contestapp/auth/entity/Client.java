package com.example.contestapp.auth.entity;

import com.example.contestapp.auth.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Client(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
