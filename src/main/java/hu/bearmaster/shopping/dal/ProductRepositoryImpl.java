package hu.bearmaster.shopping.dal;

import static hu.bearmaster.shopping.dal.jooq.Shoppingdemo.SHOPPINGDEMO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jooq.DSLContext;
import org.jooq.InsertValuesStep3;
import org.jooq.Result;

import hu.bearmaster.shopping.dal.jooq.tables.records.ManufacturerRecord;
import hu.bearmaster.shopping.dal.jooq.tables.records.ProductRecord;
import hu.bearmaster.shopping.dal.jooq.tables.records.PropertyRecord;
import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;
import hu.bearmaster.shopping.model.Property;

class ProductRepositoryImpl implements ProductRepository {

    private final DSLContext context;

    public ProductRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        Optional<Product> product = context.select()
                .from(SHOPPINGDEMO.PRODUCT)
                .join(SHOPPINGDEMO.MANUFACTURER).on(SHOPPINGDEMO.PRODUCT.MANUFACTURER_ID.eq(SHOPPINGDEMO.MANUFACTURER.ID))
                .where(SHOPPINGDEMO.PRODUCT.ID.eq(productId))
                .fetchOptional(record -> record.into(Product.class));

        if (product.isPresent()) {
            List<Property> properties = context.select()
                    .from(SHOPPINGDEMO.PROPERTY)
                    .where(SHOPPINGDEMO.PROPERTY.PRODUCT_ID.eq(productId))
                    .fetchInto(Property.class);
            Product productWithProperties = Product.builder().from(product.get()).properties(properties).build();
            return Optional.of(productWithProperties);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        Long manufacturerId;
        if (product.getManufacturer().getId().isPresent()) {
            manufacturerId = product.getManufacturer().getId().get();
        } else {
            ManufacturerRecord manufacturerRecord = context.insertInto(SHOPPINGDEMO.MANUFACTURER,
                    SHOPPINGDEMO.MANUFACTURER.NAME, SHOPPINGDEMO.MANUFACTURER.HEAD_OFFICE)
                    .values(product.getManufacturer().getName(), product.getManufacturer().getHeadOffice())
                    .returning(SHOPPINGDEMO.MANUFACTURER.ID)
                    .fetchOne();
            manufacturerId = manufacturerRecord.getId();
        }

        ProductRecord productRecord = context.insertInto(SHOPPINGDEMO.PRODUCT,
                SHOPPINGDEMO.PRODUCT.CATEGORY, SHOPPINGDEMO.PRODUCT.ITEMID, SHOPPINGDEMO.PRODUCT.MANUFACTURER_ID,
                SHOPPINGDEMO.PRODUCT.NAME, SHOPPINGDEMO.PRODUCT.PRICE)
                .values(product.getCategory(), product.getItemId(), manufacturerId, product.getName(), product.getPrice())
                .returning(SHOPPINGDEMO.PRODUCT.ID)
                .fetchOne();

        Long productId = productRecord.getId();

        InsertValuesStep3<PropertyRecord, String, String, Long> insertIntoCommand =
                context.insertInto(SHOPPINGDEMO.PROPERTY,
                        SHOPPINGDEMO.PROPERTY.NAME, SHOPPINGDEMO.PROPERTY.VALUE, SHOPPINGDEMO.PROPERTY.PRODUCT_ID);

        List<Property> propertyList = new ArrayList<>(product.getProperties().size());

        for (Property property : product.getProperties()) {
            insertIntoCommand.values(property.getName(), property.getValue(), productId);
            propertyList.add(property);
        }

        Result<PropertyRecord> propertyRecordResult = insertIntoCommand.returning(SHOPPINGDEMO.PROPERTY.ID).fetch();

        Set<Property> updatedPropertySet = new HashSet<>(product.getProperties().size());

        List<Long> propertyIds = propertyRecordResult.getValues(SHOPPINGDEMO.PROPERTY.ID);

        for (int i = 0; i < propertyList.size(); i++) {
            updatedPropertySet.add(Property.builder()
                    .from(propertyList.get(i))
                    .id(propertyIds.get(i))
                    .build());
        }

        return Product.builder()
                .from(product)
                .id(productId)
                .manufacturer(Manufacturer.builder()
                        .from(product.getManufacturer())
                        .id(manufacturerId)
                        .build())
                .properties(updatedPropertySet)
                .build();
    }
}
