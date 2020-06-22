package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuizApiController {

    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();

    @GetMapping("/quiz")
    public Quiz quiz(){
        int index = new Random().nextInt(quizzes.size());
        return quizzes.get(index);
    }
    //showメソッド
    @GetMapping("/show")
    public List<Quiz> show(){
        return quizzes;
    }

    //createメソッド
    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer){
        //String quiz = question + ":" + answer;
        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);
    }

    //checkメソッド
    @GetMapping("/check")
    public String check(@RequestParam String question, @RequestParam boolean answer){
        for (Quiz quiz:quizzes){
            if(quiz.getQuestion().equals(question)){
                if (quiz.isAnswer() == answer){
                    return "正解";
                }else{
                    return "不正解";
                }
            }
        }
        return "存在しない";
    }

    @PostMapping("/save")
    public String save() {
        try {
            quizFileDao.write(quizzes);
            return "ファイルに保存しました";
        } catch (IOException e) {
            e.printStackTrace();
            return "ファイルの保存に失敗しました";
        }
    }
    @GetMapping("/load")
    public String load(){
        try {
            quizzes = quizFileDao.read();
            return "ファイルを読み込みました";
        } catch (IOException e) {
            e.printStackTrace();
            return "ファイルの読み込みに失敗しました";
        }
    }
}
