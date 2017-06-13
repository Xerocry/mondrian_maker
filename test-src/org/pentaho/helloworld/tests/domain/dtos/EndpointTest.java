import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xerocry.mondrian.MainClass;

import java.io.IOException;

public class EndpointTest {
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
}
