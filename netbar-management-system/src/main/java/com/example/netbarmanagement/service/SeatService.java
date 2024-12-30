package com.example.netbarmanagement.service;

import com.example.netbarmanagement.model.Seat;
import com.example.netbarmanagement.model.ConsumptionRecord;
import com.example.netbarmanagement.repository.ConsumptionRecordRepository;
import com.example.netbarmanagement.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Autowired
    private BillingService billingService;

    // 获取所有座位
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    // 上机
    public Seat startUsingSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("座位未找到"));
        seat.setStatus("使用中");
        seat.setStart_time(LocalDateTime.now());
        seat.setEnd_time(null);
        return seatRepository.save(seat);
    }

    // 下机
    public Seat stopUsingSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("座位未找到"));
        seat.setStatus("空闲");
        seat.setEnd_time(LocalDateTime.now());

        // 记录消费数据
        ConsumptionRecord record = new ConsumptionRecord();
        record.setSeat(seat);
        record.setStart_time(seat.getStart_time());
        record.setEnd_time(seat.getEnd_time());
        record.setTotal_cost(billingService.calculateCost(seat, seat.getStart_time(), seat.getEnd_time(), "hourly")); // 使用 BillingService 计算费用
        consumptionRecordRepository.save(record);

        return seatRepository.save(seat);
    }

    // 计算费用（已弃用，直接使用 BillingService）
    @Deprecated
    public double calculateCost(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("座位未找到"));
        if (seat.getStart_time() == null || seat.getEnd_time() == null) {
            throw new RuntimeException("座位未正确使用");
        }

        Duration duration = Duration.between(seat.getStart_time(), seat.getEnd_time());
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) {
            hours++; // 不足一小时按一小时计算
        }

        return hours * 10.0;
    }
}