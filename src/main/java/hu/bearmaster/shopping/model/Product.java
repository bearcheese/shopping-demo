package hu.bearmaster.shopping.model;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableProduct.class)
@JsonDeserialize(as = ImmutableProduct.class)
public interface Product {

    Optional<Long> getId();

    UUID getItemId();

    String getName();

    String getCategory();

    BigDecimal getPrice();

    Manufacturer getManufacturer();

    Set<Property> getProperties();

    static Builder builder(){
        return new Builder();
    }

    class Builder extends ImmutableProduct.Builder {
    }

}
