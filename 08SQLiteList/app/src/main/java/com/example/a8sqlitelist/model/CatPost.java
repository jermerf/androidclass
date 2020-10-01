package com.example.a8sqlitelist.model;


import android.text.Editable;

/*
var post = {
        title: editTExt.value,
        content: content.value,
        iconResource: spinner.value
}

 */
public class CatPost {
    private String title;
    private String content;
    private IconResource icon;

    public CatPost(String title, String content, String iconString) {
        this.title = title;
        this.content = content;
        this.icon = IconResource.fromString(iconString);
    }

    public CatPost(Editable title, Editable content, String iconString) {
        this(title.toString(), content.toString(), iconString);
    }

    public CatPost(String title, String content, IconResource icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
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

    public IconResource getIcon() {
        return icon;
    }

    public void setIcon(IconResource icon) {
        this.icon = icon;
    }
}
