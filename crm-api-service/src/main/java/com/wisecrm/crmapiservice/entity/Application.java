package com.wisecrm.crmapiservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application")
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "key")
    private ApiKey key;


}
