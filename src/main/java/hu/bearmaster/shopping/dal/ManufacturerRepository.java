package hu.bearmaster.shopping.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.bearmaster.shopping.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
