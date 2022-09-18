package com.example.lab5;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class Word{
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;
    public Word(){
        badWords.add("happy");
        badWords.add("enjoy");
        badWords.add("life");
        goodWords.add("fuck");
        goodWords.add("olo");

    }
}
