package com.xstock.models;


public class GetTradeListItem {

    String id;
    String name;
//    String company_name;

    public GetTradeListItem(String name) {
        this.name = name;
//        this.company_name = company_name;
    }

    public GetTradeListItem(String id,String name) {
        this.id = id;
        this.name = name;
//        this.company_name = company_name;
    }

    public GetTradeListItem() {

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

//    public String getCompany_name() {
//        return company_name;
//    }
//
//    public void setCompany_name(String company_name) {
//        this.company_name = company_name;
//    }
}
