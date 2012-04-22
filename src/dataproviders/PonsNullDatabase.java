package dataproviders;

import dataproviders.PonsDatabase;
import model.PonsEntry;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 22.04.12
 * Time: 14:24
 */
public class PonsNullDatabase implements PonsDatabase {

    @Override
    public boolean storeEntry(PonsEntry newPonsEntry) {
        return false;
    }

    @Override
    public String getEntry(String baseWord) {
        return "";
    }

    @Override
    public boolean deleteEntry(String baseWord) {
        return false;
    }
}
