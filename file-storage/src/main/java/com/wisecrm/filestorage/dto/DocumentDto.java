package com.wisecrm.filestorage.dto;

import lombok.*;

import java.time.*;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentDto {
    private Long id;
    private String name;
    private LocalDate downloadAt;
    private Double size;
    private Long departmentId;
    private String extension;

}
