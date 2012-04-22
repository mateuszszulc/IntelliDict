package dataproviders;

/**
 * Created by IntelliJ IDEA.
 * User: wro00305
 * Date: 20.04.12
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */
public interface PonsDatabase {
    void storeEntry(String newEntry);
    //void storeEntry(PonsEntry ponsEntry);

    String getEntry(String baseWord);
    //PonsEntry getEntry(String baseWord);

    boolean deleteEntry(String baseWord);
}
