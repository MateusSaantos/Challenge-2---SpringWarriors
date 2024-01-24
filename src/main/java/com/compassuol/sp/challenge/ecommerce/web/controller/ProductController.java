package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.service.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.ProductMappper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;





    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id){
        Product prod = productService.getById(id);
        return ResponseEntity.ok(ProductMappper.toDto(prod));


    }



    @PutMapping("/{id}")
    public ResponseEntity <ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductCreateDto product){
        Product prod = productService.updateProduct(id, ProductMappper.toProduct(product));
        return ResponseEntity.ok(ProductMappper.toDto(prod));

    }


}
