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
    @Column(name = "POSTID", unique = true)
    @NotNull
    private Long postId;

    @Column(name = "POSTNAME", unique = true)
    @Size(min = 2, max = 30, message = "Nieoprawna długość nazwy posta")
    @NotBlank(message = "Nazwa postu nie może być pusta!")
    private String postName;

    @Column(name = "URL")
    @Nullable
    private String url;

    @Column(name = "DESCRIPTION")
    @Size(min = 10, max = 500, message = "Niepoprawna długość opisu")
    @Nullable
    @Lob
    private String description;


    private Integer voteCount = 0;


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Photos photos;
}
