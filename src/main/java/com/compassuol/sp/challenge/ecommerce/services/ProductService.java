package com.compassuol.sp.challenge.ecommerce.services;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.exception.ProductValidationException;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Produto id=%s não encontrado",id))
        );
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {

        Product prod = getById(id);
        prod.setDescription(product.getDescription());
        prod.setValue(product.getValue());
        prod.setName(product.getName());

        return prod;
    }


    @Transactional
    public Product create(Product product) {
        try {
            return productRepository.save(product);
        }
        catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new ProductValidationException("Dados fornecidos inválidos");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
