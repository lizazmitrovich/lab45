package com.example.files.Classes;


public class Post {
    private String imgString;

    private String title;
    private String description;

    public Post(){
        this.imgString = null;

        this.title = "";
        this.description = "";
    }

    public Post(String image, String title, String description){
        this.imgString = image;

        this.title = title;
        this.description = description;
    }

    public void setImgString(String imgString) {
        this.imgString = imgString;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgString() {
        return imgString;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
