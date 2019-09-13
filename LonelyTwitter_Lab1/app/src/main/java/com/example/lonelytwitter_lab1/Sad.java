package com.example.lonelytwitter_lab1;

import java.util.Date;

public class Sad extends Mood {
    public Sad(String mood) {

        super(mood);
    }

    public Sad(String mood, Date date) {

        super(mood, date);
    }

    @Override
    public String moodText() {
        return "I'm Sad" ;
    }
}
