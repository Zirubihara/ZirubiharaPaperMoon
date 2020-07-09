package com.zirubihara.phototraveller.phototraveller.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
@EqualsAndHashCode

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Size(min = 1, max = 30)
    @NotEmpty
    @Column(name = "NAME", unique = true)
    private String text;


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
