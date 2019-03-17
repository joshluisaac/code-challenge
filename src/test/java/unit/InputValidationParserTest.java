package unit;


import com.codechallenge.pwc.au.components.InputValidationParser;
import com.google.gson.JsonSyntaxException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for InputValidationParser
 */
public class InputValidationParserTest {

    private static final String INVALID_JSON = "{joshua:,zoe:87655456787}";
    private static final String VALID_JSON = "{joshua:04786454,zoe:87655456787}";

    private static final String VALID_PHONE_NO = "0479109809";
    private static final String INVALID_PHONE_NO = "04786454YKKX";



    @Test(expected = JsonSyntaxException.class)
    public void throw_JsonSyntaxException_when_Json_is_invalid(){
        InputValidationParser.isValidJson(INVALID_JSON);
    }

    @Test
    public void return_true_when_JSON_is_valid(){
        boolean result = InputValidationParser.isValidJson(VALID_JSON);
        Assert.assertThat(result,CoreMatchers.is(true));
    }

    @Test
    public void return_false_when_phone_number_is_invalid(){
        boolean result = InputValidationParser.isValidPhoneNumber(INVALID_PHONE_NO);
        Assert.assertThat(result,CoreMatchers.is(false));
    }


    @Test
    public void return_true_when_phone_number_is_valid(){
        boolean result = InputValidationParser.isValidPhoneNumber(VALID_PHONE_NO);
        Assert.assertThat(result,CoreMatchers.is(true));
    }
}
