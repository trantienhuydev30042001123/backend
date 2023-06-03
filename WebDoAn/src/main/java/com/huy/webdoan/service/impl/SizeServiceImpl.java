package com.huy.webdoan.service.impl;

import com.huy.webdoan.model.Size;
import com.huy.webdoan.repository.ISizeRepository;
import com.huy.webdoan.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements ISizeService {
    @Autowired
    ISizeRepository iSizeRepository;
    @Override
    public List<Size> finAll() {
        return iSizeRepository.findAll();
    }

    @Override
    public Optional<Size> findById(Long id) {
        return iSizeRepository.findById(id);
    }

    @Override
    public Boolean existsBySize(Integer size) {
        return iSizeRepository.existsBySize(size);
    }

    @Override
    public Size save(Size size) {
        return iSizeRepository.save(size);
    }

    @Override
    public void delete(Long id) {
        iSizeRepository.deleteById(id);
    }
}
