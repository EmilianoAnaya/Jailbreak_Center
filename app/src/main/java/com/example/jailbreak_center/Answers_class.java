package com.example.jailbreak_center;

public class Answers_class {
    private String id_answer;
    private String id_user;
    private String id_forum;
    private String answer;

    public Answers_class(){
        this.id_answer = "";
        this.id_user = "";
        this.id_forum = "";
        this.answer = "";
    }

    public void set_IDAnswer(String id_answer){
        this.id_answer = id_answer;
    }
    public void set_IDUser(String id_user){
        this.id_user = id_user;
    }
    public void set_IDForum(String id_forum){
        this.id_forum = id_forum;
    }
    public void set_Answer(String answer){
        this.answer = answer;
    }

    public String get_IDAnswer(){
        return this.id_answer;
    }
    public String get_IDUser(){
        return this.id_user;
    }
    public String get_IDForum(){
        return this.id_forum;
    }
    public String get_Answer(){
        return this.answer;
    }
}
