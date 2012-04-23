package dictionaries;

import dataproviders.PonsDatabase;
import dataproviders.PonsNullDatabase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 05.03.12
 * Time: 21:19
 */
public class PonsDictionary {
    private static final String ponsURL = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl";
    private static final String polishToGerman = "http://pl.pons.eu/polski-niemiecki/";
    private static final String germanToPolish = "http://pl.pons.eu/niemiecki-polski/";

    private static final String polishToGermanOneDirection = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl&in=pl&lf=pl";
    private static final String polishToGermanTwoDirection = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl&in=&lf=pl";

    private static final String germanToPolishOneDirection = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl&in=de&lf=de";
    private static final String germanToPolishTwoDirection = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl&in=&lf=de";

    private static Document doc;
    private PonsDatabase ponsDatabase = new PonsNullDatabase();  //new PonsInFileDatabase("ponsStorage.txt");

    public static List<Map> getPonsEntry(String entry) {
        loadDocument();

        Elements ponsEntryInHtml = extractPonsEntryInHtmlForSpecifiedLanguage("pl");

        List<Map> ponsEntry = new ArrayList<Map>();

        for (Element ponsSubEntryInHtml : ponsEntryInHtml) {
            String ponsSubEntryHeader = ponsSubEntryInHtml.getElementsByTag("thead").first().text();
            Element ponsSubEntryInTBodyElement = ponsSubEntryInHtml.getElementsByTag("tbody").first();
            Map<String, String> ponsSubEntry = extractPonsSubEntryFromTBodyElement(ponsSubEntryInTBodyElement);
            ponsEntry.add(ponsSubEntry);
        }
        return ponsEntry;
    }

    private static void loadDocument() {
        try {
            doc = Jsoup.connect(ponsURL).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> extractPonsSubEntryFromTBodyElement(Element tbody) {
        Map<String, String> ponsSubEntry = new HashMap();
        Elements rows = tbody.children();
        for (Element row : rows) {
            Element source = row.getElementsByClass("source").first();
            Element target = row.getElementsByClass("target").first();
            ponsSubEntry.put(source.text(), target.text());
        }
        return ponsSubEntry;
    }

    public static Elements extractPonsEntryInHtmlForSpecifiedLanguage(String language) {
        Element languageDivElement = doc.getElementById(language);
        //Element results = languageDivElement.getElementsByClass("results").first();
        return languageDivElement.select("div.rom.first").first().getElementsByClass("translations");
    }
}
