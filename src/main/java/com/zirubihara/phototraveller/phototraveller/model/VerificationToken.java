package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "verification_token_seq")
    @SequenceGenerator(name = "verification_token_seq", sequenceName = "verification_token_seq", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @OneToOne(fetch = LAZY)
    private User user;

    @Column(name = "EXPIRY_DATE")
    private Instant expiryDate;
}
