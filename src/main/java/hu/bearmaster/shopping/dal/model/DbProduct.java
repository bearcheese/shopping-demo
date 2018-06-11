package hu.bearmaster.shopping.dal.model;

import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;
import hu.bearmaster.shopping.model.Property;

@Entity
@Table(name = "product")
public class DbProduct implements Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "itemid")
    private UUID itemId;

    private String name;
    private String category;
    private BigDecimal price;

    @ManyToOne(targetEntity = DbManufacturer.class)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "productId", targetEntity = DbProperty.class)
    private Set<Property> properties;

    @Override
    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public UUID getItemId() {
        return itemId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.builder()
            .from(manufacturer)
            .build();
    }

    @Override
    public Set<Property> getProperties() {
        return Collections.unmodifiableSet(properties.stream()
            .map(dbProperty -> Property.builder()
                .from(dbProperty)
                .build())
            .collect(toSet()));
    }
}
