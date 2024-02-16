package in.francl.poc.pocspringbootmysqlfieldsbinaryjson._bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
    scanBasePackages = "in.francl.poc.pocspringbootmysqlfieldsbinaryjson"
)
@EntityScan(
    basePackages = "in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.entities"
)
@EnableJpaRepositories(
    basePackages = "in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.rdb.repositories"
)
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
