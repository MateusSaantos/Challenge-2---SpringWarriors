package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.services.OrderService;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Aqui estão contidas todas as operações relacionadas à criação, edição, cancelamento e leitura de um pedido.")
@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll(@RequestParam(name = "status", required = false) String status){
        List<Order> orders = orderService.getAll(status);
        return ResponseEntity.ok(OrderMapper.toListDto(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(order));
    }
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Order order = OrderMapper.toEntity(orderCreateDto);

        // Lista de produtos
        //List<ProductInOrder> productsInOrder = order.getOrder();

        // Calcula o SUBTOTAL
        //order.calculateSubtotalValue(productsInOrder);

        // Calcula o Total
        //order.calculateTotalValue();
        Order createdOrder = orderService.createOrder(order);
        OrderResponseDto responseDto = OrderMapper.toDto(createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody String status){
        Order order = orderService.update(id, status);
        return ResponseEntity.ok(OrderMapper.toDto(order));
    }
}
