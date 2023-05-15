package com.example.contestapp.core.controller;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userdetails")
public class UserDetailsController {
    @Autowired
    private UserService service;

    @GetMapping("/{id}/birthday")
    public String getBirthday(@PathVariable("id") String id) throws Exception {
        return service.getBirthdayById(id);
    }

    @GetMapping("/{id}/email")
    public String getEmailById(@PathVariable("id") String id) throws Exception {
        return service.getEmailById(id);
    }

    @GetMapping("/{id}/phonenumber")
    public String getPhoneNumberById(@PathVariable("id") String id) throws Exception {
        return service.getPhoneNumberById(id);
    }

    @PatchMapping("/{id}/birthday")
    public UserDTO updateBirthdayById(@PathVariable("id") String id, @RequestParam String birthday) throws Exception {
        return service.updateBirthdayById(id, birthday);
    }

    @PatchMapping("/{id}/email")
    public UserDTO updateEmailById(@PathVariable("id") String id, @RequestParam String email) throws Exception {
        return service.updateEmailById(id, email);
    }

    @PatchMapping("/{id}/phonenumber")
    public UserDTO updatePhoneNumberById(@PathVariable("id") String id, @RequestParam String phoneNumber) throws Exception {
        return service.updatePhoneNumberById(id, phoneNumber);
    }
}