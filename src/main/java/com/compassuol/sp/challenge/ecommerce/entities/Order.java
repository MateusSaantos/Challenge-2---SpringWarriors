package com.compassuol.sp.challenge.ecommerce.entities;
import jakarta.persistence.*;
import lombok.*;
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
    @OneToMany(mappedBy = "order")
    private List<ProductInOrder> order;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Address address;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    @Column(name = "cancel_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime cancelDate;
    @Column(name = "cancel_reason",length = 100)
    private String cancelReason;




    public Order(Address address, Payment paymentMethod, Long subtotalValue,
                 Long discount,List<ProductInOrder> order) {

        this.order = order;
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

    public void setStatus(String cancelReason){
        if (this.status != Status.SENT &&  LocalDateTime.parse(Instant.now().toString()).toLocalDate().until(createdDate.toLocalDate()).getDays() <= 90){
            this.status = Status.CANCELED;
            this.cancelDate = LocalDateTime.parse(Instant.now().toString());
            this.cancelReason = cancelReason;
        }
    }
    public void setStatus(Status status){
        if (this.status != Status.CANCELED && status == Status.SENT) {
            this.status = Status.SENT;
        }
    }


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
