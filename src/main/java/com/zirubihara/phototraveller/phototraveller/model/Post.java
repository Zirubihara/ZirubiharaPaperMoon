package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    @Column(name = "POSTID", unique = true, nullable = false)
    private Long postId;

    @Column(name = "POSTNAME", unique = true)
    @Size(min = 2, max = 30, message = "Nazwa posta powinna zawieraćod 2 do 30 znaków")
    @NotBlank(message = "Nazwa posta niepowinna być pusta")
    private String postName;

    @Column(name = "URL")
    @Nullable
    private String url;

    @Column(name = "DESCRIPTION")
    @Size(min = 10, max = 500, message = "Nazwa opisu powinna mieć od 10 do 500 zanków")
    @NotNull(message = "Nazwa posta nie powinna być pusta")
    @Lob
    private String description;


    private Integer voteCount = 0;


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Photos photos;
}
