package com.compassuol.sp.challenge.ecommerce.common;

import com.compassuol.sp.challenge.ecommerce.entities.Product;

public class ProductConstants {

    public static final Product PRODUCT = new Product(1L, "batata", "Deliciosa raiz vegetal, versátil na culinária, fonte de nutrientes e base para diversos pratos saborosos.", 2.5F);

    public static final Product UPDATED_PRODUCT = new Product(1L, "batata", "Updated description.", 3.0F);

    public static final Product INVALID_PRODUCT_NULL_INFO = new Product(null, null, null, -1.0F);

    public static final Product INVALID_PRODUCT_REPEATED_NAME = new Product(2L, "batata", "Produto com o mesmo nome.", 3.0F);

    public static final Product INVALID_PRODUCT_EMPTY_NAME = new Product(3L, "", "Produto sem nome.", 4.0F);

    public static final Product INVALID_PRODUCT_NEGATIVE_PRICE = new Product(4L, "cenoura", "Esse produto tem preço negativo.", -5.0F);


}
