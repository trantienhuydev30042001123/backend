package com.huy.webdoan.repository;

import com.huy.webdoan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepository extends JpaRepository<Order, Long> {
}
