package com.xstock.models;

public class GetDataNganh {

    String index_display;
    String pclose;
    String volume;
    int type;
    String updown_percent;

    public GetDataNganh(String index_display,String pclose,String updown_percent, String volume,
                        int type) {
        this.pclose = pclose;
        this.index_display = index_display;
        this.updown_percent = updown_percent;
        this.volume = volume;
        this.type = type;
    }

    public GetDataNganh() {
    }


    public String getIndex_display() {
        return index_display;
    }

    public void setIndex_display(String index_display) {
        this.index_display = index_display;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPclose() {
        return pclose;
    }

    public void setPclose(String pclose) {
        this.pclose = pclose;
    }

    public String getUpdown_percent() {
        return updown_percent;
    }

    public void setUpdown_percent(String updown_percent) {
        this.updown_percent = updown_percent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
