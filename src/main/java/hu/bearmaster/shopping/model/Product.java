package hu.bearmaster.shopping.model;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Table;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bearmaster.shopping.model.annotation.InjectColumn;
import hu.bearmaster.shopping.model.annotation.InjectEntity;
import hu.bearmaster.shopping.model.annotation.InjectGeneratedValue;
import hu.bearmaster.shopping.model.annotation.InjectId;
import hu.bearmaster.shopping.model.annotation.InjectJoinColumn;
import hu.bearmaster.shopping.model.annotation.InjectManyToOne;
import hu.bearmaster.shopping.model.annotation.InjectOneToMany;

@InjectEntity
@Table(name = "product")
@Value.Immutable
@JsonSerialize(as = ImmutableProduct.class)
@JsonDeserialize(as = ImmutableProduct.class)
public interface Product {

    @InjectId
    @InjectGeneratedValue
    Optional<Long> getId();

    @InjectColumn(name = "itemid")
    UUID getItemId();

    String getName();

    String getCategory();

    BigDecimal getPrice();

    @InjectManyToOne(targetEntity = ImmutableManufacturer.class)
    @InjectJoinColumn(name = "manufacturer_id", nullable = false)
    Manufacturer getManufacturer();

    @InjectOneToMany(mappedBy = "productId", targetEntity = ImmutableProperty.class)
    Set<Property> getProperties();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableProduct.Builder {
    }

}
