package com.xstock.models;

public class GetNewsHeader {
    String id;
    String coverimage;
    String title;
    String summary;
    String created;
    String author;
    int TotalPages;
    boolean is_publish;

    public GetNewsHeader(String id, String coverimage, String title, String summary,
                         String created, String author, int TotalPages, boolean is_publish) {
        this.id = id;
        this.coverimage = coverimage;
        this.title = title;
        this.summary = summary;
        this.created = created;
        this.author = author;
        this.TotalPages = TotalPages;
        this.is_publish = is_publish;
    }

    public GetNewsHeader() {
    }

    public String getId() {
        return id;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getCreated() {
        return created;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public boolean getPublish() {
        return is_publish;
    }

    public void setPublish(boolean is_publish) {
        this.is_publish = is_publish;
    }
}
