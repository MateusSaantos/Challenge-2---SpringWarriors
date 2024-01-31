package com.compassuol.sp.challenge.ecommerce.services;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> getAll(String status){
        Sort sortByCreatedAtDesc = Sort.by(Sort.Order.desc("createdAt"));
        if (!status.isEmpty()){
            return orderRepository.findAllByStatus(Order.Status.valueOf(String.valueOf(status)), sortByCreatedAtDesc);
        } else {
            return orderRepository.findAll(sortByCreatedAtDesc);
        }
    }
}
