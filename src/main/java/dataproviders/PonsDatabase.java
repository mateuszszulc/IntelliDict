package dataproviders;

import model.PonsEntry;

/**
 * Created by IntelliJ IDEA.
 * User: wro00305
 * Date: 20.04.12
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */
public interface PonsDatabase {
    //boolean storeEntry(String newEntry);
    boolean storeEntry(PonsEntry newPonsEntry);

    String getEntry(String baseWord);
    //PonsEntry getEntry(String baseWord);

    boolean deleteEntry(String baseWord);
}
