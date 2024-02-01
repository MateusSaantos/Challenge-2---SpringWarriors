package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.Address;
import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressResponseDto;
import org.modelmapper.ModelMapper;

public class AddressMapper {


    public static AddressResponseDto toDto(Address address){
        ModelMapper mapper = new ModelMapper();
        return   mapper.map(address,AddressResponseDto.class);

    }
    public Address toAddress(AddressResponseDto dto){
        return  new ModelMapper().map(dto, Address.class);
    }


}
