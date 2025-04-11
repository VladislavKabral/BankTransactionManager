package by.kabral.banktransactionmanager.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("external-api")
public class ExternalAPIProperties {
  private String scheme;
  private String host;
  private String path;
  private String symbolParamName;
  private String keyParamName;
  private String key;
}
