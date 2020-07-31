package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Email
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CREATED")
    private Instant created;

    @Column(name = "ENABLED")
    private boolean enabled;

    //    @Id
//    @GeneratedValue(strategy = SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
//    @NotNull
//    private Long userId;
//
//    @Column(name = "USERNAME", unique = true)
//    @Size(min = 5, max = 20, message = "Niepoprawna długość nazwy użytkownika")
//    @NotBlank(message = "Nazwa użytkownika jest wymagana")
//    private String username;
//
//    @NotBlank(message = "Hasło jest wymagane")
//    private String password;
//
//    @Email
//    @NotEmpty(message = "Adres email jest wymagany")
//    private String email;
//
//    private Instant created;
//
//    private boolean enabled;
}
