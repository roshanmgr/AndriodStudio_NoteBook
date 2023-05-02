package com.example.notebook;

public class Note {
    String title,discription,category;
    Integer color;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Note(String title, String discription, String category, Integer color, long id) {
        this.title = title;
        this.discription = discription;
        this.category = category;
        this.color = color;
        this.id = id;
    }

    public Note(String title, String discription, String category, Integer color) {
        this.title = title;
        this.discription = discription;
        this.category = category;
        this.color = color;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCategory() {
        return category;
    }

    public void setColor(String Color){ this.color = color;}

    public Integer getColor(){
        return color;
    }


    public void setCategory(String category) {
        this.category = category;
    }
}
