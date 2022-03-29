package com.example.telenor.service;

import com.example.telenor.model.Product;
import com.example.telenor.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class ReadFileTest {


    @InjectMocks
    private ReadFile readFile;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private List<Product> productList;


    @BeforeEach
    void setup() {
        product = new Product();
        productList = new ArrayList<>();

        product.setPrice(200.0);
        product.setCity("Lännagatan 10, Stockholm");
        product.setType("phone");
        product.setProperties("color:grön");
        productList.add(product);

        productRepository.saveAll(productList);
    }

    @Test
    void readFile() {
        lenient().when(productRepository.saveAll(productList)).thenReturn(productList);
        //when
        readFile.readFile();
        //then
        verify(productRepository, times(1)).saveAll(productList);
    }

}