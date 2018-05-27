package hu.bearmaster.shopping.model;

import java.util.Optional;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableManufacturer.class)
@JsonDeserialize(as = ImmutableManufacturer.class)
public interface Manufacturer {

    Optional<Long> getId();

    String getName();

    String getHeadOffice();

    static Builder builder(){
        return new Builder();
    }

    class Builder extends ImmutableManufacturer.Builder {
    }

}
