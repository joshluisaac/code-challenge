package unit;

import com.codechallenge.pwc.au.entities.AddressBook;
import com.codechallenge.pwc.au.entities.Contact;
import com.codechallenge.pwc.au.persistence.AddressBookDao;
import com.codechallenge.pwc.au.persistence.IDao;
import com.codechallenge.pwc.au.services.AddressBookService;
import com.codechallenge.pwc.au.services.IAddressBookService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AddressBookServiceTest {

    /* dependencies */
    private IDao dao;
    private AddressBook addressBook;
    private IAddressBookService service;

    @Before
    public void run_once_per_test() throws Exception {
        dao = new AddressBookDao();
        addressBook = new AddressBook();
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
        /* initial size of cache */
        int initialSize = addressBook.getContacts().size();

        /* given a contact */
        Contact contact = new Contact("Joshua", "0479109809");

        /* when i save the contact */
        service.saveContact(contact);

        /* i expect the cache size to remain the same */
        int finalSize = addressBook.getContacts().size();

        Assert.assertThat(finalSize, CoreMatchers.is(initialSize));
    }


}
