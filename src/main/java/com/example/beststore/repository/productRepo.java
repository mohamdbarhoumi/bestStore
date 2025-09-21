package com.example.beststore.repository;
import com.example.beststore.models.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepo extends JpaRepository<product,Integer> {
}
