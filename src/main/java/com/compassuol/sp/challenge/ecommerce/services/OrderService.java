package com.compassuol.sp.challenge.ecommerce.services;


import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> getAll(String status){
        Sort sortByCreatedAtDesc = Sort.by(Sort.Order.desc("createdAt"));
        if (!status.isEmpty()){
            return orderRepository.findAllByStatus(Order.Status.valueOf(status), sortByCreatedAtDesc);
        } else {
            return orderRepository.findAll(sortByCreatedAtDesc);
        }
    }

    @Transactional(readOnly = true)
    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order id=%s não encontrado",id)));
    }

    @Transactional
    public Order update(Long id, Order updatedOrder){
        Order existingOrder = getById(id);
        existingOrder.setStatus(updatedOrder.getStatus());
        return orderRepository.save(existingOrder);
    }



}
