package ru.kc4kt4.signature.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Api error.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String transcription;

    /**
     * Instantiates a new Api error.
     *
     * @param code the code
     * @param transcription the transcription
     */
    public ApiError(int code, String transcription) {
        this.code = String.valueOf(code);
        this.transcription = transcription;
    }
}
