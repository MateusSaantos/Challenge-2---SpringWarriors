package com.compassuol.sp.challenge.ecommerce.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 100)
    private String street;
    private Integer number;
    @Column(length = 100)
    private String complement;
    @Column(length = 50)
    private String city;
    @Column(length= 20)
    private String state;
    @Column(length=8)
    private String postalCode;
}
