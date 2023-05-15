package com.example.contestapp.mapper;

import com.example.contestapp.dto.UserDTO;
import com.example.contestapp.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOAll(List<User> users);
}
