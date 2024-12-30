package com.example.netbarmanagement.repository;

import com.example.netbarmanagement.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}