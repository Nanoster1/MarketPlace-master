package com.example.marketplace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item{
    public Item(){

    }

    public Item(String name){
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String name;
    private Boolean isSale = false;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer id) {
        this.itemId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSale() {
        return isSale;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
    }
}
