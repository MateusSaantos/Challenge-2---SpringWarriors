package com.compassuol.sp.challenge.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductInOrder> products;

    @JsonIgnoreProperties("order")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private Payment paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "subtotal_value")
    private Double subtotalValue;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "total_value")
    private Double totalValue;
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdDate;
    @Column(name = "cancel_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime cancelDate;
    @Column(name = "cancel_reason",length = 100)
    private String cancelReason;



    public Order(Address address, Payment paymentMethod, Double subtotalValue,
                 Double discount,List<ProductInOrder> order) {


        this.products = products;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.status = Status.CONFIRMED;
        this.subtotalValue = subtotalValue;
        this.discount = discount;
        this.createdDate = LocalDateTime.parse(Instant.now().toString());
        this.cancelDate = null;
        this.cancelReason = null;
        calculateTotalValue();
    }


    public void setStatus(Status status){
            this.status = status;
        }

    public void attStatus(String value){
            this.status = Status.valueOf(value);

    }

    public enum Status{
        CONFIRMED,SENT,CANCELED
    }

    public enum Payment{
        CREDIT_CARD, BANK_TRANSFER, CRYPTOCURRENCY, GIFT_CARD, PIX, OTHER
    }

    public void calculateTotalValue() {
        if (paymentMethod == Payment.PIX) {
            discount = (5 * subtotalValue) / 100;
        } else {
            discount = 0D;
        }
        totalValue = subtotalValue - discount;
    }

    public void calculateSubtotalValue(List<ProductInOrder> productsInOrder) {
        subtotalValue = 0D;

        for (ProductInOrder productInOrder : productsInOrder) {
            Float productPrice = productInOrder.getProduct().getValue();

            subtotalValue += (Float) (productPrice * productInOrder.getQuantity());

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
