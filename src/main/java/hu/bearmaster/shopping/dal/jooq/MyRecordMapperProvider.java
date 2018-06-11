package hu.bearmaster.shopping.dal.jooq;

import static hu.bearmaster.shopping.dal.jooq.Shoppingdemo.SHOPPINGDEMO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;
import hu.bearmaster.shopping.model.Property;

public class MyRecordMapperProvider implements RecordMapperProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRecordMapperProvider.class);

    private static final Map<Class<?>, RecordMapper<?, ?>> MAPPERS;

    static {
        Map<Class<?>, RecordMapper<?, ?>> mapper = new HashMap<>();
        mapper.put(Manufacturer.class, MyRecordMapperProvider::mapManufacturerRecord);
        mapper.put(Product.class, MyRecordMapperProvider::mapProductRecord);
        mapper.put(Property.class, MyRecordMapperProvider::mapPropertyRecord);
        MAPPERS = Collections.unmodifiableMap(mapper);
    }

    private static Manufacturer mapManufacturerRecord(Record record) {
        return Manufacturer.builder()
                .id(record.get(SHOPPINGDEMO.MANUFACTURER.ID))
                .name(record.get(SHOPPINGDEMO.MANUFACTURER.NAME))
                .headOffice(record.get(SHOPPINGDEMO.MANUFACTURER.HEAD_OFFICE))
                .build();
    }

    private static Product mapProductRecord(Record record) {
        Manufacturer manufacturer = record.into(Manufacturer.class);
        return Product.builder()
                .id(record.get(SHOPPINGDEMO.PRODUCT.ID))
                .itemId(record.get(SHOPPINGDEMO.PRODUCT.ITEMID))
                .category(record.get(SHOPPINGDEMO.PRODUCT.CATEGORY))
                .name(record.get(SHOPPINGDEMO.PRODUCT.NAME))
                .price(record.get(SHOPPINGDEMO.PRODUCT.PRICE))
                .manufacturer(manufacturer)
                .build();
    }

    private static Property mapPropertyRecord(Record record) {
        return Property.builder()
                .id(record.get(SHOPPINGDEMO.PROPERTY.ID))
                .name(record.get(SHOPPINGDEMO.PROPERTY.NAME))
                .value(record.get(SHOPPINGDEMO.PROPERTY.VALUE))
                .productId(record.get(SHOPPINGDEMO.PROPERTY.PRODUCT_ID))
                .build();
    }

    @Override
    public final <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> rowType, Class<? extends E> type) {
        LOGGER.info("Providing mapper for row type {} and class {}", rowType.getClass().getSimpleName(), type.getSimpleName());
        return (RecordMapper<R, E>) MAPPERS.getOrDefault(type, new DefaultRecordMapper<>(rowType, type));
    }
}
