package hu.bearmaster.shopping;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShoppingApplicationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingApplicationTest.class);

    @Test
    void contextLoads() {
        LOGGER.info("Spring context successfully loaded!");
    }
}
