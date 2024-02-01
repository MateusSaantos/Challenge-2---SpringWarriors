package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.Address;
import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressCreateDto;
import org.modelmapper.ModelMapper;

public class AddressMapper {



    public static AddressCreateDto toDto(Address address){
        ModelMapper mapper = new ModelMapper();
        return   mapper.map(address, AddressCreateDto.class);


    }
    public Address toAddress(AddressCreateDto dto){
        return  new ModelMapper().map(dto, Address.class);
    }


}
