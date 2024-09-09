package com.example.scraper;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class ElementsExtractor {
    private Elements teams;
    private Elements dates;
    private Elements times;
    private Elements matchdays;
    private int matchID;

    public ElementsExtractor(Document document){
        this.matchdays = extractMatchdaysContainerFromDocument(document);
        getMatchdaysFromContainer();
        this.teams = extractTeamsFromDocument(document);
        //this.dates = extractDatesFromDocument(document);
        //this.times = extractTimesFromDocument(document);
    }

    public Elements extractTeamsFromDocument(Document document){
        return document.getElementsByClass("SimpleMatchCardTeam_simpleMatchCardTeam__name__7Ud8D");
    }

    public Elements extractDatesFromDocument(Document document){
        return document.getElementsByClass("SimpleMatchCardTeam_simpleMatchCardTeam__name__7Ud8D");
    }

    public Elements extractTimesFromDocument(Document document){
        return document.getElementsByClass("SimpleMatchCardTeam_simpleMatchCardTeam__name__7Ud8D");
    }

    public Elements extractMatchdaysContainerFromDocument(Document document){
        return document.getElementsByClass("MatchCardsListsAppender_container__y5ame");
    }

    public ArrayList<Matchday> getMatchdaysFromContainer(){
        matchID = 1;
        AtomicInteger matchdayMatchID = new AtomicInteger();
        ArrayList<Matchday> matchdays = new ArrayList<>();
        this.matchdays.get(0).forEachNode(matchdayNode -> {
            int matchdayID = Integer.parseInt(matchdayNode
                    .childNode(0)//SectionHeader
                    .childNode(0)//Inside SectionHeader
                    .childNode(1)//h3
                    .childNode(0)//Inside h3
                    .toString()
                    .replace("Matchday ", ""));
            Matchday matchday = new Matchday(matchdayID);
            matchdayNode
                    .childNode(1)
                    .forEachNode(matchNode -> {
                        Match match = new Match(matchID);
                        matchID = matchID + 1;
                        final int[] teamID = {1};
                        matchNode
                                .childNode(0)//inside list item
                                .childNode(0)//match card
                                .childNode(0)//match content
                                .childNode(0)//teams
                                .forEachNode(teamNode -> {
                                    String teamName = teamNode
                                            .childNode(1)//team name container
                                            .childNode(0)//team name
                                            .toString();
                                    switch (teamID[0]){
                                        case 1:
                                            match.setHomeSide(teamName);
                                            break;
                                        case 2:
                                            match.setAwaySide(teamName);
                                            break;
                                    }
                                    teamID[0] = teamID[0] + 1;
                                });
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String matchDateString = matchNode
                                .childNode(0)//inside list item
                                .childNode(0)//match card
                                .childNode(0)//match content
                                .childNode(1)//match datetime container
                                .childNode(0)//match date
                                .toString();
                        String matchTimeString = matchNode
                                .childNode(0)//inside list item
                                .childNode(0)//match card
                                .childNode(0)//match content
                                .childNode(1)//match datetime container
                                .childNode(1)//match time
                                .toString();
                        try {
                            match.setFixtureDate(simpleDateFormat.parse(matchDateString));
                            match.setFixtureTime(matchTimeString);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        matchday.getMatches().add(match);
                        matchdayMatchID.set(matchdayMatchID.get() + 1);
                    });
            matchdays.add(matchday);
            matchdayMatchID.set(0);
        });
        return null;
    }

}
