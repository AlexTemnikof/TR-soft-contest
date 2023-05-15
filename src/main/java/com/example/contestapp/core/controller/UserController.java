package com.example.contestapp.core.controller;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO createUser(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam(required = false) String patronymic,
                              @RequestParam(required = false) String birthday,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String phoneNumber){ //возможен вариант в виде передачи параметров в виде json UserDTO
        return service.addUser(surname, name, patronymic, birthday, email, phoneNumber); //однако учитывая, что могут быть необязательные параметры был выбран такой метод
    }

    @GetMapping("")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<UserDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UserDTO getById(@PathVariable("id") String id) throws Exception {
        return service.getById(id);
    }

    @PatchMapping("/{id}/surname")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO updateSurname(@PathVariable("id") String id, @RequestParam String surname) throws Exception {
        return service.updateSurnameById(id, surname);
    }

    @PatchMapping("/{id}/name")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO updateName(@PathVariable("id") String id, @RequestParam String name) throws Exception {
        return service.updateNameById(id, name);
    }

    @PatchMapping("/{id}/patronymic")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO updatePatronymic(@PathVariable("id") String id, @RequestParam String patronymic) throws Exception {
        return service.updatePatronymicById(id, patronymic);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable("id") String id) throws Exception {
        return service.deleteById(id);
    }


}
