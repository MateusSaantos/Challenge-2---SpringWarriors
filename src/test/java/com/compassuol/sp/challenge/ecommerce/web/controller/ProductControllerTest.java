package com.compassuol.sp.challenge.ecommerce.web.controller;


import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.exception.DuplicateProductNameException;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.controller.ProductController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.compassuol.sp.challenge.ecommerce.common.ProductConstants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    public void createProduct_WithValidData_ReturnsStatus201() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(PRODUCT);

        mockMvc
                .perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(PRODUCT)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createProduct_WithInvalidData_ReturnsStatus409() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(INVALID_PRODUCT_NULL_INFO);

        mockMvc
                .perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(INVALID_PRODUCT_NULL_INFO)))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void createProduct_WithDuplicateName_ReturnsStatus409() throws Exception {
        when(productService.create(any(Product.class))).thenThrow(new DuplicateProductNameException("nome repetido"));

        mockMvc
                .perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(INVALID_PRODUCT_REPEATED_NAME)))
                .andExpect(status().isConflict() );
    }
    @Test
    public void getProduct_WithExistentId_ReturnsStatus200() throws Exception {
        when(productService.getById(anyLong())).thenReturn(PRODUCT);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(PRODUCT)));
    }

    @Test
    public void getProduct_WithNonExistentId_ReturnsStatus404() throws Exception {
        when(productService.getById(anyLong())).thenThrow(new EntityNotFoundException("id não encontrado"));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllProducts_ReturnsStatus200() throws Exception {
        List<Product> products = List.of(PRODUCT);
        when(productService.getAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    public void getAllProducts_ReturnStatus404(){
        when(productService.getAll()).thenReturn(Collections.emptyList());

    }

}
