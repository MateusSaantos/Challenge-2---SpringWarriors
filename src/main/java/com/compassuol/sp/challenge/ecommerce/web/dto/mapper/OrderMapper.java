package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class OrderMapper {

    public static Order toEntity(OrderCreateDto orderCreateDto) {
        return new ModelMapper().map(orderCreateDto, Order.class);
    }

    public static Order toOrder(OrderCreateDto dto){
        return  new ModelMapper().map(dto, Order.class);
    }

    public static OrderResponseDto toDto(Order order){
        PropertyMap<Order, OrderResponseDto> props = new PropertyMap<Order, OrderResponseDto>() {
            @Override
            protected void configure() {

            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return   mapper.map(order,OrderResponseDto.class);
    }




}
