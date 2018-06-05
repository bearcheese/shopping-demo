package hu.bearmaster.shopping.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.bearmaster.shopping.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
