package ru.kc4kt4.signature.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.kc4kt4.signature.reqeust.ApiRequest;
import ru.kc4kt4.signature.service.serverside.SignatureServiceOnServerSide;

/**
 * The type Verify signature handler.
 */
@Service
public class VerifySignatureHandler {

    @Autowired
    private SignatureServiceOnServerSide signatureServiceOnServerSide;

    /**
     * Verify signature http status.
     *
     * @param request the request
     * @return the http status
     */
    public HttpStatus verifySignature(ApiRequest request) {
        signatureServiceOnServerSide.verifySignature(request.getRequestAsString());
        return HttpStatus.OK;
    }
}
