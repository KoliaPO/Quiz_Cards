package com.QuizCard;

public class QuizCard{

    private String questions;
    private String answer;

    QuizCard(String questions, String answer){
        this.questions = questions;
        this.answer = answer;
    }

    public String getQuestions() {
        return questions;
    }

    public String getAnswer() {
        return answer;
    }
}
