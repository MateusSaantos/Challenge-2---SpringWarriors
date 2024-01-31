package com.compassuol.sp.challenge.ecommerce.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderAddressDto {

    @NotBlank
    private Integer number;
    @NotBlank
    private String complement;
    @NotBlank
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 dígitos")
    private String postalCode;
}
