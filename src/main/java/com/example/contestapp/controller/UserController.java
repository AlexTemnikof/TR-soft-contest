package com.example.contestapp.controller;

import com.example.contestapp.dto.UserDTO;
import com.example.contestapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("")
    public UserDTO createUser(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam(required = false) String patronymic,
                              @RequestParam(required = false) String birthday,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String phoneNumber){ //возможен вариант в виде передачи параметров в виде json UserDTO
        return service.addUser(surname, name, patronymic, birthday, email, phoneNumber); //однако учитывая, что могут быть необязательные параметры был выбран такой метод
    }

    @GetMapping("")
    public List<UserDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") String id) throws Exception {
        return service.getById(id);
    }

    @PatchMapping("/{id}/surname")
    public UserDTO updateSurname(@PathVariable("id") String id, @RequestParam String surname) throws Exception {
        return service.updateSurnameById(id, surname);
    }

    @PatchMapping("/{id}/name")
    public UserDTO updateName(@PathVariable("id") String id, @RequestParam String name) throws Exception {
        return service.updateNameById(id, name);
    }

    @PatchMapping("/{id}/patronymic")
    public UserDTO updatePatronymic(@PathVariable("id") String id, @RequestParam String patronymic) throws Exception {
        return service.updatePatronymicById(id, patronymic);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") String id) throws Exception {
        return service.deleteById(id);
    }


}
