package com.example.telenor.repository;

import com.example.telenor.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("""
       select distinct p from Product p where
       (?1 is null or p.type = ?1)
       and (?2 is null or p.properties like %?2%)
       and (?3 is null or p.price > ?3 and ?4 is null or p.price <?4)
       and (?5 is null or p.city like %?5%)
       and (?6 is null or p.properties like %?6%)
       """)
    List<Product> findAllProducts(String type, String properties, Double minPrice, Double maxPrice, String city, String propertyColor, Double propertyGbLimitMin, Double propertyGbLimitMax);
}
