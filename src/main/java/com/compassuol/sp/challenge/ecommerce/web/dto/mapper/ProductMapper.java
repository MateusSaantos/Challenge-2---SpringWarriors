package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toProduct(ProductCreateDto dto){
        return  new ModelMapper().map(dto,Product.class);
    }
    public static ProductResponseDto toDto(Product product){
        PropertyMap<Product,ProductResponseDto> props = new PropertyMap<Product, ProductResponseDto>() {
            @Override
            protected void configure() {

            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return   mapper.map(product,ProductResponseDto.class);
    }

    public static List<ProductResponseDto> toListDto(List<Product> products){
        return products.stream().map(prod -> toDto(prod)).collect(Collectors.toList());
    }




}
