package com.example.lonelytwitter_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NormalTweet normalTweet = new NormalTweet( "This is a normal tweet");
        ImportantTweet importantTweet = new ImportantTweet("This is a important tweet");

        ArrayList<Tweet> tweetlist = new ArrayList<Tweet>();

        tweetlist.add(normalTweet);
        tweetlist.add(importantTweet);

        System.out.println(tweetlist.get(0).getText());

        Happy happy= new Happy("Happy");
        System.out.println(happy.moodText());


    }
}
