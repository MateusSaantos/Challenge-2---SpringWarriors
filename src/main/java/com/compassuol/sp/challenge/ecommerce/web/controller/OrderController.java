package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.services.OrderService;
import com.compassuol.sp.challenge.ecommerce.web.dto.CancelReasonDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.UpdateStatusRequestDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Listar todos os pedidos",
            description = "Recupera a lista de todos os pedidos em ordem de criação.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de pedidos recuperada com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll(@RequestParam(name = "status", required = false) String status){
        List<Order> orders = orderService.getAll(status);
        return ResponseEntity.ok(OrderMapper.toListDto(orders));
    }

    @Operation(
            summary = "Retornar pedido pelo Id",
            description = "Recupera um pedido pelo seu Id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pedido recuperado com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(order));
    }

    @Operation(
            summary = "Criar pedido",
            description = "Cria um novo pedido.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pedido criado com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Order createdOrder = OrderMapper.toOrder(orderCreateDto);
        orderService.createOrder(createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toDto(createdOrder));
    }

    @Operation(
            summary = "Atualizar status do pedido",
            description = "Atualiza o status de um pedido pelo seu Id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pedido atualizado com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody UpdateStatusRequestDto status){
        Order order = orderService.update(id, status.getStatus());
        return ResponseEntity.ok(OrderMapper.toDto(order));
    }

    @Operation(
            summary = "Cancelar pedido",
            description = "Modifica um pedido para Cancela-lo pelo seu Id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pedido cancelado com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDto> delete(@PathVariable Long id,@RequestBody CancelReasonDto cancelReason){
        Order order =orderService.changeStatusToCancel(id, cancelReason.getCancelReason());
        return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(order));
    }


}
