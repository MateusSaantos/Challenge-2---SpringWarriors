package com.compassuol.sp.challenge.ecommerce.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateStatusRequestDto {

    @NotBlank
    String status;
}
