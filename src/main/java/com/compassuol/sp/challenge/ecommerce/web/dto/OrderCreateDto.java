package com.compassuol.sp.challenge.ecommerce.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderCreateDto {

    @NotNull
    private List<OrderProductDto> products;

    @NotBlank
    private OrderAddressDto address;

    @NotBlank
    private String paymentMethod;
}
