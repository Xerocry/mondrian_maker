package com.xerocry.mondrian_maker.endpoints.dtos.mappers;

import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IAddress;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.IAddressFactory;
import com.xerocry.mondrian_maker.endpoints.dtos.entities.AddressDTO;
import com.xerocry.mondrian_maker.endpoints.dtos.mappers.interfaces.IAddressDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddressDTOMapper implements IAddressDTOMapper {

  //region Attributes
  IAddressFactory addressFactory;
  //endregion

  //region Constructors
  @Autowired
  public AddressDTOMapper( IAddressFactory addressFactory ) {

    //initialize dependencies
    this.addressFactory = addressFactory;
  }
  //endregion

  //region IAddressDTOMapper implementation
  @Override
  public IAddress toEntity( AddressDTO dto ) {

    //get new address instance
    IAddress address = this.addressFactory.create();

    //fill the instance
    address.setStreetName( dto.streetName );
    address.setPostalCode( dto.postalCode );

    //return the instance
    return address;
  }

  @Override
  public AddressDTO toDTO( IAddress address ) {

    //get new dto instance
    AddressDTO dto = new AddressDTO();

    //fill this dto's attributes
    dto.streetName = address.getStreetName();
    dto.postalCode = address.getPostalCode();

    //return the dto
    return dto;
  }

  @Override
  public Iterable<IAddress> toEntities( List<AddressDTO> dtos ) {

    Collection<IAddress> addresses = new ArrayList<IAddress>();

    for ( AddressDTO dto : dtos ) {
      addresses.add( this.toEntity( dto ) );
    }

    return addresses;
  }

  @Override
  public List<AddressDTO> toDTOs( Iterable<IAddress> addresses ) {

    List<AddressDTO> dtos = new ArrayList<AddressDTO>();

    for ( IAddress address : addresses ) {
      dtos.add( this.toDTO( address ) );
    }

    return dtos;
  }
  //endregion
}
