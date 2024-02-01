package com.compassuol.sp.challenge.ecommerce.web.dto.order;

import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class OrderResponseDto {

    private Long id;
    private List<ProductInOrderResponseDto> products;
    private AddressResponseDto address;
    private String paymentMethod;
    private Double subtotalValue;
    private Double discount;
    private Double totalValue;
    private String createdDate;
    private String status;


}
