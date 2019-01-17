package ru.kc4kt4.signature.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kc4kt4.signature.annotation.JsonRestController;
import ru.kc4kt4.signature.exception.IllegalSignatureException;
import ru.kc4kt4.signature.exception.InternalServerException;
import ru.kc4kt4.signature.response.ErrorResponse;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice(annotations = JsonRestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalSignatureException.class)
    @ResponseBody
    public ErrorResponse handleException(IllegalSignatureException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    public ErrorResponse handleException(InternalServerException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
