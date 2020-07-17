package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
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
