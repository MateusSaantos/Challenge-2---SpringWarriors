package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMapper {

    public static Order toEntity(OrderCreateDto orderCreateDto) {
        return new ModelMapper().map(orderCreateDto, Order.class);
    }

    public static Order toOrder(OrderCreateDto dto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(OrderCreateDto.class, Order.class).addMappings(mapper -> {
            mapper.map(src -> ProductInOrderMapper.toProductInOrderList(dto.getProducts()), Order::setProducts);
            mapper.map(src -> AddressMapper.toAddress(dto.getAddress()), Order::setAddress);
        });
        return  new ModelMapper().map(dto, Order.class);
    }

    public static OrderResponseDto toDto(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Order.class, OrderResponseDto.class).addMappings(mapper -> {
            mapper.map(src -> ProductInOrderMapper.toDtoList(order.getProducts() ), OrderResponseDto::setProducts);
        });
        return modelMapper.map(order, OrderResponseDto.class);
        }


    public static List<OrderResponseDto> toListDto(List<Order> orders) {
        return orders.stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }
}

