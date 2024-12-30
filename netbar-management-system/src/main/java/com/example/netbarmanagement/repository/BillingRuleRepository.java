package com.example.netbarmanagement.repository;

import com.example.netbarmanagement.model.BillingRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRuleRepository extends JpaRepository<BillingRule, Long> {
    List<BillingRule> findByRuleType(String ruleType);
}