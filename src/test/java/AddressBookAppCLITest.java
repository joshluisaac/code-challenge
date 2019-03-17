import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Integration test for AddressBookAppCLI
 */
public class AddressBookAppCLITest {


    @Test
    public void display_usage_when_invalid_flag_is_passed_integration_test() throws Exception {
        Class<?> cls = Class.forName("com.codechallenge.pwc.au.AddressBookAppCLI");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[3];
        params[0] = "-F";
        params[1] = "James";
        params[2] = "98765445667";
        method.invoke(null, (Object) params);
    }

    @Test
    public void execute_store_command_integration_test_v1() throws Exception {
        Class<?> cls = Class.forName("com.codechallenge.pwc.au.AddressBookAppCLI");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[3];
        params[0] = "--store";
        params[1] = "James";
        params[2] = "98765445667";
        method.invoke(null, (Object) params);
    }


    @Test
    public void execute_store_command_integration_test_v2() throws Exception {
        Class<?> cls = Class.forName("com.codechallenge.pwc.au.AddressBookAppCLI");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[3];
        params[0] = "-s";
        params[1] = "James";
        params[2] = "98765445667";
        method.invoke(null, (Object) params);
    }


    @Test
    public void execute_union_command_integration_test_v1() throws Exception {
        Class<?> cls = Class.forName("com.codechallenge.pwc.au.AddressBookAppCLI");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[2];
        params[0] = "--union";
        params[1] = "{James:98765445667,Kennedy:865544788}";
        method.invoke(null, (Object) params);
    }


    @Test
    public void execute_union_command_integration_test_v2() throws Exception {
        Class<?> cls = Class.forName("com.codechallenge.pwc.au.AddressBookAppCLI");
        Method method = cls.getMethod("main", String[].class);
        String[] params = new String[2];
        params[0] = "-u";
        params[1] = "{James:98765445667,Kennedy:865544788}";
        method.invoke(null, (Object) params);
    }



}
