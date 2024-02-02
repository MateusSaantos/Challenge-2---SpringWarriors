package com.compassuol.sp.challenge.ecommerce.web.dto.address;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressApiResponse {
    private String logradouro;
    private String localidade;
    private String uf;


}
