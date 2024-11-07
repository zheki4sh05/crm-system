package com.wisecrm.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.*;
import java.time.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deal")
@Builder
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "started")
    private Timestamp started;

    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name="stage")
    private Long stage;

}
