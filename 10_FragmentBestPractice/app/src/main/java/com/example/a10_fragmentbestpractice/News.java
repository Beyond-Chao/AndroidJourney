package com.example.a10_fragmentbestpractice;

public class News {
    private String title;
    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
