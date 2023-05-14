package com.example.contestapp.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString(includeFieldNames=true)
@Builder
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "birthday")
    private String birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    //todo: add photo

    public void updateSurname(String surname){
        this.surname = surname;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updatePatronymic(String patronymic){
        this.patronymic = patronymic;
    }

}
