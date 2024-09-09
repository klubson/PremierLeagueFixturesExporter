package com.example.scraper;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.nodes.*;
import org.jsoup.select.*;
@Getter
@Setter
public class WebsiteScraper {
    private Document document;


    public WebsiteScraper(){
        this.document = downloadWebsite();
    }

    public Document downloadWebsite(){
        Document document;
        try {
            // fetching the target website
            document = Jsoup.connect("https://onefootball.com/en/competition/premier-league-9/fixtures").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }
}
