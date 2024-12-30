package com.example.netbarmanagement.controller;

import com.example.netbarmanagement.model.Snack;
import com.example.netbarmanagement.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/snacks")
public class SnackController {

    @Autowired
    private SnackService snackService;

    // 获取所有商品
    @GetMapping
    public List<Snack> getAllSnacks() {
        return snackService.getAllSnacks();
    }

    // 添加商品
    @PostMapping
    public Snack addSnack(@RequestBody Snack snack) {
        return snackService.addSnack(snack);
    }

    // 修改商品
    @PutMapping("/{id}")
    public Snack updateSnack(@PathVariable Long id, @RequestBody Snack snack) {
        return snackService.updateSnack(id, snack);
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<?> sellSnack(@PathVariable Long id, @RequestParam int quantity, @RequestParam Long seatId) {
        try {
            Snack snack = snackService.sellSnack(id, quantity, seatId);
            return ResponseEntity.ok(snack);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("内部服务器错误");
        }
    }
}