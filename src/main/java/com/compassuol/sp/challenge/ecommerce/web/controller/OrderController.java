package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.services.OrderService;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(order));
    }
}
