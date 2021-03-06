package unit;

import com.codechallenge.pwc.au.components.LocalStorageStreamStrategy;
import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.AddressBookDatabase;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.IAddressBookService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Unit test for AddressBookService
 */
public class AddressBookServiceTest {

    /* dependencies */
    private IDao dao;
    private AddressBook addressBook;
    private IAddressBookService service;

    @Before
    public void run_once_per_test() throws Exception {
        InputStream inputStream = new FileInputStream(new File(AddressBookDatabase.DATABASE));
        dao = new AddressBookDao();
        addressBook = new AddressBook(new LocalStorageStreamStrategy(inputStream));
        service = new AddressBookService(dao,addressBook);
    }

    @Test
    public void cache_should_increment_when_i_add_a_new_contact() throws IOException {
        /* initial size of cache */
        int initialSize = addressBook.getContacts().size();

        /* given a contact */
        Contact contact = new Contact("Mark", "0479109809");

        /* when i save the contact */
        service.saveContact(contact);

        /* i expect the cache size increase by 1 element */
        int finalSize = addressBook.getContacts().size();

        Assert.assertThat(finalSize, CoreMatchers.is(initialSize + 1));
    }


    @Test
    public void cache_should_not_increase_when_i_add_an_existing_contact() throws IOException {

        /* given a contact */
        Contact contact = new Contact("Simeon", "0479109809");

        /* when i save the contact */
        service.saveContact(contact);

        /* initial size of cache */
        int initialSize = addressBook.getContacts().size();

        /* when i save the contact */
        service.saveContact(contact);

        /* i expect the cache size to remain the same */
        int finalSize = addressBook.getContacts().size();

        Assert.assertThat(finalSize, CoreMatchers.is(initialSize));
    }


}
