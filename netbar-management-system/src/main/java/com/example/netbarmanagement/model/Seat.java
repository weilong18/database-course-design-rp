package com.example.netbarmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seat_id;

    @Column(nullable = false)
    private String status;

    private LocalDateTime start_time; // 开始时间
    private LocalDateTime end_time;   // 结束时间


    public Long getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Long seat_id) {
        this.seat_id = seat_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}