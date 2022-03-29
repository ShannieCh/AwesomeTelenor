package com.example.telenor.controller;

import com.example.telenor.model.Product;
import com.example.telenor.service.ReadFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ReadFile readFile;

    public ProductController(ReadFile readFile) {
        this.readFile = readFile;
    }

    @GetMapping("/product")
    @ResponseBody
    public List<Product> getAllProducts(@RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "property", required = false) String properties,
                                        @RequestParam(value = "min_price", required = false) Double min_Price,
                                        @RequestParam(value = "max_price", required = false) Double max_Price,
                                        @RequestParam(value = "store_adress", required = false) String store_adress,
                                        @RequestParam(value = "property:color", required = false) String propertyColor,
                                        @RequestParam(value = "property:gb_limit_min", required = false) Double propertyGbLimitMin,
                                        @RequestParam(value = "property:gb_limit_max", required = false) Double propertyGbLimitMax) {

        return readFile.findAllProducts(type, properties, min_Price, max_Price, store_adress, propertyColor, propertyGbLimitMin, propertyGbLimitMax);
    }

}