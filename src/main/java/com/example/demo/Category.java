package com.example.demo;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "car_id")
    private Car car;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;


    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
