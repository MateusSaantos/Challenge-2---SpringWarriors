package com.compassuol.sp.challenge.ecommerce.services;


import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order id=%s não encontrado",id)));
    }



}
