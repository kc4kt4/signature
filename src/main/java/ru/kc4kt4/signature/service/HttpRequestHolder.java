package ru.kc4kt4.signature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The type Http request holder.
 */
@Component
public class HttpRequestHolder {

    private static final String SIGNATURE = "Signature";

    private HttpServletRequest httpServletRequest;

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
        return getHttpRequestHeader(SIGNATURE);
    }

    private Optional<HttpServletRequest> getHttpServletRequest() {
        Optional<HttpServletRequest> result = Optional.empty();
        if (RequestContextHolder.getRequestAttributes() != null) {
            result = Optional.of(this.httpServletRequest);
        }
        return result;
    }

    @Autowired
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    private void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    private String getHttpRequestHeader(String headerName) {
        return getHttpServletRequest()
                .map(request -> request.getHeader(headerName))
                .orElse(null);
    }
}
