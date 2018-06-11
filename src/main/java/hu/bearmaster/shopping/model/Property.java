package hu.bearmaster.shopping.model;

import java.util.Optional;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableProperty.class)
@JsonDeserialize(as = ImmutableProperty.class)
public interface Property {

    Optional<Long> id();

    String getName();

    String getValue();

    Long productId();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableProperty.Builder {
    }

}
