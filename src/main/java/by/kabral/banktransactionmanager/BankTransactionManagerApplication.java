package by.kabral.banktransactionmanager;

import by.kabral.banktransactionmanager.config.prop.ExternalAPIProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ExternalAPIProperties.class})
public class BankTransactionManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankTransactionManagerApplication.class, args);
    }

}
