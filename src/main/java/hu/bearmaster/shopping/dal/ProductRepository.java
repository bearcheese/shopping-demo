package hu.bearmaster.shopping.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import hu.bearmaster.shopping.model.Product;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    @Transactional
    Product save(Product product);

}
