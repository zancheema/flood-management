package com.shaikha.floodmanagement.cofig;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
@Data
@AllArgsConstructor
public class RsaKeyProperties {
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;
}
