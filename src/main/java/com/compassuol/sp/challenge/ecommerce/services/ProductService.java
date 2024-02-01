package com.compassuol.sp.challenge.ecommerce.services;
import com.compassuol.sp.challenge.ecommerce.entities.Product;
import com.compassuol.sp.challenge.ecommerce.exception.DuplicateProductNameException;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.exception.ProductValidationException;
import com.compassuol.sp.challenge.ecommerce.web.dto.order.ProductOrderDto;
import com.compassuol.sp.challenge.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String productName = product.getName();

        if(productRepository.existsByNameAndIdNot(productName, id))
        {
            throw new DuplicateProductNameException(String.format("Produto com nome {%s} ja existe", productName));
        }

        prod.setDescription(product.getDescription());
        prod.setValue(product.getValue());
        prod.setName(product.getName());
        return prod;
    }

    @Transactional
    public Product create( Product product) {
        if(productRepository.existsByName(product.getName()))
            throw new DuplicateProductNameException(String.format("Produto com nome {%s} já existe",product.getName()));
        try {
            return productRepository.save(product);
        }
        catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new ProductValidationException(String.format("Nome %s já cadastrado", product.getName()));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    //PEGANDO OS PREÇOS DOS PRODUTOS
    @Transactional
    public Map<Long, Float> getProductPrices(List<ProductOrderDto> productDtos) {
        List<Long> productIds = productDtos.stream()
                .map(ProductOrderDto::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllById(productIds);

        Map<Long, Float> productPrices = new HashMap<>();

        for (Product product : products) {
            productPrices.put(product.getId(), product.getValue());
        }
        return productPrices;
    }
}
