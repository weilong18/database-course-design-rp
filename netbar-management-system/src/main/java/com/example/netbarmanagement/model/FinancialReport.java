package com.example.netbarmanagement.model;

import java.time.LocalDate;

public class FinancialReport {
    private LocalDate reportDate; // 报告日期
    private double totalIncome;   // 总收入
    private double seatIncome;    // 座位收入
    private double snackIncome;   // 小卖部收入


    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getSeatIncome() {
        return seatIncome;
    }

    public void setSeatIncome(double seatIncome) {
        this.seatIncome = seatIncome;
    }

    public double getSnackIncome() {
        return snackIncome;
    }

    public void setSnackIncome(double snackIncome) {
        this.snackIncome = snackIncome;
    }
}