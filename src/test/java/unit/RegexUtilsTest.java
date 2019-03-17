package unit;

import com.codechallenge.pwc.au.utils.RegexUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RegexUtilsTest {


    @Test
    public void i_expect_false_if_phone_number_fails_regex(){
        /* Given a phone number */
        String phoneNumber = "908766566K";

        /* When i check if it matches a given regex */
        boolean result = RegexUtils.hasMatch(phoneNumber,"^[0-9]+$");

        /* I expect false when it fails */
        Assert.assertThat(result, CoreMatchers.is(false));
    }
}
