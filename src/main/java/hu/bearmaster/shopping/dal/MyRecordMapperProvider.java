package hu.bearmaster.shopping.dal;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bearmaster.shopping.dal.jooq.Shoppingdemo;
import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.ModifiableManufacturer;
import hu.bearmaster.shopping.model.ModifiableProperty;
import hu.bearmaster.shopping.model.Product;
import hu.bearmaster.shopping.model.Property;

public class MyRecordMapperProvider implements RecordMapperProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRecordMapperProvider.class);

    private static final Map<Class<?>, RecordMapper<?, ?>> MAPPERS;

    static {
        MAPPERS = Collections.unmodifiableMap(
                Map.of(Manufacturer.class, MyRecordMapperProvider::mapManufacturerRecord,
                        Product.class, MyRecordMapperProvider::mapProductRecord,
                        Property.class, MyRecordMapperProvider::mapPropertyRecord));
    }

    private static Manufacturer mapManufacturerRecord(Record record) {
        return Manufacturer.builder()
                .from(record.into(ModifiableManufacturer.class))
                .build();
    }

    private static Product mapProductRecord(Record record) {
        Manufacturer manufacturer = record.into(Manufacturer.class);
        return Product.builder()
                .id(record.get(Shoppingdemo.SHOPPINGDEMO.PRODUCT.ID))
                .itemId(UUID.fromString(record.get(Shoppingdemo.SHOPPINGDEMO.PRODUCT.ITEMID)))
                .category(record.get(Shoppingdemo.SHOPPINGDEMO.PRODUCT.CATEGORY))
                .name(record.get(Shoppingdemo.SHOPPINGDEMO.PRODUCT.NAME))
                .price(record.get(Shoppingdemo.SHOPPINGDEMO.PRODUCT.PRICE))
                .manufacturer(manufacturer)
                .build();
    }

    private static Property mapPropertyRecord(Record record) {
        return Property.builder()
                .from(record.into(ModifiableProperty.class))
                .build();
    }

    @Override
    public final <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> rowType, Class<? extends E> type) {
        LOGGER.info("Providing mapper for row type {} and class {}", rowType.getClass().getSimpleName(), type.getSimpleName());
        return (RecordMapper<R, E>) MAPPERS.getOrDefault(type, new DefaultRecordMapper<>(rowType, type));
    }
}
