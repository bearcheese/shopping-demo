package hu.bearmaster.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("products")
    public Long newProduct(@RequestBody Product product) {
        return productRepository.save(product).getId().get();
    }

    @GetMapping("manufacturer/{id}")
    public Manufacturer getManufacturer(@PathVariable Long id) {
        return manufacturerRepository.getOne(id);
    }

}
