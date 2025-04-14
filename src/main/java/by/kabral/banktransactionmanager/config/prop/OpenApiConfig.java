package by.kabral.banktransactionmanager.config.prop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Bank Transaction Manager Api",
                description = "API of bank transaction manager",
                version = "1.0.0",
                contact = @Contact(
                        name = "Kabral Vladislav",
                        email = "mr.kabral@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
