package com.wisecrm.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "deal")
    private Long deal;

    @Column(name = "is_done")
    private Boolean is_done;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "worker")
    private User worker;


    @Column(name = "start")
    private Timestamp start;

    @Column(name = "finishAt")
    private Timestamp finishAt;




}
