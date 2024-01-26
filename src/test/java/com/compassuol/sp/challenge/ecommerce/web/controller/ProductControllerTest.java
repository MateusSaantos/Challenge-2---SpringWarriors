package com.compassuol.sp.challenge.ecommerce.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.exception.DuplicateProductNameException;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;
import com.compassuol.sp.challenge.ecommerce.web.controller.ProductController;
import com.compassuol.sp.challenge.ecommerce.web.controller.dto.ProductCreateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.compassuol.sp.challenge.ecommerce.common.ProductConstants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private ProductRepository productRepository;

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
                .andExpect(status().isConflict());
    }

    @Test
    public void updateProduct_WithValidDataAndId_ReturnStatus200() throws Exception {
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(UPDATED_PRODUCT);

        mockMvc.perform(put("/products/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(UPDATED_PRODUCT)))
                .andExpect(status().isOk());
    }

    @Test
    public void getProduct_WithExistentId_ReturnsStatus200() throws Exception {
        when(productService.getById(anyLong())).thenReturn(PRODUCT);

        mockMvc.perform(get("/products/1"))
                .andExpect(content().json(objectMapper.writeValueAsString(PRODUCT)))
                .andExpect(status().isOk());
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
    public void deleteProduct_withValidId_ReturnsStatus204() throws Exception {
        when(productService.getById(anyLong())).thenReturn(PRODUCT);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteProduct_withNonValidId_ReturnsStatus404() throws Exception {
        when(productService.getById(anyLong())).thenThrow(new EntityNotFoundException("Produto não encontrado"));

        mockMvc.perform(delete("/products/{id}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteProduct_WithNonExistingId_ReturnsNotFound() throws Exception {
        final Long nonExistingProductId = 1L;

        doThrow(new EmptyResultDataAccessException(1)).when(productService).deleteById(nonExistingProductId);

        mockMvc.perform(delete("/products/" + nonExistingProductId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteProduct_WithExistingId_ReturnsNoContent() throws Exception {
        Long productId = 1L;

        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void listProducts_ReturnsNoProducts() throws Exception {
        when(productService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}