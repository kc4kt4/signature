package ru.kc4kt4.signature.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.kc4kt4.signature.AbstractTest;
import ru.kc4kt4.signature.reqeust.ApiRequest;
import ru.kc4kt4.signature.response.CreateSignatureResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The type Signature controller test.
 */
public class SignatureControllerTest  extends AbstractTest {
    private final static String HOST_SIGNATURE = "http://localhost:8081/signature";
    private final static String CREATE = "/create";
    private final static String VERIFY = "/verify";
    private final static String SIGNATURE_HEADER_NAME = "Signature";
    private final static String SIGNATURE = "OqEvv5oARkBw/3PsncQO1V0mGoSi81BHGTK6ic6OQeAr/KsEOq+QSeqZChLDUG2EykfUy2sfuRXTw5JKKpGSdZdc4uOVRAWB98j8TBoG3v4T9C/6Q62szI9Yl7kCiq+8NkLmr5IWzfAuBPPT/KAhDfpIR4g4WhUKXnD1D6bpg0aWhDt6UxTGVCvwgc/7nig7nRCWqoYN7h2ET2ImHuVzbf5oQjbQa5krbWyub8zqHSlCYnrgmvFd5kYPnle3FRAG96yhzvm3eXkW6uCkLAQajarWDIuOv1QM4QNU/JDwBqFEaev5WF6if7HvRxrFacYahok1aEYMyr0fNwIN9Of0IQ==";
    private static final String REQUEST_AS_STRING = "{\"firstField\": \"some info\", \"secondField\": \"someInfo\"}";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Create signature test.
     */
    @Test
    public void createSignatureTest() {
        ResponseEntity<CreateSignatureResponse> response = restTemplate.exchange(HOST_SIGNATURE + CREATE,
                HttpMethod.POST,
                new HttpEntity<>(createRequest(), createHeaders(false)),
                CreateSignatureResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(SIGNATURE, response.getBody().getSignature());
    }

    /**
     * Verify signature test.
     */
    @Test
    public void verifySignatureTest() {
        ResponseEntity<Object> response = restTemplate.exchange(HOST_SIGNATURE + VERIFY,
                HttpMethod.POST,
                new HttpEntity<>(createRequest(), createHeaders(true)),
                Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    private ApiRequest createRequest() {
        ApiRequest request = new ApiRequest();
        request.setRequestAsString(REQUEST_AS_STRING);
        return request;
    }

    private HttpHeaders createHeaders(boolean withSignature) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if (withSignature) {
            headers.set(SIGNATURE_HEADER_NAME, SIGNATURE);

        }
        return headers;
    }
}
