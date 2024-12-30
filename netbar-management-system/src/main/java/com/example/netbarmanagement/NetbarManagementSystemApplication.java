package com.example.netbarmanagement;

import com.example.netbarmanagement.model.BillingRule;
import com.example.netbarmanagement.repository.BillingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class NetbarManagementSystemApplication {

	@Autowired
	private BillingRuleRepository billingRuleRepository;

	public static void main(String[] args) {
		SpringApplication.run(NetbarManagementSystemApplication.class, args);
	}

	@PostConstruct
	public void init() {
		List<BillingRule> rules = billingRuleRepository.findByRuleType("hourly");
		if (rules.isEmpty()) {
			BillingRule defaultRule = new BillingRule();
			defaultRule.setRuleType("hourly");
			defaultRule.setRate(10.0); // 每小时 10元
			billingRuleRepository.save(defaultRule);
		} else if (rules.size() > 1) {
			for (int i = 1; i < rules.size(); i++) {
				billingRuleRepository.delete(rules.get(i));
			}
		}
	}
}