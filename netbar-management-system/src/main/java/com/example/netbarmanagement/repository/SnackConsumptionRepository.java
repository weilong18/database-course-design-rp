package com.example.netbarmanagement.repository;

import com.example.netbarmanagement.model.SnackConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;


public interface SnackConsumptionRepository extends JpaRepository<SnackConsumption, Long> {

    @Query("SELECT SUM(s.total_cost) FROM SnackConsumption s")
    Double sumTotalCost();

    @Query("SELECT SUM(s.total_cost) FROM SnackConsumption s WHERE DATE(s.consumption_time) = :date")
    Double sumTotalCostByDate(LocalDate date);

    @Query("SELECT SUM(s.total_cost) FROM SnackConsumption s WHERE DATE(s.consumption_time) BETWEEN :startDate AND :endDate")
    Double sumTotalCostBetweenDates(LocalDate startDate, LocalDate endDate);
}