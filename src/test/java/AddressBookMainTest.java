//import com.opencsv.exceptions.CsvValidationException;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class AddressBookMainTest {

    @Test
    public void givenClass_shouldReturnConnection() {
        ConnectionCreate jdbc = new ConnectionCreate();
        Connection con = jdbc.makeConnection();
        Assert.assertNotNull(con);
    }

    @Test
    public void givenConnection_shouldReadFromTable() throws SQLException {
        AddressBook addressBook = new AddressBook();
        Assert.assertNotNull(addressBook.readDB());
    }

    @Test
    public void givenName_shouldReturnIfSync() throws SQLException {
        AddressBook addressBook = new AddressBook();
        addressBook.readDB();
        Assert.assertTrue(addressBook.checkSync("Ashish Aggarwal"));
    }

    @Test
    public void givenContact_shouldInsertInDB() throws SQLException {
        AddressBook addressBook = new AddressBook();
        addressBook.readDB();
        int size = addressBook.sizeOfAddressBook();
        Contact contact = new Contact("Sumit","Sharma",
                "Mohali","Mohali","Punjab","161200",
                "8956989898","sum@gmail.com",new Date(1000000L));
        addressBook.insert(contact);
        addressBook.readDB();
        Assert.assertEquals(7,size+1);

    }
/*
    @Test
    public void givenStartAndEndDate_shouldReturnNumberOfPeople() throws SQLException {
        AddressBook addressBook = new AddressBook();
        addressBook.readDB();
        Assert.assertEquals(2,addressBook.findDoj("2018-01-01","2019-01-01"));
    }

    @Test
    public void givenStateAndCity_shouldReturnNumberOfPeople() throws SQLException {
        AddressBook addressBook = new AddressBook();
        addressBook.readDB();
        //Assert.assertEquals(2,addressBook.retrieveByState("California"));
        Assert.assertEquals(2,addressBook.retrieveByCity("California"));
    }



    @Test
    public void givenAddressBook_shouldInsertAll() throws IOException, SQLException {
        AddressBook addressBook = new AddressBook();
        addressBook.readDB();
        int size = addressBook.sizeOfAddressBook();
        Contact contact1 = new Contact("Sumit","Sharma",
                "mohali","mohali","punjab","160000",
                "98989898","sam@gmail.com",new Date(1000000L));
        Contact contact2 = new Contact("Sumit","Sharma",
                "Chandigarh","Chandigarh","Punjab","260000",
                "819809239","sam@gmail.com",new Date(1000000L));

        ArrayList<Contact> arrayList = new ArrayList<>();
        arrayList.add(contact1);
        arrayList.add(contact2);
        addressBook.insertMultiple(arrayList);
        addressBook.readDB();
        Assert.assertEquals(20,size+3);
    }

    @Test
    public void givenJsonServer_shouldPerformIOOperation() throws SQLException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;

        AddressBook addressBook = new AddressBook();
        ArrayList<Contact> list;
        list = addressBook.readDB();
        for(Contact contact : list){
            LinkedHashMap<String,String> details = new LinkedHashMap<>();
            details.put("FirstName",contact.getFirst());
            details.put("LastName",contact.getLast());
            details.put("Address",contact.getAddress());
            details.put("City",contact.getCity());
            details.put("State",contact.getState());
            details.put("zip",contact.getZip());
            details.put("phone",contact.getPhone());
            details.put("email",contact.getEmail());
            RestAssured.given().
                    contentType(ContentType.JSON).
                    accept(ContentType.JSON).
                    body(details).
                    when().
                    post("/contacts/create");


        }


    }*/
}