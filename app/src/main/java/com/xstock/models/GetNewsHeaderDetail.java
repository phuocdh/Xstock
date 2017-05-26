package com.xstock.models;

public class GetNewsHeaderDetail {
    String id;
    String coverimage;
    String title;
    String summary;
    String created;
    String detail;
    String author;
    int viewNumber;

    public GetNewsHeaderDetail(String id, String coverimage, String title, String summary,
                               String created, String author, String detail, int viewNumber) {
        this.id = id;
        this.coverimage = coverimage;
        this.title = title;
        this.summary = summary;
        this.created = created;
        this.author = author;
        this.detail = detail;
        this.viewNumber = viewNumber;
    }

    public GetNewsHeaderDetail() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }


}
