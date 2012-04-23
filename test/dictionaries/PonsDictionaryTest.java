package dictionaries;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 05.03.12
 * Time: 21:31
 */
public class PonsDictionaryTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testReturnHomeForDom() {
        List<Map> listOfResults = PonsDictionary.getPonsEntry("Dupa");
        Assert.assertEquals("", "Home");

    }

    @After
    public void tearDown() throws Exception {

    }
}
