package com.example.lonelytwitter_lab1;

import java.util.Date;

public class ImportantTweet extends Tweet {
    public ImportantTweet(String text) {
        super(text);
    }

    public ImportantTweet(String text, Date date) {
        super(text, date);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }
}
