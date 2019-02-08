package ru.kc4kt4.signature.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type Create signature response.
 */
@Data
public class CreateSignatureResponse {

    @ApiModelProperty(value = "Сформированная подпись")
    private String signature;
}
