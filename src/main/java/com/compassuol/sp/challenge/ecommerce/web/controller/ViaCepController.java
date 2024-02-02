package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.entities.Address;
import com.compassuol.sp.challenge.ecommerce.services.ViaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViaCepController {


    @Autowired
    private ViaCepService viaCepService;

    @Operation(
            summary = "Retorna informações de endereços",
            description = "Recupera informações de endereço com base no CEP.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Informações de endereço recuperadas com sucesso.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Endereço não encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
            }
    )
    @GetMapping("/{cep}")
    public Address getCep(@PathVariable String cep) {
        Address address = viaCepService.buscaEnderecoPorCep(cep);
        return address;


    }
}