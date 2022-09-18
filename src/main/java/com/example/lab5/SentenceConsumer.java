package com.example.lab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer {
    protected Sentence sentence;
    @RabbitListener(queues = "bad")
    public void addBadSentence(String s){
        sentence.badSentences.add(s);
        System.out.println("In addBadSentence Method : ["+s+"]");
    }
    @RabbitListener(queues = "good")
    public void addGoodSentence(String s){
        sentence.goodSentences.add(s);
        System.out.println("In addGoodSentence Method : ["+s+"]");

    }
}
