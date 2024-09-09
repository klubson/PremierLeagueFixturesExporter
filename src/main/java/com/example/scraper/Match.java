package com.example.scraper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class Match {
    private int matchID;
    private int matchdayMatchID;
    private String homeSide;
    private String awaySide;
    private Date fixtureDate;
    private String fixtureTime;

    public Match(int matchID){
        this.matchID = matchID;
    }

}
