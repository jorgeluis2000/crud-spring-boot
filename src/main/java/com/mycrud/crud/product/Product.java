package com.mycrud.crud.product;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private float price;
    private LocalDate myDate;
    @Transient
    private int antiquity;

    public Product() {
    }

    public Product(Long id, String name, float price, LocalDate myDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.myDate = myDate;
    }

    public Product(String name, float price, LocalDate myDate) {
        this.name = name;
        this.price = price;
        this.myDate = myDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getMyDate() {
        return this.myDate;
    }

    public void setMyDate(LocalDate myDate) {
        this.myDate = myDate;
    }

    public int getAntiquity() {
        return Period.between(this.myDate, LocalDate.now()).getYears();
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

}
