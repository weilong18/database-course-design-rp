package com.example.netbarmanagement.service;

import com.example.netbarmanagement.model.BillingRule;
import com.example.netbarmanagement.model.ConsumptionRecord;
import com.example.netbarmanagement.model.Seat;
import com.example.netbarmanagement.repository.BillingRuleRepository;
import com.example.netbarmanagement.repository.ConsumptionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRuleRepository billingRuleRepository;

    @Autowired
    private ConsumptionRecordRepository consumptionRecordRepository;

    // 计算费用
    public double calculateCost(Seat seat, LocalDateTime startTime, LocalDateTime endTime, String ruleType) {
        // 查询所有 ruleType 为指定类型的规则
        List<BillingRule> rules = billingRuleRepository.findByRuleType(ruleType);
        if (rules.isEmpty()) {
            throw new RuntimeException("未找到计费规则: " + ruleType);
        } else if (rules.size() > 1) {
            throw new RuntimeException("找到多个计费规则: " + ruleType);
        }

        // 获取第一条规则
        BillingRule rule = rules.get(0);

        // 计算费用
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) {
            hours++; // 不足一小时按一小时计算
        }

        return hours * rule.getRate(); // 使用规则中的费率
    }

    // 记录消费
    public ConsumptionRecord recordConsumption(Seat seat, LocalDateTime startTime, LocalDateTime endTime, String ruleType) {
        double totalCost = calculateCost(seat, startTime, endTime, ruleType);

        ConsumptionRecord record = new ConsumptionRecord();
        record.setSeat(seat);
        record.setStart_time(startTime);
        record.setEnd_time(endTime);
        record.setTotal_cost(totalCost);

        return consumptionRecordRepository.save(record);
    }
}