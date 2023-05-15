package com.example.contestapp.tools;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOAll(List<User> users);
}
