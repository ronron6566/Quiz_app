package com.example.quizapp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class QuizTest {

    @Test
    public void toStringWhenMaru(){
        Quiz quiz = new Quiz ( "問題文", true);
        assertThat(quiz.toString(), is("問題文 〇"));
    }
}

