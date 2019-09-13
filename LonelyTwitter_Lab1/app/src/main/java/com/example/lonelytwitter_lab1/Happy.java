package com.example.lonelytwitter_lab1;

import java.util.Date;

public class Happy extends Mood {
    public Happy(String mood) {
        super(mood);
    }

    public Happy(String mood, Date date) {
        super(mood, date);
    }

    @Override
    public String moodText() {
        return "I'm Happy" ;
    }
}
