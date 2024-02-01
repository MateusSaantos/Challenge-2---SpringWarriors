package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.ProductInOrder;
import com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder.ProductInOrderResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class ProductInOrderMapper {


    private static ProductInOrderResponseDto toDto(ProductInOrder productInOrder){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ProductInOrder.class, ProductInOrderResponseDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getProduct().getId(), ProductInOrderResponseDto::setProduct_id);
        });
        return modelMapper.map(productInOrder, ProductInOrderResponseDto.class);
    }
    public static List<ProductInOrderResponseDto> toDtoList(List<ProductInOrder> productInOrder){
        return productInOrder.stream().map(prod -> toDto(prod)).collect(Collectors.toList());
    }
}
