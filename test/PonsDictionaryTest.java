import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void testReturnHomeForDom()
    {
        String result = PonsDictionary.get("Dom");
        //Assert.assertEquals(result, "Home");
    }

    @After
    public void tearDown() throws Exception {

    }
}
