package com.huy.webdoan.service;

import com.huy.webdoan.model.Size;

import java.util.List;
import java.util.Optional;

public interface ISizeService {
    List<Size> finAll();
    Optional<Size> findById(Long id);
    Boolean existsBySize(Integer size);
    Size save(Size size);
    void delete(Long id);
}
