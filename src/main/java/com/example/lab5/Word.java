package com.example.lab5;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class Word{
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;
    public Word(){
        this.badWords = new ArrayList<>();
        this.goodWords = new ArrayList<>();
        this.badWords.add("fuck");
        this.badWords.add("olo");
        this.goodWords.add("happy");
        this.goodWords.add("enjoy");
        this.goodWords.add("life");

    }
}
