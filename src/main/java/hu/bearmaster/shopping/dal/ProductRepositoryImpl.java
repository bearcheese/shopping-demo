package hu.bearmaster.shopping.dal;

import static hu.bearmaster.shopping.dal.jooq.Shoppingdemo.SHOPPINGDEMO;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;

import hu.bearmaster.shopping.dal.jooq.tables.records.ManufacturerRecord;
import hu.bearmaster.shopping.model.Manufacturer;
import hu.bearmaster.shopping.model.Product;

class ProductRepositoryImpl implements ProductRepository {

    private final DSLContext context;

    public ProductRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Record> record = context.select()
                .from(SHOPPINGDEMO.PRODUCT)
                .join(SHOPPINGDEMO.MANUFACTURER).on(SHOPPINGDEMO.PRODUCT.MANUFACTURER_ID.eq(SHOPPINGDEMO.MANUFACTURER.ID))
                .where(SHOPPINGDEMO.PRODUCT.ID.eq(id))
                .fetchOptional();

        if (record.isPresent()) {
            Manufacturer manufacturer = Manufacturer.builder()
                    .from(record.get().into(Manufacturer.class))
                    .build();
            Product product = Product.builder()
                    .from(record.get().into(Product.class))
                    .manufacturer(manufacturer)
                    .build();
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        ManufacturerRecord manufacturerRecord = context.insertInto(SHOPPINGDEMO.MANUFACTURER,
                SHOPPINGDEMO.MANUFACTURER.NAME, SHOPPINGDEMO.MANUFACTURER.HEAD_OFFICE)
                .values(product.getManufacturer().getName(), product.getManufacturer().getHeadOffice())
                .returning(SHOPPINGDEMO.MANUFACTURER.ID)
                .fetchOne();

        return null;
    }
}
