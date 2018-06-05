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
@Table(name = "manufacturer")
@Value.Immutable
@JsonSerialize(as = ImmutableManufacturer.class)
@JsonDeserialize(as = ImmutableManufacturer.class)
public interface Manufacturer {

    @InjectId
    @InjectGeneratedValue
    Optional<Long> getId();

    String getName();

    String getHeadOffice();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableManufacturer.Builder {
    }
}
