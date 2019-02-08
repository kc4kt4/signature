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

/**
 * The type Api exception handler.
 */
@Slf4j
@ControllerAdvice(annotations = JsonRestController.class)
public class ApiExceptionHandler {

    /**
     * Handle exception error response.
     *
     * @param e the exception
     * @param httpResponse the http response
     * @return the error response
     */
    @ExceptionHandler(IllegalSignatureException.class)
    @ResponseBody
    public ErrorResponse handleException(IllegalSignatureException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * Handle exception error response.
     *
     * @param e the exception
     * @param httpResponse the http response
     * @return the error response
     */
    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    public ErrorResponse handleException(InternalServerException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
