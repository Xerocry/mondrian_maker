package main;

import com.xerocry.mondrian_maker.MainClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by raskia on 5/9/2017.
 */
public class MainTest {
    String outputLocation;
    @Before
    public void initialize() {
        outputLocation = "outputjcl";
    }

    @Test
    public void mainTest() throws IOException {
        String[] args = {"vtiger/test.xml", "outputSchema"};
        MainClass.main(args);
    }


}
