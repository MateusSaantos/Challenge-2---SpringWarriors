package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.ProductInOrder;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class ProductInOrderMapper {


    private static ProductInOrderResponseDto toDto(ProductInOrder productInOrder){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productInOrder, ProductInOrderResponseDto.class);
    }
    public static List<ProductInOrderResponseDto> toDtoList(List<ProductInOrder> productInOrder) {
        return productInOrder.stream().map(prod -> toDto(prod)).collect(Collectors.toList());}


    public static ProductInOrder toProductInOrder(ProductInOrderCreateDto productInOrderCreateDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productInOrderCreateDto, ProductInOrder.class);
    }
    public static List<ProductInOrder> toProductInOrderList(List<ProductInOrderCreateDto> productInOrderCreateDto) {
        return productInOrderCreateDto.stream().map(prod -> toProductInOrder(prod)).collect(Collectors.toList());
    }
}

