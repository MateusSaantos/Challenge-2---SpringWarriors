package com.compassuol.sp.challenge.ecommerce.web.dto.order;

import com.compassuol.sp.challenge.ecommerce.web.dto.address.OrderAddressDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderCreateDto {

    @NotNull
    private List<ProductInOrderDto> products;

    @NotBlank
    private OrderAddressDto address;

    @NotBlank
    private String paymentMethod;
}
