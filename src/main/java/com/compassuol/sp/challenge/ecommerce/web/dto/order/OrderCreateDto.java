package com.compassuol.sp.challenge.ecommerce.web.dto.order;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderCreateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderCreateDto {

    @NotNull
    private List<ProductInOrderCreateDto> products;

    @NotBlank
    private AddressCreateDto address;

    @NotBlank
    private String paymentMethod;



}
