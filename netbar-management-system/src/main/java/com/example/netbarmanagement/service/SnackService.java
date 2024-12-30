package com.example.netbarmanagement.service;

import com.example.netbarmanagement.model.Snack;
import com.example.netbarmanagement.model.SnackConsumption;
import com.example.netbarmanagement.model.Seat;
import com.example.netbarmanagement.repository.SnackConsumptionRepository;
import com.example.netbarmanagement.repository.SnackRepository;
import com.example.netbarmanagement.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SnackService {

    @Autowired
    private SnackRepository snackRepository;

    @Autowired
    private SnackConsumptionRepository snackConsumptionRepository;

    @Autowired
    private SeatRepository seatRepository;

    // 获取所有商品
    public List<Snack> getAllSnacks() {
        return snackRepository.findAll();
    }

    // 添加商品
    public Snack addSnack(Snack snack) {
        return snackRepository.save(snack);
    }

    // 修改商品
    public Snack updateSnack(Long id, Snack snack) {
        Snack existingSnack = snackRepository.findById(id).orElseThrow(() -> new RuntimeException("商品未找到"));
        existingSnack.setName(snack.getName());
        existingSnack.setPrice(snack.getPrice());
        existingSnack.setPurchase_price(snack.getPurchase_price());
        existingSnack.setStock(snack.getStock());
        return snackRepository.save(existingSnack);
    }

    @Transactional
    public Snack sellSnack(Long id, int quantity, Long seatId) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("数量必须大于0");
        }

        Snack snack = snackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品未找到"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("座位未找到"));

        System.out.println("售出前库存: " + snack.getStock());

        if (snack.getStock() < quantity) {
            throw new RuntimeException("库存不足");
        }

        // 更新库存
        snack.setStock(snack.getStock() - quantity);
        Snack updatedSnack = snackRepository.save(snack);

        System.out.println("售出后库存: " + updatedSnack.getStock());

        // 记录销售数据
        SnackConsumption consumption = new SnackConsumption();
        consumption.setSnack(updatedSnack);
        consumption.setSeat(seat);
        consumption.setQuantity(quantity);
        consumption.setTotal_cost(updatedSnack.getPrice() * quantity);
        consumption.setConsumption_time(LocalDateTime.now());
        snackConsumptionRepository.save(consumption);

        return updatedSnack;
    }
}