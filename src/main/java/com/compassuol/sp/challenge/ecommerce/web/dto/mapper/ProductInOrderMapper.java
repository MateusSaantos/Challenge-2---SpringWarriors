package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.ProductInOrder;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductInOrderMapper {



    private ProductInOrderResponseDto toDto(ProductInOrder productInOrder){
        ProductInOrderResponseDto dto = new ProductInOrderResponseDto();
        dto.setProduct_id(productInOrder.getProduct().getId());
        dto.setQuantity(productInOrder.getQuantity());
        return dto;
    }
    private List<ProductInOrderResponseDto> toDtoList(List<ProductInOrder> productInOrder){
        return productInOrder.stream().map(prod -> toDto(prod)).collect(Collectors.toList());
    }
}
