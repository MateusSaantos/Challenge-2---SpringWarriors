package com.compassuol.sp.challenge.ecommerce.service;

import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.exception.DuplicateProductNameException;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.exception.ProductValidationException;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import com.compassuol.sp.challenge.ecommerce.services.ProductService;

import static com.compassuol.sp.challenge.ecommerce.common.ProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void createProduct_WithValidData_ReturnsProduct(){
        when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);
        Product sut = productService.create(PRODUCT);
        assertEquals(sut, PRODUCT);
    }

    @Test
    public void createProduct_WithEmptyProductName_ReturnsProduct(){
        when(productRepository.save(INVALID_PRODUCT_EMPTY_NAME)).thenThrow(new ProductValidationException("nome inválido"));
        assertThrows(ProductValidationException.class, () -> productService.create(INVALID_PRODUCT_EMPTY_NAME));
    }

    @Test
    public void createProduct_WithNullProduct_ReturnsProduct(){
        when(productRepository.save(INVALID_PRODUCT_NULL_INFO)).thenThrow(new ProductValidationException("informação inválida"));
        assertThrows(ProductValidationException.class, () -> productService.create(INVALID_PRODUCT_NULL_INFO));
    }

    @Test
    public void createProduct_WithRepeatedProduct_ReturnsProduct(){
        when(productRepository.existsByName(PRODUCT.getName())).thenReturn(true);
        assertThrows(DuplicateProductNameException.class, () -> productService.create(PRODUCT));
    }

    @Test
    public void updateProduct_WithValidBodyAndId_ReturnsProduct(){
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(PRODUCT));
        when(productRepository.existsByNameAndIdNot(UPDATED_PRODUCT.getName(), productId)).thenReturn(false);
        when(productRepository.save(UPDATED_PRODUCT)).thenReturn(UPDATED_PRODUCT);

        Product result = productService.updateProduct(productId, UPDATED_PRODUCT);

        assertNotNull(result);
        assertEquals(UPDATED_PRODUCT, result);
    }

    @Test
    public void updateProduct_WithValidBodyAndNotValidId_ReturnsProduct(){
        Long InvalidProductId = 99L;

        when(productRepository.findById(InvalidProductId)).thenThrow(new EntityNotFoundException("Id não existente"));
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(InvalidProductId, UPDATED_PRODUCT));
    }

    @Test
    public void updateProduct_WithSameNameProductAndValidId_ReturnsProduct(){
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(PRODUCT));
        when(productRepository.existsByNameAndIdNot(UPDATED_PRODUCT.getName(), productId)).thenReturn(true);

        assertThrows(DuplicateProductNameException.class, () -> productService.updateProduct(productId, UPDATED_PRODUCT));
    }

    @Test
    public void updateProduct_WithSameObject_DoesNotReturn(){
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(PRODUCT));
        when(productRepository.existsByNameAndIdNot(PRODUCT.getName(), productId)).thenReturn(false);

        assertDoesNotThrow(() -> productService.updateProduct(productId, PRODUCT));
    }

    @Test
    public void getProduct_WithExistentId_ReturnsProduct(){
        Long ExistentId = 1L;
        when(productRepository.findById(ExistentId)).thenReturn(Optional.of(PRODUCT));
        Optional<Product> sut = Optional.ofNullable(productService.getById(ExistentId));
        assertEquals(sut, Optional.of(PRODUCT));
    }

    @Test
    public void getProduct_WithNonExistentId_ReturnsEntityNotFound(){
        Long nonExistentId = 1L;
        when(productRepository.findById(nonExistentId)).thenThrow(new EntityNotFoundException("Id não existente"));
        assertThrows(EntityNotFoundException.class, () -> productService.getById(nonExistentId));
    }

    @Test
    public void listProducts_ReturnsAllProducts(){
        List<Product> products = new ArrayList<>();
        products.add(PRODUCT);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> sut = productService.getAll();

        assertEquals(sut.size(), 1);
        assertFalse(sut.isEmpty());
        assertEquals(sut.get(0), PRODUCT);
    }

    @Test
    public void listProducts_ReturnsNoProducts(){
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        List<Product> sut = productService.getAll();
        assertTrue(sut.isEmpty());
    }

    @Test
    public void removeProduct_WithExistingId_doesNotThrowAnyException(){
        Long existentId = 1L;
        doReturn(Optional.of(PRODUCT)).when(productRepository).findById(existentId);
        doNothing().when(productRepository).deleteById(existentId);
        assertDoesNotThrow(() -> productService.deleteById(existentId));
    }

    @Test
    public void removeProduct_WithNonExistentId_ThrowsException(){
        Long nonExistentId = 1L;
        doThrow(new EntityNotFoundException("Id não existente")).when(productRepository).deleteById(nonExistentId);
        assertThrows(EntityNotFoundException.class, () -> productService.deleteById(nonExistentId));
    }

}
