package com.api.cat;

public class Gatos {
    String id;
    String url;
    String apíKey = "live_AGzO51zAfCakiGhtvE2ogtykxEtlU2BMcweJOETXIMwa8xpiFr5bZQUBXpOT4mVU";
    String imagen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApíKey() {
        return apíKey;
    }

    public void setApíKey(String apíKey) {
        this.apíKey = apíKey;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
