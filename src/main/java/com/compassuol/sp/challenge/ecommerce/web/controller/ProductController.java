package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.controller.dto.ProductCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.controller.dto.ProductResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.controller.dto.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(ProductMapper.toListDto(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        Product prod = productService.getById(id);
        return ResponseEntity.ok(ProductMapper.toDto(prod));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductCreateDto product) {
        Product prod = productService.updateProduct(id, ProductMapper.toProduct(product));
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDto(prod));

    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto productDto) {
        Product createdProduct = productService.create(ProductMapper.toProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(createdProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
