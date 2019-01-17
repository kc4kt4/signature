package ru.kc4kt4.signature.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kc4kt4.signature.reqeust.ApiRequest;
import ru.kc4kt4.signature.response.CreateSignatureResponse;
import ru.kc4kt4.signature.service.clientside.SignatureServiceOnClient;

@Service
public class CreateSignatureHandler {

    @Autowired
    private SignatureServiceOnClient signatureServiceOnClient;

    public CreateSignatureResponse createSignature(ApiRequest apiRequest) {
        String signature = signatureServiceOnClient.createSignature(apiRequest.getRequestAsString());
        CreateSignatureResponse response = new CreateSignatureResponse();
        response.setSignature(signature);
        return response;
    }
}
