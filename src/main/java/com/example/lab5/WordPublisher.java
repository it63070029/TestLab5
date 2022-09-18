package com.example.lab5;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
@RestController
public class WordPublisher {
    protected Word words;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value="/addBad/{word}")
    public ArrayList<String> addBadWord(@PathVariable("word") String s){
        this.words.badWords.add(s);
        return this.words.badWords;
    }
    @RequestMapping(value="/delBad/{word}")
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s){
        for (int i = 0; i < words.badWords.size(); i++) {
            if(words.badWords.get(i).equals(s)){
                this.words.badWords.remove(i);
                return this.words.badWords;
            }

        }
        return null;
    }
    @RequestMapping(value="/addGood/{word}")
    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
        this.words.goodWords.add(s);
        return this.words.goodWords;

    }
    @RequestMapping(value="/delGood/{word}")
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s){
        for (int i = 0; i < words.goodWords.size(); i++) {
            if(words.goodWords.get(i).equals(s)){
                this.words.goodWords.remove(i);
                return this.words.goodWords;
            }
        }
        return null;
    }
    @RequestMapping(value="/proof/{sentence}")
    public String proofSentence(@PathVariable("sentence") String s){
        for (int i = 0; i < words.goodWords.size(); i++) {
            for (int j = 0; j < words.badWords.size(); i++) {
                if(s.contains(words.goodWords.get(i)) && s.contains(words.badWords.get(j))){

                    rabbitTemplate.convertAndSend("MyFanoutExchange","",s);
                    return "Found Bad & Good Word";

                }
                else if (s.contains(words.goodWords.get(i))) {
                    rabbitTemplate.convertAndSend("MyDirectExchange","good",s);
                    return "Found Good Word";
                }
                else if (s.contains(words.badWords.get(j))) {
                    rabbitTemplate.convertAndSend("MyDirectExchange","bad",s);
                    return "Found Bad Word";
                }
            }

        }
        return "Not Found";
    }
}
