package com.compassuol.sp.challenge.ecommerce.web.dto.mapper;

import com.compassuol.sp.challenge.ecommerce.entities.Address;
import com.compassuol.sp.challenge.ecommerce.web.dto.address.AddressResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class AddressMapper {


    public AddressResponseDto toDto(Address adress){

        PropertyMap<Address, AddressResponseDto> props = new PropertyMap<Address, AddressResponseDto>() {
            @Override
            protected void configure() {

            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return   mapper.map(adress,AddressResponseDto.class);

    }
    public Address toAddress(AddressResponseDto dto){
        return  new ModelMapper().map(dto, Address.class);
    }


}
