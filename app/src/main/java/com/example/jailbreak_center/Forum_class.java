package com.example.jailbreak_center;

import java.io.Serializable;

public class Forum_class implements Serializable {
    private String id_foro;
    private String id_usuario;
    private String forum_title;
    private String forum_description;
    private String console_forum;

    public Forum_class(){
        this.id_foro = "";
        this.id_usuario = "";
        this.forum_title = "";
        this.forum_description = "";
        this.console_forum = "";
    }

    public void set_IDforos(String id_foro){
        this.id_foro = id_foro;
    }

    public void set_IDusuario(String id_usuario){
        this.id_usuario = id_usuario;
    }

    public void set_ForumTitle(String forum_title){
        this.forum_title = forum_title;
    }

    public void set_ForumDescription(String forum_description){
        this.forum_description = forum_description;
    }

    public void set_ConsoleForum(String console_forum){
        this.console_forum = console_forum;
    }

    public String get_IDforo(){
        return id_foro;
    }
    public String get_IDusuario(){
        return id_usuario;
    }
    public String get_ForumTitle(){
        return forum_title;
    }
    public String get_ForumDescription(){
        return forum_description;
    }
    public String get_ConsoleForum(){
        return console_forum;
    }
}
