import org.junit.Before;
import org.junit.Test;
import org.xerocry.mondrian.MainClass;

import java.io.IOException;

public class UserDTOTest {
    String outputLocation;

    @Before
    public void initialize() {
        outputLocation = "outputjcl";
    }

    @Test
    public void mainTest() throws IOException {
        String[] args = {"Vtiger.xml", "outputSchema"};
        MainClass.main(args);
    }

/*  @Test
  public void convertUserToUserDTO() {

    //test dependencies
    IAddressFactory addressFactory = new AddressFactory();
    IUserFactory userFactory = new UserFactory(addressFactory);
    IAddressDTOMapper addressDTOMapper = new AddressDTOMapper(addressFactory);
    IUserDTOMapper userDTOMapper = new UserDTOMapper(userFactory, addressDTOMapper);

    IAddress address = addressFactory.create();
    address.setStreetName( "test street name" );
    address.setPostalCode( "test postal code" );
    IUser user = userFactory.create( "test user", "test password", address, 30 );
    UserDTO userDTO = userDTOMapper.toDTO( user );

    Assert.assertEquals( userDTO.userName, user.getUserName() );
    Assert.assertEquals( userDTO.password, user.getPassword() );
    Assert.assertEquals( userDTO.address.streetName, user.getAddress().getStreetName() );
    Assert.assertEquals( userDTO.address.postalCode, user.getAddress().getPostalCode() );
  }*/
}
