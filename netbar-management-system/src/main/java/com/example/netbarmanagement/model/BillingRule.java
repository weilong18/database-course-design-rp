package com.example.netbarmanagement.model;

import jakarta.persistence.*;

@Entity
public class BillingRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rule_id;

    @Column(nullable = false, unique = true) // 确保 ruleType 唯一
    private String ruleType; // 计费规则类型

    @Column(nullable = false)
    private double rate; // 费率


    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}