package com.zirubihara.phototraveller.phototraveller.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;

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
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TEXT", unique = true, nullable = false, length = 200)
    private String text;


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
}
