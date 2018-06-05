package hu.bearmaster.shopping.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hu.bearmaster.shopping.dal.ManufacturerRepository;
import hu.bearmaster.shopping.dal.ProductRepository;
import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;

@RestController
public class ProductController {

    private final ManufacturerRepository manufacturerRepository;

    private final ProductRepository productRepository;

    public ProductController(ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("products")
    public List<? extends Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("manufacturers")
    public List<? extends Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("manufacturers/{id}")
    public Manufacturer getManufacturer(@PathVariable Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

}
