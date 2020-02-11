package com.timerec.robot.entity;

import lombok.Data;

@Data
public class ContentResource {

    private String imageUrl;

    public String getImageUrl(){
        return this.imageUrl;
    }
    public String setImageUrl(String url){
        return imageUrl = url;
    }
}
