package ru.kc4kt4.signature.reqeust;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * The type Api request.
 */
@Data
public class ApiRequest {

    @ApiModelProperty(value = "Запрос в строке", required = true)
    @NotBlank
    private String requestAsString;
}
