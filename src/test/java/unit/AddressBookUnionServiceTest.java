package unit;

import com.codechallenge.pwc.au.services.AddressBookUnionService;
import com.codechallenge.pwc.au.services.IAddressBookUnionService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Unit test for AddressBookUnionService
 */
public class AddressBookUnionServiceTest {

    /* dependencies */
    private IAddressBookUnionService service;

    @Before
    public void run_once_per_test() {
        service = new AddressBookUnionService();
    }


    @Test
    public void given_address_book_union_should_produce_unique_map() {

        /* given two address books */
        Map<String, String> book1 = new TreeMap<>(String::compareToIgnoreCase);
        book1.put("Joshua", "0479109809");
        book1.put("Zoe", "0479109801");
        book1.put("Edith", "0479109802");

        Map<String, String> book2 = new TreeMap<>(String::compareToIgnoreCase);
        book2.put("Joshua", "0479109809");
        book2.put("Roxanne", "0479109801");
        book2.put("Edith", "0479109802");
        book2.put("Jordan", "0479109804");

        /* the union of these books (book 1 and book 2) */
        SortedMap<String, String> result = service.union(book1, book2);

        /* expectation and assertion */
        Assert.assertThat(result.toString(), CoreMatchers.is("{Jordan=0479109804, Roxanne=0479109801, Zoe=0479109801}"));

    }

}
