package com.wisecrm.filestorage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "document")
public class Document {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "load_at")
    private LocalDate loadAt;


    @Column(name = "department_id")
    private Long department_id;

    @Column(name = "path")
    private String path;

    @Column(name = "size")
    private Double size;

    @ManyToOne
    @JoinColumn(name = "extension")
    private Extension extension;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


}
