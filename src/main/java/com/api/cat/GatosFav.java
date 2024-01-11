package com.api.cat;

public class GatosFav {
    String id;
    String image_id;
    String apikey = "live_AGzO51zAfCakiGhtvE2ogtykxEtlU2BMcweJOETXIMwa8xpiFr5bZQUBXpOT4mVU";
    Imagex image;

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

}
