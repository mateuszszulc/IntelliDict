import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 05.03.12
 * Time: 21:19
 */
public class PonsDictionary {
    private static final String ponsURL = "http://pl.pons.eu/dict/search/results/?q=dom&l=depl";

    public static String get(String entry) {
        Document doc = null;
        try {
            doc = Jsoup.connect(ponsURL).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements tables = getTranslationTablesForLanguage(doc, "pl");

        for ( Element table : tables ) {
            String groupHeader = table.getElementsByTag("thead").first().text();
            Element tbody = table.getElementsByTag("tbody").first();

            Map<String, String> groupItems = getGroupItems(tbody);
            //System.out.print(thead.text());
//            Element th = thead.getElementsByTag("th").first();
//            System.out.println(th.text());

        }

        System.out.println(tables.html());
        return null;
    }

    private static Map<String, String> getGroupItems(Element tbody) {
        Map<String, String> groupItems = new HashMap();
        Elements rows = tbody.children();
        for ( Element row : rows ) {
            Element source = row.getElementsByClass("source").first();
            Element target = row.getElementsByClass("target").first();

            System.out.println(source.text());
            System.out.println(target.text());
        }
          return groupItems;
    }

    public static Elements getTranslationTablesForLanguage(Document doc, String language) {
        Element languageDivElement = doc.getElementById(language);
        //Element results = languageDivElement.getElementsByClass("results").first();
        return languageDivElement.select("div.rom.first").first().getElementsByClass("translations");
    }

    /*public static unusedOldCode() {
        Elements linia = doc.getElementsByClass("linia");
        Element table = linia.first();
        Elements tbody = table.getElementsByTag("tbody");
        Elements trElements = tbody.first().getElementsByTag("tr");

        trElements.remove(0);
        trElements.remove(0);

        for (Element busStationTr : trElements) {
            Elements trChildrens = busStationTr.children();

            BusStation busStation = new BusStation();
            busStation.setRoute(trChildrens.get(0).text());
            busStation.setSourceUrl(trChildrens.get(1).getElementsByTag("a").first().attr("href"));
            busStation.setName(trChildrens.get(1).text());

            System.out.println("*******************************");
            System.out.print(trChildrens.get(1).getElementsByTag("a").first().attr("href") + "  ");
            System.out.println(trChildrens.get(1).text() + "  ");
        }

        //Element s = results.select("")
        //Elements tables = results.getElementsByTag("table");
        //Elements romfirst = de.select("div.rom.first");
        //Elements tables = results.getElementsByClass("translations");
        //Element div1 = results.children().first();
        //Element div2 = el1.getElementsByTag("div").first();
        //Element div3 = div2.getElementsByTag("div").first();


        return null;
    } */

}
