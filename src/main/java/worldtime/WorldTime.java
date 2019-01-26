package worldtime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WorldTime {

    //private final String baseurl = "https://www.worldtimeserver.com/current_time_in_";
    //private final String endurl = ".aspx";
    private final String baseurl = "https://time.is/";

    public String showTime(String countryCode) {

        try {
            String url = baseurl + countryCode;
            Document document = Jsoup.connect(url).get();

            //String time = document.select("#theTime").text();
            //String date = document.select(".desc .font6").text();
            String time = document.select("#twd").text();
            String date = document.select("#dd").text();

            // Henter navnet på plassen du "søker" etter
            String place = document.select("#msgs").text();

            //String info = "Time in " + countryCode + ": " + time + " Date in " + countryCode + ": " + date;
            String info = place + " " + time + " - " + date;
            return info;
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
    }
}