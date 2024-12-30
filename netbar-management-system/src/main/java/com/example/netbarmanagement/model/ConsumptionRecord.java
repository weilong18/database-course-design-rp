package com.example.netbarmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConsumptionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // 关联的座位

    @Column(nullable = false)
    private LocalDateTime start_time; // 开始时间

    @Column(nullable = false)
    private LocalDateTime end_time; // 结束时间

    @Column(nullable = false)
    private double total_cost; // 总费用


    public Long getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Long record_id) {
        this.record_id = record_id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
}