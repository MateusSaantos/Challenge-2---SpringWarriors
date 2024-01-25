package com.compassuol.sp.challenge.ecommerce.web.controller.dto;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductCreateDto {

    private String name;
    
    @Size(min=10)
    private String description;
    
    @Min(0)
    private Float value;
}
