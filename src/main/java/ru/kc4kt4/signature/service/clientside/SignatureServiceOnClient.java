package ru.kc4kt4.signature.service.clientside;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.signature.config.properties.SignatureProperties;
import ru.kc4kt4.signature.exception.IllegalSignatureException;

import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * The type Signature service on client.
 */
@Slf4j
@Service
public class SignatureServiceOnClient {

    @Autowired
    private SignatureProperties signatureProperties;

    /**
     * Create signature string.
     *
     * @param request the request
     * @return the string
     */
    public String createSignature(String request) {
        try {
            KeyStore keystore = getKeyStore();

            PrivateKey key = getPrivateKey(keystore);

            Signature sig = Signature.getInstance(signatureProperties.getAlgorithm());
            sig.initSign(key);

            byte[] bytes = request.getBytes(StandardCharsets.UTF_8);
            sig.update(bytes);

            byte[] sign = sig.sign();

            return Base64.getEncoder().encodeToString(sign);

        } catch (Exception e) {
            log.error("Error with creating signature", e);
            throw new IllegalSignatureException("Something went wrong!");
        }
    }

    private KeyStore getKeyStore() throws Exception {
        KeyStore keystore = KeyStore.getInstance(signatureProperties.getKeyStoreInstanceName());
        keystore.load(SignatureServiceOnClient.class.getClassLoader()
                              .getResourceAsStream(signatureProperties.getP12FileName()),
                      signatureProperties.getPassword().toCharArray());
        return keystore;
    }

    private PrivateKey getPrivateKey(KeyStore keystore) throws Exception {
        return (PrivateKey) keystore.getKey(signatureProperties.getAlias(),
                                            signatureProperties.getPassword().toCharArray());
    }
}
