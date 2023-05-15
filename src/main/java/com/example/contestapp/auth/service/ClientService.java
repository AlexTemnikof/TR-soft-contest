package com.example.contestapp.auth.service;

import com.example.contestapp.auth.entity.Client;
import com.example.contestapp.auth.enums.Role;
import com.example.contestapp.auth.repository.ClientRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client getByLogin(@NonNull String login) {
        return clientRepository.findByLogin(login);
    }

    public Client create(@NonNull String login, String password){

        Client client = new Client(login, password, Role.USER);
        clientRepository.save(client);
        return client;
    }
}
