package com.example.scraper;

public class Menu {
    private WebsiteScraper websiteScraper;
    private ElementsExtractor elementsExtractor;
    private Season season;
    public Menu(){
        initialize();
    }

    public void initialize(){
        websiteScraper = new WebsiteScraper();
        elementsExtractor = new ElementsExtractor(websiteScraper.downloadWebsite());
        season = new Season("2024/2025");
    }
}
