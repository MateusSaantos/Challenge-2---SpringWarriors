package com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInOrderDto {

    @NotNull
    private Long productId;

    @NotBlank
    @Size(min= 1, message = "Quantidade de produto inválida")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter apenas números")
    private int quantity;

}
