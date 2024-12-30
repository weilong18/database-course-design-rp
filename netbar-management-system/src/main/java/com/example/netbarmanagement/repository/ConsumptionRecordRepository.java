package com.example.netbarmanagement.repository;

import com.example.netbarmanagement.model.ConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, Long> {
    // 计算所有座位消费的总收入
    @Query("SELECT SUM(c.total_cost) FROM ConsumptionRecord c")
    Double sumTotalCost();

    // 按日期计算座位消费的总收入
    @Query("SELECT SUM(c.total_cost) FROM ConsumptionRecord c WHERE DATE(c.end_time) = :date")
    Double sumTotalCostByDate(LocalDate date);

    // 按日期范围计算座位消费的总收入
    @Query("SELECT SUM(c.total_cost) FROM ConsumptionRecord c WHERE DATE(c.end_time) BETWEEN :startDate AND :endDate")
    Double sumTotalCostBetweenDates(LocalDate startDate, LocalDate endDate);
}