package ru.kc4kt4.signature.service.clientside;

import com.sun.xml.internal.txw2.IllegalSignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.signature.config.properties.SignatureProperties;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

@Slf4j
@Service
public class SignatureServiceOnClient {

    @Autowired
    private SignatureProperties signatureProperties;

    public String createSignature(String request) {
        try {
            KeyStore keystore = KeyStore.getInstance(signatureProperties.getKeyStoreInstanceName());
            keystore.load(SignatureServiceOnClient.class.getClassLoader()
                            .getResourceAsStream(signatureProperties.getP12FileName()),
                    signatureProperties.getPassword().toCharArray());
            PrivateKey key = (PrivateKey) keystore.getKey(signatureProperties.getAlias(),
                    signatureProperties.getPassword().toCharArray());

            Signature sig = Signature.getInstance(signatureProperties.getAlgorithm());
            sig.initSign(key);

            byte[] bytes = request.getBytes(StandardCharsets.UTF_8);

            sig.update(bytes);
            byte[] sign = sig.sign();

            return Base64.getEncoder().encodeToString(sign);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalSignatureException("Something went wrong!");
        }
    }
}
