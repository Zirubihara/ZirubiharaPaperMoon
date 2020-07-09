package com.zirubihara.phototraveller.phototraveller.model;

import javafx.geometry.Pos;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORY")
@EqualsAndHashCode

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(name = "NAME", unique = true, length = 30)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "DESCRIPTION", length = 500)
    private String description;


    @OneToMany(fetch = EAGER)
    private List<Post> quests;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    private User user;

}
