package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    private final ProductService productService;

}
