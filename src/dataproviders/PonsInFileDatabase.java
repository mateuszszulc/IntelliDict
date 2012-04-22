package dataproviders;

import model.PonsRow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wro00305
 * Date: 20.04.12
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public class PonsInFileDatabase implements PonsDatabase {
    private List<PonsRow> ponsEntries;
    private File databaseFile;
    private String databaseFilename;

    public PonsInFileDatabase(String databaseFilename) {
        ponsEntries = new ArrayList<PonsRow>();
        this.databaseFilename = databaseFilename;
        loadDatabase();
    }

    private void loadDatabase() {
        databaseFile = new File(databaseFilename);

        try {
            createDatabaseFileIfNotExist();
            readPonsEntriesFromFile();
        } catch (IOException e) {
            System.out.println("CAN'T CREATE OR READ FILE!!!!!!!");
        }

    }

    private void readPonsEntriesFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(databaseFilename));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            PonsRow ponsRow = PonsRow.createInstanceFromRawText(line);
            if (ponsRow != null) ponsEntries.add(ponsRow);
        }

        bufferedReader.close();
    }

    private void createDatabaseFileIfNotExist() throws IOException {
        if (!databaseFile.exists()) {
            databaseFile.createNewFile();
        }
    }

    public List<String> getPonsEntriesAsListOfRawText() {
        List<String> ponsEntriesAsRowText = new ArrayList<String>();
        for (PonsRow ponsRow : ponsEntries) {
            ponsEntriesAsRowText.add(ponsRow.toString());
        }
        return ponsEntriesAsRowText;
    }

    public int getItemCount() {
        return ponsEntries.size();
    }

    @Override
    public void storeEntry(String newEntry) {
        if (!ponsEntries.contains(PonsRow.createInstanceFromRawText(newEntry))) {
            ponsEntries.add(PonsRow.createInstanceFromRawText(newEntry));
            saveToDatabase(); //is this a right moment to save?
        }
    }

    @Override
    public boolean deleteEntry(String baseWord) {
        PonsRow ponsRowWithoutTranslation = new PonsRow(baseWord, "");
        return ponsEntries.remove(ponsRowWithoutTranslation);
    }

    public void saveToDatabase() {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(databaseFilename));
            List<String> allEntries = getPonsEntriesAsListOfRawText();

            for (String rawText : allEntries) {
                bufferedWriter.write(rawText);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public String getEntry(String baseWord) {
        PonsRow ponsRowWithoutTranslation = new PonsRow(baseWord, "");
        int indexOfBaseWord = ponsEntries.indexOf(ponsRowWithoutTranslation);
        return indexOfBaseWord != -1 ? ponsEntries.get(indexOfBaseWord).getTranslation() : "";
    }
}
