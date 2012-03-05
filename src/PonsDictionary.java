import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 05.03.12
 * Time: 21:19
 */
public class PonsDictionary {
    private final String ponsURL = null;

    public static String get(String entry) {

        return null;
    }

    public void todo() {
    String timetableURL = sourceUrlForTimetables + lineNumber + ".html";

        Document doc = null;
        try {
            System.out.println("Debug");
            doc = Jsoup.connect(timetableURL).get();
            System.out.println("Debug");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Elements newsHeadlines = doc.select("#mp-itn b a");
    }
    public static List<BusStation> fetchPrimaryDirection(Document doc) {
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
    }

}
