package com.example.netbarmanagement.service;

import com.example.netbarmanagement.model.FinancialReport;
import com.example.netbarmanagement.repository.ConsumptionRecordRepository;
import com.example.netbarmanagement.repository.SnackConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialReportService {

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    @Autowired
    private SnackConsumptionRepository snackConsumptionRepository;

    // 生成日报表
    public FinancialReport generateDailyReport(LocalDate date) {
        FinancialReport report = new FinancialReport();
        report.setReportDate(date);

        // 计算座位收入
        Double seatIncome = consumptionRecordRepository.sumTotalCostByDate(date);
        report.setSeatIncome(seatIncome != null ? seatIncome : 0.0);

        // 计算小卖部收入
        Double snackIncome = snackConsumptionRepository.sumTotalCostByDate(date);
        report.setSnackIncome(snackIncome != null ? snackIncome : 0.0);

        // 计算总收入
        report.setTotalIncome(report.getSeatIncome() + report.getSnackIncome());

        return report;
    }

    // 生成周报表
    public FinancialReport generateWeeklyReport(LocalDate startDate, LocalDate endDate) {
        FinancialReport report = new FinancialReport();
        report.setReportDate(endDate);

        // 计算座位收入
        Double seatIncome = consumptionRecordRepository.sumTotalCostBetweenDates(startDate, endDate);
        report.setSeatIncome(seatIncome != null ? seatIncome : 0.0);

        // 计算小卖部收入
        Double snackIncome = snackConsumptionRepository.sumTotalCostBetweenDates(startDate, endDate);
        report.setSnackIncome(snackIncome != null ? snackIncome : 0.0);

        // 计算总收入
        report.setTotalIncome(report.getSeatIncome() + report.getSnackIncome());

        return report;
    }

    // 生成月报表
    public FinancialReport generateMonthlyReport(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return generateWeeklyReport(startDate, endDate);
    }
    public double calculateTotalIncome() {
        // 计算座位收入
        Double seatIncome = consumptionRecordRepository.sumTotalCost();
        if (seatIncome == null) {
            seatIncome = 0.0;
        }

        // 计算小卖部收入
        Double snackIncome = snackConsumptionRepository.sumTotalCost();
        if (snackIncome == null) {
            snackIncome = 0.0;
        }

        // 返回总收入
        return seatIncome + snackIncome;
    }
}