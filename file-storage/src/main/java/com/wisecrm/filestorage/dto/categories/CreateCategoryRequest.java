package com.wisecrm.filestorage.dto.categories;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {

    private Long id;
    private String name;

}
