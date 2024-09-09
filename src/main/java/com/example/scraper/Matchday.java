package com.example.scraper;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Matchday {
    private int matchdayID;
    private ArrayList<Match> matches;

    public Matchday(int matchdayID){
        this.matchdayID = matchdayID;
    }
}
