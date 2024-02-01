package com.compassuol.sp.challenge.ecommerce.web.dto.address;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressResponseDto {


    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;

}
