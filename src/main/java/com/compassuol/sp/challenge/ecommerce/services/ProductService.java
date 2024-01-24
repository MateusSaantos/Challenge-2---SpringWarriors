package com.compassuol.sp.challenge.ecommerce.service;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product updateProduct(Long id, Product product) {

        Product prod = getById(id);
        prod.setDescription(product.getDescription());
        prod.setValue(product.getValue());
        prod.setName(product.getName());

        return prod;
    }
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto não encontrado.")
        );
    }




}
