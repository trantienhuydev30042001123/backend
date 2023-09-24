package com.huy.webdoan.repository;

import com.huy.webdoan.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
