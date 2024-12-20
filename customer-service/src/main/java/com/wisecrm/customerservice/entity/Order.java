package com.wisecrm.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;

    @Column(name = "count")
    private String code;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "deal")
    private Deal deal;




}
