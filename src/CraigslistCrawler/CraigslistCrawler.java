package CraigslistCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class CraigslistCrawler {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";

    public static void main(String[] args) throws IOException{

        String url = "https://sfbay.craigslist.org/d/apts-housing-for-rent/search/apa";
        CraigslistCrawler.Crawl(url);
    }

    public static void Crawl (String requestUrl) throws IOException{
        Document doc = Jsoup.connect(requestUrl).userAgent(USER_AGENT).timeout(1000).get();

        Elements ads = doc.getElementsByClass("result-info");
        System.out.println(ads.size());

        for(int i = 0;i < ads.size();i++) {
            System.out.printf("================= Ads %d ==============", i);
            System.out.println();
            // Extract link
            Elements link = ads.get(i).select("a[href*=html]");
            System.out.println("Title: "+ link.text());
            System.out.println("Detail URL: "+ link.attr("href"));

            // extract price and hood
            Elements sub = ads.get(i).getElementsByClass("result-price");
            System.out.println("Rent price: " + sub.text());
            Elements hood = ads.get(i).getElementsByClass("result-hood");
            System.out.println("Hood: " + hood.text());

        }
    }
}
