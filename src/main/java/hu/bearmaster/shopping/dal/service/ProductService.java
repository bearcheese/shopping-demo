package hu.bearmaster.shopping.dal.service;

import java.util.Optional;

import hu.bearmaster.shopping.dal.ProductRepository;
import hu.bearmaster.shopping.model.Product;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(daoProduct -> Product.builder().from(daoProduct).build());
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
