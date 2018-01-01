package com.napt.studentregister.cf.model;

/**
 * Created by sreelal on 16/12/17.
 */

public class Name_Id {
    String name;
    String id;

    public Name_Id(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
