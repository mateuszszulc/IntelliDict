package dictionaries;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: wro00305
 * Date: 20.04.12
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */
public class PonsInFileDatabaseTest {
    private PonsInFileDatabase ponsInFileDatabase;

    @Before
    public void setUp() throws Exception {
        ponsInFileDatabase = new PonsInFileDatabase("ponsStorage.txt");
    }

    @Test
    public void testGetPonsEntriesAsListOfRawText() throws Exception {
        for (String i : ponsInFileDatabase.getPonsEntriesAsListOfRawText()) {
            System.out.println(i);
        }

    }

    @Test
    public void testStoreEntry() {
        ponsInFileDatabase.storeEntry("jas=maaaaaaaaaaaaaazabawke");
        ponsInFileDatabase.storeEntry("mateusz=makasie");
    }
}
