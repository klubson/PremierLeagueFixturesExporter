package com.example.scraper;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Season {
    private String seasonID;
    private ArrayList<Matchday> matchdays;

    public Season(String seasonID){
        this.seasonID = seasonID;
    }
}
