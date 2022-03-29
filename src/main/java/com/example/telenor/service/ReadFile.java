package com.example.telenor.service;

import com.example.telenor.model.Product;
import com.example.telenor.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class  ReadFile {

    private final ProductRepository productRepository;

    @Autowired
    public ReadFile(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }




    public void readFile() {
        try {
            Pattern pattern = Pattern.compile(",");
            Pattern pattern2 = Pattern.compile("\"\".*\"\"\"");
            File file = new File("testTelenor.csv");
            BufferedReader in = new BufferedReader(new FileReader(file));


            List<Product> products = in.lines().skip(1).map(line -> {
                String[] data = pattern.split(line.replaceAll("\"", ""));
                Matcher data2 = pattern2.matcher(line);
                String city = "";
                if (data2.find()) {
                    city = data2.group(0).replace("\"", "");
                }
                return new Product(data[0],  data[1], Double.parseDouble((data[2])), city);
            }).collect(Collectors.toList());

            productRepository.saveAll(products);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Product> findAllProducts(String type, String property, Double min_Price, Double max_Price, String store_adress, String propertyColor, Double propertyGbLimitMin, Double propertyGbLimitMax) {
        return productRepository.findAllProducts(type, property, min_Price, max_Price, store_adress, propertyColor, propertyGbLimitMin, propertyGbLimitMax);
    }


}