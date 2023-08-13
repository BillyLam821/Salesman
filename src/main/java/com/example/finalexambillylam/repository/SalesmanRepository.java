package com.example.finalexambillylam.repository;

import com.example.finalexambillylam.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
//    @Query("SELECT s.name, s.item, SUM(s.amount) FROM Salesman s GROUP BY s.name, s.item")
    @Query("SELECT s.name, SUM(CASE WHEN s.item = 'Washing Machine' THEN s.amount ELSE 0 END), " +
            "SUM(CASE WHEN s.item = 'Refrigerator' THEN s.amount ELSE 0 END), " +
            "SUM(CASE WHEN s.item = 'Music System' THEN s.amount ELSE 0 END) " +
            "FROM Salesman s GROUP BY s.name")
//    Washing Machine | Refrigerator | Music System|
    List<Object[]> sumAmountBySalesmanAndItem();
}
