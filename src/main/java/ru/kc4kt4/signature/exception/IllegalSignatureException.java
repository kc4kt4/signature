package ru.kc4kt4.signature.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalSignatureException extends RuntimeException {

    public IllegalSignatureException(String message) {
        super(message);
    }
}
