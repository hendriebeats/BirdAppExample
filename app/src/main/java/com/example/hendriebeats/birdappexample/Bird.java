package com.example.hendriebeats.birdappexample;

/**
 * Created by Hendrie Beats on 2/21/2017.
 */

public class Bird {

    private int id;
    private String name;
    private String description;

    public Bird(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Bird(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bird() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
