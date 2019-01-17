package ru.kc4kt4.signature.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ErrorResponse {
    private ApiError[] apiErrors;

    public ErrorResponse(ApiError... apiErrors) {
        this.apiErrors = apiErrors;
    }

    public ErrorResponse(Integer code, String title) {
        this(new ApiError(code, title));
    }
}
