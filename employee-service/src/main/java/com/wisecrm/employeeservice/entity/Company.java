package com.wisecrm.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "api_key")
    private String api_key;

    @Column(name = "email")
    private String email;


}
