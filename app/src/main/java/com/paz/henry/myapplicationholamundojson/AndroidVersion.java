package com.paz.henry.myapplicationholamundojson;

import java.io.Serializable;

public class AndroidVersion implements Serializable {
    private String ver;
    private String name;
    private String api;


    @Override
    public String toString() {
        return "AndroidVersion P{" +
                "ver='" + ver + '\'' +
                ", name='" + name + '\'' +
                ", api='" + api + '\'' +
                '}';
    }

    public AndroidVersion(String ver, String name, String api) {
        this.ver = ver;
        this.name = name;
        this.api = api;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
