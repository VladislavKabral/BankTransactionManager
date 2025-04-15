package by.kabral.banktransactionmanager.config.prop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import static by.kabral.banktransactionmanager.util.Description.*;

@OpenAPIDefinition(
        info = @Info(
                title = PROJECT_TITLE,
                description = PROJECT_DESCRIPTION,
                version = PROJECT_VERSION,
                contact = @Contact(
                        name = PROJECT_AUTHOR_NAME,
                        email = PROJECT_AUTHOR_EMAIL
                )
        )
)
public class OpenApiConfig {
}
