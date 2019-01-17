package ru.kc4kt4.signature.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateSignatureResponse {

    @ApiModelProperty(value = "Сформированная подпись")
    private String signature;
}
