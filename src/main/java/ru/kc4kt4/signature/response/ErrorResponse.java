package ru.kc4kt4.signature.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Error response.
 */
@NoArgsConstructor
@Getter
public class ErrorResponse {
    private ApiError[] apiErrors;

    /**
     * Instantiates a new Error response.
     *
     * @param apiErrors the api errors
     */
    public ErrorResponse(ApiError... apiErrors) {
        this.apiErrors = apiErrors;
    }

    /**
     * Instantiates a new Error response.
     *
     * @param code the code
     * @param title the title
     */
    public ErrorResponse(Integer code, String title) {
        this(new ApiError(code, title));
    }
}
