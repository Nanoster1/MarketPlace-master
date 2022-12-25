package com.example.marketplace.models;

public class ItemResponse {
    private String name;

    public ItemResponse() {
    }

    public ItemResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
