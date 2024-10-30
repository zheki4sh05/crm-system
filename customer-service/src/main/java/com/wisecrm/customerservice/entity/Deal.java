package com.wisecrm.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "employee_id")
    private Long employee_id;

}
