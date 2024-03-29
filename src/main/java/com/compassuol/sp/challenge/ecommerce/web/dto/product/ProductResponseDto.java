package com.compassuol.sp.challenge.ecommerce.web.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDto {

    private Long id;
    private String name;
    private Float value;
    private String description;
}
