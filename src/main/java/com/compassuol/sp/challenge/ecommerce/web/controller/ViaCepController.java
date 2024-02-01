package com.compassuol.sp.challenge.ecommerce.web.controller;

import com.compassuol.sp.challenge.ecommerce.entities.Address;
import com.compassuol.sp.challenge.ecommerce.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViaCepController {


    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public Address getCep(@PathVariable String cep) {
        Address address = viaCepService.buscaEnderecoPorCep(cep);
        return address;


    }
}