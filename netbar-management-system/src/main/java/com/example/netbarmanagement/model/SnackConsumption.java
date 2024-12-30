package com.example.netbarmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SnackConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumption_id;

    @ManyToOne
    @JoinColumn(name = "snack_id", nullable = false)
    private Snack snack; // 关联的商品

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // 关联的座位

    @Column(nullable = false)
    private int quantity; // 消费数量

    @Column(nullable = false)
    private double total_cost; // 总消费金额

    @Column(nullable = false)
    private LocalDateTime consumption_time; // 消费时间


    public Long getConsumption_id() {
        return consumption_id;
    }

    public void setConsumption_id(Long consumption_id) {
        this.consumption_id = consumption_id;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public LocalDateTime getConsumption_time() {
        return consumption_time;
    }

    public void setConsumption_time(LocalDateTime consumption_time) {
        this.consumption_time = consumption_time;
    }
}