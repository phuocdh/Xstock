package com.xstock.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GetTradeList extends RealmObject {

    @PrimaryKey
    String id;
    String name;
    String company_name;
    String parent_icb;
    String parent_tt;
    String country;
    String market;
    String type;
    String group1;
    String level1;
    String level2;
    String level3;
    String level4;
    String active;
    String pic_name1;
    String pic_name2;
    String created;
    String modified;

    public GetTradeList(String id, String name, String company_name, String parent_icb, String parent_tt, String country, String market, String type, String group1, String level1, String level2, String level3, String level4, String active, String pic_name1, String pic_name2, String created, String modified) {
        this.id = id;
        this.name = name;
        this.company_name = company_name;
        this.parent_icb = parent_icb;
        this.parent_tt = parent_tt;
        this.country = country;
        this.market = market;
        this.type = type;
        this.group1 = group1;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.active = active;
        this.pic_name1 = pic_name1;
        this.pic_name2 = pic_name2;
        this.created = created;
        this.modified = modified;
    }

    public GetTradeList() {

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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getParent_icb() {
        return parent_icb;
    }

    public void setParent_icb(String parent_icb) {
        this.parent_icb = parent_icb;
    }

    public String getParent_tt() {
        return parent_tt;
    }

    public void setParent_tt(String parent_tt) {
        this.parent_tt = parent_tt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPic_name1() {
        return pic_name1;
    }

    public void setPic_name1(String pic_name1) {
        this.pic_name1 = pic_name1;
    }

    public String getPic_name2() {
        return pic_name2;
    }

    public void setPic_name2(String pic_name2) {
        this.pic_name2 = pic_name2;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
