package com.github.Mehmet;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScraperExample {
    public static void main(String[] args) throws IOException {
        String url = "https://www.cimri.com/oyuncu-kulakliklari/en-ucuz-hyperx-cloud-ii-wireless-oyuncu-kulakligi-fiyatlari,1048058496";
        Document doc = Jsoup.connect(url).get();
        Elements priceElement = doc.select("div.product-prices > span[itemprop=price]");
        String price = priceElement.text();
        System.out.println("The price is: " + price);
    }
}
