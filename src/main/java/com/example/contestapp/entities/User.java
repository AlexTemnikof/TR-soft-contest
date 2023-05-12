package com.example.contestapp.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString(includeFieldNames=true)
public class User {

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String birthday;
    private String email;
    private String phoneNumber;

    //todo: add photo

}
