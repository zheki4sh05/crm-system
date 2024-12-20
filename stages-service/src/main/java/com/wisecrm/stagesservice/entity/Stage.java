package com.wisecrm.stagesservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stage")
@Builder
public class Stage implements Comparable<Stage>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "groups")
    private Group group;

    @Column(name="order")
    private Integer order;

    @Override
    public int compareTo(Stage o) {
        return this.order.compareTo(o.order);
    }
}