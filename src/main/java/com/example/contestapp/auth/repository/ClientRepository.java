package com.example.contestapp.auth.repository;

import com.example.contestapp.auth.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByLogin(String login);
}
