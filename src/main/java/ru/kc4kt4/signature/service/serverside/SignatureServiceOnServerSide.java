package ru.kc4kt4.signature.service.serverside;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.signature.config.properties.SignatureProperties;
import ru.kc4kt4.signature.exception.IllegalSignatureException;
import ru.kc4kt4.signature.exception.InternalServerException;
import ru.kc4kt4.signature.service.HttpRequestHolder;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;

/**
 * The type Signature service on server side.
 */
@Service
@Slf4j
public class SignatureServiceOnServerSide {

    @Autowired
    private SignatureProperties signatureProperties;
    @Autowired
    private HttpRequestHolder httpRequestHolder;

    /**
     * Verify signature.
     *
     * @param requestAsString the request as string
     */
    public void verifySignature(String requestAsString) {
        String signature = httpRequestHolder.getSignature();

        try {
            byte[] decode = Base64.getDecoder().decode(signature);

            CertificateFactory certificateFactory = CertificateFactory.getInstance(signatureProperties.getName());
            InputStream is = SignatureServiceOnServerSide.class
                    .getClassLoader()
                    .getResourceAsStream(signatureProperties.getFileName());
            assert is != null;

            InputStream certInputStream = new BufferedInputStream(is);
            Certificate certificate = certificateFactory.generateCertificate(certInputStream);

            PublicKey publicKey = certificate.getPublicKey();

            Signature signatureVerified = Signature.getInstance(signatureProperties.getAlgorithm());

            signatureVerified.initVerify(publicKey);

            signatureVerified.update(requestAsString.getBytes());

            boolean verified = signatureVerified.verify(decode);

            if (!verified) {
                throw new IllegalSignatureException("Error while handle signature");
            }
        } catch (IllegalSignatureException e) {
            log.error("Error with verifying signature", e);
            throw e;
        } catch (Exception ex) {
            log.error("Unknown error while verify signature processing", ex);
            throw new InternalServerException("Something went wrong!");
        }
    }
}
