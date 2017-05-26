package com.xstock.models;


public class GetDetailMessage {

    String id;
    String name;
    String date;
    int totalPages;

    public GetDetailMessage(String id, String name, String date, int totalPages) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.totalPages = totalPages;
    }

    public GetDetailMessage() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
