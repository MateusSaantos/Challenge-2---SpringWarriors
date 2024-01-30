package com.compassuol.sp.challenge.ecommerce.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductDto {

    @NotNull
    private Long productId;

    @NotBlank
    @Size(min= 1, message = "Quantidade de produto inválida")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter apenas números")
    private int quantity;

}
