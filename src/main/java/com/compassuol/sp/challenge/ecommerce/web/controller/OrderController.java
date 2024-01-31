package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.services.OrderService;
import com.compassuol.sp.challenge.ecommerce.web.dto.OrderResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Aqui estão contidas todas as operações relacionadas à criação, edição, cancelamento e leitura de um pedido.")
@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Listar todos os pedidos", description = "Recurso para listar todos os pedidos em ordem de criação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll(@RequestParam(name = "status", required = false) String status){
        List<Order> orders = orderService.getAll(status);
        return ResponseEntity.ok(OrderMapper.toListDto(orders));
    }
}
