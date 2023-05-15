package com.example.contestapp.core.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageAttachment {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;


    private String attachTitle;

    @Column(nullable = false, updatable = false)
    private LocalDate uploadDate;

    private String extension;

    private String downloadLink;
}
