package rest.assured.sample.project.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "petstore.api")
public class PetStoreProperties {
    private String baseUrl;
}