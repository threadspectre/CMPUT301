package com.example.lonelytwitter_lab1;

import java.util.Date;

public class NormalTweet extends Tweet {
    public NormalTweet(String text) {
        super(text);
    }

    public NormalTweet(String text, Date date) {
        super(text, date);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
