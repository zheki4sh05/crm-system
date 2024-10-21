package com.wisecrm.filestorage.dto.categories;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;
    private String name;
    private Long company;

}
