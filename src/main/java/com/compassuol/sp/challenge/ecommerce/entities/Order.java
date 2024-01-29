package com.compassuol.sp.challenge.ecommerce.entities;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Product> products;
    @Column (name = "address", length = 600)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private Payment paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "subtotal_value")
    private Long subtotalValue;
    @Column(name = "discount")
    private Long discount;
    @Column(name = "total_value")
    private Long totalValue;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;
    @Column(name = "cancel_reason")
    private String cancelReason;


    public enum Status{
        CONFIRMED, SENT, CANCELED
    }

    public enum Payment{
        CREDIT_CARD, BANK_TRANSFER, CRYPTOCURRENCY, GIFT_CARD, PIX, OTHER
    }

    public void calculateTotalValue() {
        if (paymentMethod == Payment.PIX) {
            discount = (5 * subtotalValue) / 100;
        } else {
            discount = 0L;
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
