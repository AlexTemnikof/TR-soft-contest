package com.example.contestapp.core.service;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.entities.User;
import com.example.contestapp.tools.UserMapper;
import com.example.contestapp.core.repositories.UserRepository;
import com.example.contestapp.tools.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @PostConstruct
    public void init(){
        //todo:
    }

    public UserDTO addUser(String surname, String name, String patronymic, String birthday, String email,
                           String phoneNumber){
        User user  = User.builder().surname(surname).name(name).patronymic(patronymic).birthday(birthday).email(email)
                .phoneNumber(phoneNumber).build();
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAll(){
        return userMapper.toDTOAll(userRepository.findAll());
    }

    public UserDTO getById(String id) throws Exception {
        return userMapper.toDTO(userRepository.getById(FieldValidator.validateId(id)));
    }

    public String getSurnameById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getSurname();
    }

    public String getNameById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getName();
    }

    public String getPatronymicById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getPatronymic();
    }

    public UserDTO updateNameById(String id, String name) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateName(name);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateSurnameById(String id, String surname) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateSurname(surname);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePatronymicById(String id, String patronymic) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateSurname(patronymic);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public boolean deleteById(String id) throws Exception {
        userRepository.deleteById(FieldValidator.validateId(id));
        return true;
    }

    public String getBirthdayById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getBirthday();
    }

    public String getEmailById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getEmail();
    }

    public String getPhoneNumberById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getPhoneNumber();
    }

    public UserDTO updateBirthdayById(String id, String birthday) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateBirthday(birthday);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateEmailById(String id, String email) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateEmail(email);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePhoneNumberById(String id, String phoneNumber) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updatePhoneNumber(phoneNumber);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

}
