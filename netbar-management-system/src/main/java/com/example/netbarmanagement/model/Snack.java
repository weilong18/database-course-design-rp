package com.example.netbarmanagement.model;

import jakarta.persistence.*;

@Entity
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snack_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double purchase_price;

    @Column(nullable = false)
    private int stock;


    public Long getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(Long snack_id) {
        this.snack_id = snack_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
