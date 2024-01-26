package com.compassuol.sp.challenge.ecommerce.web.dto;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min=10)
    private String description;

    @NotNull
    @Min(0)
    private Float value;
}
