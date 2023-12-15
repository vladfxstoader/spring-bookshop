package com.example.bookshop.mapper;

import com.example.bookshop.dto.AddressDto;
import com.example.bookshop.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address map(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setBuilding(addressDto.getBuilding());
        address.setBuildingNo(addressDto.getBuildingNo());
        address.setCity(addressDto.getCity());
        address.setCounty(addressDto.getCounty());
        return address;
    }

    public AddressDto map(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setBuilding(address.getBuilding());
        addressDto.setBuildingNo(address.getBuildingNo());
        addressDto.setCity(address.getCity());
        addressDto.setCounty(address.getCounty());
        return addressDto;
    }
}
