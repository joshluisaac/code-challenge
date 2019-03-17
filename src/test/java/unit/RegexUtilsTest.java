package unit;

import com.codechallenge.pwc.au.utils.RegexUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit test for RegexUtils
 */
public class RegexUtilsTest {


    @Test
    public void i_expect_false_when_phone_number_fails_regex(){
        /* Given a phone number */
        String phoneNumber = "908766566K";

        /* When i check if it matches a given regex */
        boolean result = RegexUtils.hasMatch(phoneNumber,"^[0-9]+$");

        /* I expect false when it fails */
        Assert.assertThat(result, CoreMatchers.is(false));
    }


    @Test
    public void i_expect_true_when_phone_number_passes_regex(){
        /* Given a phone number */
        String phoneNumber = "908766566";

        /* When i check if it matches a given regex */
        boolean result = RegexUtils.hasMatch(phoneNumber,"^[0-9]+$");

        /* I expect true when it passes */
        Assert.assertThat(result, CoreMatchers.is(true));
    }
}
