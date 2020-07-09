package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PHOTOS")
@Builder
public class Photos {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "photos_seq")
    @SequenceGenerator(name = "photos_seq", sequenceName = "photos_seq", allocationSize = 1)
    @Column(name = "ID", unique = true)
    @NotNull
    private Long id;

    @Column(name = "NAME", unique = true)
    @Size(min = 5, max = 40, message = "Nieoprawna długość")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(min = 10, max = 500, message = "Niepoprawna długość opisu")
    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = EAGER, cascade = {CascadeType.ALL})
    private List<Post> posts;

    @Column(name = "CREATED_DATE")
    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    private User user;
}
