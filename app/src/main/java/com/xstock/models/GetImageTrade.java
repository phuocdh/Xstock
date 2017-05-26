package com.xstock.models;

public class GetImageTrade {

    String image_data;
    String image_type;

    public GetImageTrade(String image_data, String image_type) {
        this.image_data = image_data;
        this.image_type = image_type;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getImage_data() {
        return image_data;
    }

    public void setImage_data(String image_data) {
        this.image_data = image_data;
    }
}
