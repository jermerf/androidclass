package com.example.a4listsandmenu.model;

public class CatPost {
    private String title;
    private String content;
    private int iconResource;

    public CatPost(String title, String content, int iconResource){
        this.title = title;
        this.content = content;
        this.iconResource = iconResource;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getIconResource() {
        return iconResource;
    }
    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
