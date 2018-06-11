package hu.bearmaster.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.bearmaster.shopping.dal.service.ProductService;
import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id).orElse(null);
    }

    @PostMapping("products")
    public Long newProduct(@RequestBody Product product) {
        return productService.save(product).getId().get();
    }

//    @GetMapping("manufacturer/{id}")
//    public Manufacturer getManufacturer(@PathVariable Long id) {
//        return manufacturerRepository.getOne(id);
//    }

}
