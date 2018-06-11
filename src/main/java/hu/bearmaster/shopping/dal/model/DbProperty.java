package hu.bearmaster.shopping.dal.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hu.bearmaster.shopping.model.Property;

@Entity
@Table(name = "property")
public class DbProperty implements Property {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String value;

    private Long productId;

    @Override
    public Optional<Long> id() {
        return Optional.ofNullable(id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Long productId() {
        return productId;
    }
}
