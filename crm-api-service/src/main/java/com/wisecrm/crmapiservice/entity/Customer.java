package com.wisecrm.crmapiservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "key")
    private ApiKey key;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
