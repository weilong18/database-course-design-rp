package com.example.netbarmanagement.controller;

import com.example.netbarmanagement.model.Seat;
import com.example.netbarmanagement.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    // 获取所有座位
    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    // 上机
    @PostMapping("/{id}/start")
    public Seat startUsingSeat(@PathVariable Long id) {
        return seatService.startUsingSeat(id);
    }

    // 下机
    @PostMapping("/{id}/stop")
    public Seat stopUsingSeat(@PathVariable Long id) {
        return seatService.stopUsingSeat(id);
    }

    // 计算费用
    @GetMapping("/{id}/cost")
    public double calculateCost(@PathVariable Long id) {
        return seatService.calculateCost(id);
    }
}