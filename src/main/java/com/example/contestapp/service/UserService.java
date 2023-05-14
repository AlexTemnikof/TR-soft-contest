package com.example.contestapp.service;

import com.example.contestapp.dto.UserDTO;
import com.example.contestapp.entities.User;
import com.example.contestapp.mapper.UserMapper;
import com.example.contestapp.repositories.UserRepository;
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

    public UserDTO getById(String id){
        return userMapper.toDTO(userRepository.getById(id));
    }

    public String getSurnameById(String id){
        return userRepository.getById(id).getSurname();
    }

    public String getNameById(String id){
        return userRepository.getById(id).getName();
    }

    public String getPatronymicById(String id){
        return userRepository.getById(id).getPatronymic();
    }

    public UserDTO updateNameById(String id, String name){
        User user = userRepository.getById(id);
        user.updateName(name);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateSurnameById(String id, String surname){
        User user = userRepository.getById(id);
        user.updateSurname(surname);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePatronymicById(String id, String patronymic){
        User user = userRepository.getById(id);
        user.updateSurname(patronymic);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public boolean deleteById(String id){
        userRepository.deleteById(id);
        return true;
    }

    public String getBirthdayById(String id){
        return userRepository.getById(id).getBirthday();
    }

    public String getEmailById(String id){
        return userRepository.getById(id).getEmail();
    }

    public String getPhoneNumberById(String id){
        return userRepository.getById(id).getPhoneNumber();
    }

    public UserDTO updateBirthdayById(String id, String birthday){
        User user = userRepository.getById(id);
        user.updateBirthday(birthday);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateEmailById(String id, String email){
        User user = userRepository.getById(id);
        user.updateEmail(email);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePhoneNumberById(String id, String phoneNumber){
        User user = userRepository.getById(id);
        user.updatePhoneNumber(phoneNumber);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

}
