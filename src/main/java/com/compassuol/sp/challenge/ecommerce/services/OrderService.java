package com.compassuol.sp.challenge.ecommerce.services;

import com.compassuol.sp.challenge.ecommerce.entities.Order;
import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.entities.ProductInOrder;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.repository.AddressRepository;
import com.compassuol.sp.challenge.ecommerce.repository.OrderRepository;
import com.compassuol.sp.challenge.ecommerce.repository.ProductInOrderRepository;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.OrderMapper;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.OrderCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductInOrderRepository productInOrderService;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Order> getAll(String status){
       Sort sortByCreatedAtDesc = Sort.by(Sort.Order.desc("createdDate"));
       if (status != null && !status.isEmpty()){
            return orderRepository.findAllByStatus(Order.Status.valueOf(status.trim().toUpperCase()), sortByCreatedAtDesc);
        }
        return orderRepository.findAll(sortByCreatedAtDesc);
    }


    @Transactional(readOnly = true)
    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order id=%s não encontrado",id)));
    }

    @Transactional
    public Order createOrder(OrderCreateDto dto) {
        System.out.println(dto.getPaymentMethod());
        Order order =OrderMapper.toOrder(dto);
        Double doubleAux=0D;
        List <Product> listProducts=new ArrayList<>();
        for(ProductInOrder product:order.getProducts()){
            Product aux = productRepository.findById(product.getId()).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Produto id=%s não encontrado",product.getId())));
            Float price = aux.getValue();
            int quantity = product.getQuantity();
            doubleAux+=price*quantity;
        }
        order.setSubtotalValue(doubleAux);
        if(order.getPaymentMethod().equals(Order.Payment.PIX)){
            order.setDiscount(0.05);
        } else{
            order.setDiscount(0D);
        }
        order.setCreatedDate(LocalDateTime.now());
        order.setTotalValue(order.getSubtotalValue()-(order.getDiscount()* order.getSubtotalValue()));
        //Address address=addressRepository.save(order.getAddress());
        order.setStatus(Order.Status.CONFIRMED);
       // address.setOrder(order);
        //order.setAddress(address);
        List<ProductInOrder> products=order.getProducts();
        List<ProductInOrder>saved=productInOrderService.saveAll(products);
        saved.forEach(p->p.setOrder(order));
        order.setProducts(saved);
        return orderRepository.save(order);
    }

    @Transactional
    public Order update(Long id, String status){
        Order existingOrder = getById(id);
        existingOrder.setStatus(Order.Status.valueOf(status.trim().toUpperCase()));
        return orderRepository.save(existingOrder);
    }


    @Transactional
    public Order changeStatusToCancel(Long id, String cancelReason){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order id=%s não encontrado",id)));

        if(order.getStatus().equals(Order.Status.CANCELED)){
            throw new IllegalArgumentException("Pedido já está cancelado");
        }
        if(order.getCreatedDate()
                .plusDays(90)
                .isBefore(java.time.LocalDateTime.now())){
            throw new IllegalArgumentException("Pedido não pode ser cancelado após 90 dia de sua criação");
        }
        order.setCancelReason(cancelReason);
        order.setStatus(Order.Status.CANCELED);
        return orderRepository.save(order);

    }
}
