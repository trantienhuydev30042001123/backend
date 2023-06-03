package com.huy.webdoan.service;

import com.huy.webdoan.dto.productDTO;
import com.huy.webdoan.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> finAll(String searchKey,Integer startPrice, Integer EndPrice);
//    List<productDTO> finAll(Pageable pageable);
    int totalItem();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
    List<Product> findProductByCategory( Long catId);
    List<Product> findProductByRating( Long ratingId);
//    productDTO save(productDTO productDTO);
//    productDTO update(productDTO productDTO);
//    void delete(Long[] ids);

}
