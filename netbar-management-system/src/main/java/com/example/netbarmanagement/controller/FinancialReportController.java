package com.example.netbarmanagement.controller;

import com.example.netbarmanagement.model.FinancialReport;
import com.example.netbarmanagement.service.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/financial-reports")
public class FinancialReportController {

    @Autowired
    private FinancialReportService financialReportService;

    // 生成日报表
    @GetMapping("/daily")
    public FinancialReport generateDailyReport(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return financialReportService.generateDailyReport(localDate);
    }

    // 生成周报表
    @GetMapping("/weekly")
    public FinancialReport generateWeeklyReport(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return financialReportService.generateWeeklyReport(start, end);
    }

    // 生成月报表
    @GetMapping("/monthly")
    public FinancialReport generateMonthlyReport(@RequestParam int year, @RequestParam int month) {
        return financialReportService.generateMonthlyReport(year, month);
    }

    // 获取累计收益
    @GetMapping("/total-income")
    public double getTotalIncome() {
        return financialReportService.calculateTotalIncome();
    }
}