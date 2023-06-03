package com.huy.webdoan.repository;

import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Long> {
}
