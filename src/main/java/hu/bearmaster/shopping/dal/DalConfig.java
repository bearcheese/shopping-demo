package hu.bearmaster.shopping.dal;

import org.jooq.DSLContext;
import org.jooq.RecordMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hu.bearmaster.shopping.dal.jooq.MyRecordMapperProvider;
import hu.bearmaster.shopping.dal.service.ProductService;
import hu.bearmaster.shopping.dal.spring.InterfaceBasedJpaRepositoryFactoryBean;

@Configuration
@EntityScan(basePackages = "hu.bearmaster.shopping.dal.model")
@EnableJpaRepositories(repositoryFactoryBeanClass = InterfaceBasedJpaRepositoryFactoryBean.class)
public class DalConfig {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public RecordMapperProvider recordMapperProvider() {
        return new MyRecordMapperProvider();
    }

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository);
    }
}
