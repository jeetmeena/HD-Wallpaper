package com.example.jeetmeena.testss;

public class JsonData {
    String nameS;
    String imageurl;

    public JsonData(String nameS, String imageurl) {
        this.nameS = nameS;
        this.imageurl = imageurl;
    }

    public String getNameS() {
        return nameS;
    }

    public String getImageUrl() {
        return imageurl;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public void setImageUrl(String imageUrl) {
        this.imageurl = imageUrl;
    }
}
