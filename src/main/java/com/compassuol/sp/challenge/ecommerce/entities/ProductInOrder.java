package com.compassuol.sp.challenge.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_in_order")
public class ProductInOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name ="order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

}
