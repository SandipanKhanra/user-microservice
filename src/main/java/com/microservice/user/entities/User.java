package com.microservice.user.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private String userId;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
