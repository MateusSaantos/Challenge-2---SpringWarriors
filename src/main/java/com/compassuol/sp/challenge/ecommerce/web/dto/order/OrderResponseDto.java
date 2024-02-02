package com.compassuol.sp.challenge.ecommerce.web.dto.order;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseDto {

    private List<ProductInOrderResponseDto> products;
    private AddressCreateDto address;
    private Order.Payment paymentMethod;
    private Double subtotalValue;
    private Double discount;
    private Double totalValue;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    private Order.Status status;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime cancelDate;
    private String cancelReason;

}
