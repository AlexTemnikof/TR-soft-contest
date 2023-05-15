package com.example.contestapp.core.dto;

import com.example.contestapp.core.entities.ImageAttachment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String surname;
    private String name;
    private String patronymic;
    private String birthday;
    private String email;
    private String phoneNumber;

    private ImageAttachment imageAttachment;
}
