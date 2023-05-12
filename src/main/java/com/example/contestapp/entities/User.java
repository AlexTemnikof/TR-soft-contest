package com.example.contestapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString(includeFieldNames=true)
@Builder
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private final String surname;
    private final String name;
    private String patronymic;
    private String birthday;
    private String email;
    private String phoneNumber;

    //todo: add photo

}
