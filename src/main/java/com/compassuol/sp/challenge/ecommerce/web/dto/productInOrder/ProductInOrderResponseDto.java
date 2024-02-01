package com.compassuol.sp.challenge.ecommerce.web.dto.productInOrder;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductInOrderResponseDto {
    private Long product_id;
    private int quantity;

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
