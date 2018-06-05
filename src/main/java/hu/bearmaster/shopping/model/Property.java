package hu.bearmaster.shopping.model;

import java.util.Optional;

import javax.persistence.Table;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bearmaster.shopping.model.annotation.InjectEntity;
import hu.bearmaster.shopping.model.annotation.InjectGeneratedValue;
import hu.bearmaster.shopping.model.annotation.InjectId;

@InjectEntity
@Table(name = "property")
@Value.Immutable
@JsonSerialize(as = ImmutableProperty.class)
@JsonDeserialize(as = ImmutableProperty.class)
public interface Property {

    @InjectId
    @InjectGeneratedValue
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
