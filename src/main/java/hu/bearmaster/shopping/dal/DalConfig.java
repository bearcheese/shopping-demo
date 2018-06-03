package hu.bearmaster.shopping.dal;

import org.jooq.DSLContext;
import org.jooq.RecordMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DalConfig {

    @Autowired
    private DSLContext dslContext;

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl(dslContext);
    }

    @Bean
    public RecordMapperProvider recordMapperProvider() {
        return new MyRecordMapperProvider();
    }
}
