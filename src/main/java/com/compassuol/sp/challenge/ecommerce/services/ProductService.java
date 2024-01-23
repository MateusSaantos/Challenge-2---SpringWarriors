package com.compassuol.sp.challenge.ecommerce.services;

import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService{

    private final ProductRepository productRepository;

    //transactional... method
}

