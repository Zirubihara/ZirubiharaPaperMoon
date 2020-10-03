package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
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

    @Column(name = "POSTNAME", unique = true, length = 30, nullable = false)
    private String postName;

    @Column(name = "URL", nullable = false)
    private String url;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    private String description;

    @Column(name = "VOTE_COUNT")
    private Integer voteCount = 0;


    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Photos photos;
}
