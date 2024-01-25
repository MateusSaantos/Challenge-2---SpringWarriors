package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductCreateDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.ProductResponseDto;
import com.compassuol.sp.challenge.ecommerce.web.dto.mapper.ProductMappper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Produtos", description = "Aqui estão contidas todas as operações relacionadas à criação, edição, deleção e leitura de um produto.")
@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Listar todos os produtos", description = "Recurso para listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(ProductMappper.toListDto(products));
    }

    @Operation(summary = "Listar um produto pelo Id", description = "Recurso para listar um produto específico")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id) {
        Product prod = productService.getById(id);
        return ResponseEntity.ok(ProductMappper.toDto(prod));
    }

    @Operation(summary = "Atualizar um produto", description = "Recurso para atualizar um produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductCreateDto product) {
        Product prod = productService.updateProduct(id, ProductMappper.toProduct(product));
        return ResponseEntity.ok(ProductMappper.toDto(prod));

    }

    @Operation(summary = "Criar um produto", description = "Recurso para criar um produto")
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto productDto) {
        Product createdProduct = productService.create(ProductMappper.toProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMappper.toDto(createdProduct));
    }

    @Operation(summary = "Deletar um produto pelo Id", description = "Recurso para deletar um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
