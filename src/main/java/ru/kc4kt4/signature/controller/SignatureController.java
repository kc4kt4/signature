package ru.kc4kt4.signature.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kc4kt4.signature.annotation.JsonRestController;
import ru.kc4kt4.signature.handler.CreateSignatureHandler;
import ru.kc4kt4.signature.handler.VerifySignatureHandler;
import ru.kc4kt4.signature.reqeust.ApiRequest;
import ru.kc4kt4.signature.response.CreateSignatureResponse;

import javax.validation.Valid;

/**
 * The type Signature controller.
 */
@Api("Api: signature")
@JsonRestController
@RequestMapping(value = "/signature",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SignatureController {

    private static final String SIGNATURE = "Signature";
    @Autowired
    private CreateSignatureHandler createSignatureHandler;
    @Autowired
    private VerifySignatureHandler verifySignatureHandler;

    /**
     * Verify signature http status.
     *
     * @param apiRequest the api request
     * @return the http status
     */
    @ApiOperation(value = "Проверить подпись")
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = SIGNATURE,
                    value = SIGNATURE,
                    paramType = "header",
                    required = true),
    })
    public HttpStatus verifySignature(@Valid @RequestBody ApiRequest apiRequest) {
        return verifySignatureHandler.verifySignature(apiRequest);
    }

    /**
     * Create signature create signature response.
     *
     * @param apiRequest the api request
     * @return the create signature response
     */
    @ApiOperation(value = "Сформировать подпись")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateSignatureResponse createSignature(@Valid @RequestBody ApiRequest apiRequest) {
        return createSignatureHandler.createSignature(apiRequest);
    }
}
