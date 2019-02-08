package ru.kc4kt4.signature.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * The type Signature properties.
 */
@Component
@ConfigurationProperties(prefix = "security")
@PropertySource("classpath:application.yml")
@Data
@Validated
public class SignatureProperties {

    @NotBlank
    private String alias;
    @NotBlank
    private String password;
    @NotBlank
    private String algorithm;
    @NotBlank
    private String p12FileName;
    @NotBlank
    private String keyStoreInstanceName;

    @NotBlank
    private String name;
    @NotBlank
    private String fileName;
}
