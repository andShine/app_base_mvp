package com.tourong.app.entity;

import java.util.ArrayList;

public class ActicleEntity {
    public String name;
    public ArrayList<String> list;

    public String getName() {
        return name == null ? "" : name;
    }

    public ArrayList<String> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
