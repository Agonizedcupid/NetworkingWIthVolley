package com.aariyan.networkingwithvolley.Model;

public class DataModel {

    private String userName;
    private int likes;
    private int comments;
    private String imageUrl;

    public DataModel(){}

    public DataModel(String userName, int likes, int comments, String imageUrl) {
        this.userName = userName;
        this.likes = likes;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
