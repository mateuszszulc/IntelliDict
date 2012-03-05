import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
            doc = Jsoup.connect(ponsURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element de = doc.getElementById("pl");
        Element results = de.getElementsByClass("results").first();
        //Elements tables = results.getElementsByTag("table");
        //Elements romfirst = results.getElementsByClass("rom");
        Elements tables = results.getElementsByClass("translations");

        //Element div1 = results.children().first();

        //Element div2 = el1.getElementsByTag("div").first();
        //Element div3 = div2.getElementsByTag("div").first();


        //System.out.println(div3.html());
        return null;
    }

    /*public static List<BusStation> fetchPrimaryDirection(Document doc) {
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

        return null;
    } */

}
