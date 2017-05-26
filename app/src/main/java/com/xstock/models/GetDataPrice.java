package com.xstock.models;

public class GetDataPrice {

    String trade_name;
    String pclose;
    String volume;
    int type;
    String point;
    String updown;

    public GetDataPrice(String trade_name, String pclose, String point, String updown, String volume, int type) {
        this.trade_name = trade_name;
        this.pclose = pclose;
        this.volume = volume;
        this.type = type;
        this.point = point;
        this.updown = updown;
    }

    public GetDataPrice() {

    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getPclose() {
        return pclose;
    }

    public void setPclose(String pclose) {
        this.pclose = pclose;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getUpdown() {
        return updown;
    }

    public void setUpdown(String updown) {
        this.updown = updown;
    }
}
