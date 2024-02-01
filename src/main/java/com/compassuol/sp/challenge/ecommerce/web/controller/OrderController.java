package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.entities.ProductInOrder;
import com.compassuol.sp.challenge.ecommerce.services.OrderService;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Pedidos", description = "Aqui estão contidas todas as operações relacionadas à criação, edição, cancelamento e leitura de um pedido.")
@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    //private final ProductService productService;

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

}
