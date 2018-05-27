package hu.bearmaster.shopping.model;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableProperty.class)
@JsonDeserialize(as = ImmutableProperty.class)
public interface Property {

    String getName();

    String getValue();

    static Builder builder(){
        return new Builder();
    }

    class Builder extends ImmutableProperty.Builder {
    }

}
