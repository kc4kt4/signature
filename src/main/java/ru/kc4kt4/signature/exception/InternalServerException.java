package ru.kc4kt4.signature.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
}
