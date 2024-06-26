package com.compassuol.sp.challenge.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<ProductInOrder> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
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
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;
    @Column(name = "cancel_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime cancelDate;
    @Column(name = "cancel_reason",length = 100)
    private String cancelReason;

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
