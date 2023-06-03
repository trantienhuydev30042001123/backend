package com.huy.webdoan.service.impl;

import com.huy.webdoan.converter.productConverter;
import com.huy.webdoan.dto.productDTO;
import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.model.Rating;
import com.huy.webdoan.repository.CategoryRopository;
import com.huy.webdoan.repository.IProductRepository;
import com.huy.webdoan.repository.IRatingRepository;
import com.huy.webdoan.service.IProductService;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    CategoryRopository categoryRopository;
    @Autowired
    IRatingRepository iRatingRepository;
    @Autowired
    private productConverter productConverter;

    @Override
    public List<Product> finAll(String searchKey,Integer startPrice, Integer endprice) {
        if (searchKey.equals("") && startPrice.equals(0) && endprice.equals(0)){
            return iProductRepository.findAll();
        }
        else if (startPrice.equals(0) && endprice.equals(0)){
            List<Product> getlistproductN = this.iProductRepository.findByNameContainingIgnoreCase(searchKey);
            return getlistproductN;
        }
        else{
            List<Product> getlistproductP = this.iProductRepository.findByPriceBetween(startPrice,endprice);
            return getlistproductP;
        }
    }

//    @Override
//    public List<productDTO> finAll( Pageable pageable) {
//        List<productDTO> results = new ArrayList<>();
//        List<Product> entity = iProductRepository.findAll(pageable).getContent();
//       for (Product item: entity){
//           productDTO productDTO = productConverter.toDTO(item);
//           results.add(productDTO);
//       }
//        return results;
//    }

    @Override
    public int totalItem() {
        return (int) iProductRepository.count();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByCategory(Long catId) {
        Category category = this.categoryRopository.findById(catId).orElseThrow(() -> new ResourceAccessException("Access"));
        List<Product> findByCategory = this.iProductRepository.findByCategory(category);
        return findByCategory;
    }

    @Override
    public List<Product> findProductByRating(Long RatingId) {
        Rating rating = this.iRatingRepository.findById(RatingId).orElseThrow(() -> new ResourceAccessException("Access"));
        List<Product> findByRating = this.iProductRepository.findByRating(rating);
        return findByRating;
    }

//    @Override
//    public productDTO save(productDTO productDTO) {
//        Category category = categoryRopository.findOneById(productDTO.getCategory());
//        Product product = productConverter.toModel1(productDTO);
//        product.setCategory(category);
//        product = iProductRepository.save(product);
//        return productConverter.toDTO(product);
//    }

//    @Override
//    public productDTO update(productDTO productDTO) {
//        Optional<Product> oldProduct = iProductRepository.findById(productDTO.getId());
//        Product product = productConverter.toModel(productDTO,oldProduct);
//        Category category = categoryRopository.findOneById(productDTO.getCategory());
//        product.setCategory(category);
//        product = iProductRepository.save(product);
//        return productConverter.toDTO1(product);
//    }

//    @Override
//    public void delete(Long[] ids) {
//        for (long  item : ids ){
//            iProductRepository.delete(item);
//        }
//    }
}
