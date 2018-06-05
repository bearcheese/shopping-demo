package hu.bearmaster.shopping.dal;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "hu.bearmaster.shopping.model")
public class DalConfig {

}
