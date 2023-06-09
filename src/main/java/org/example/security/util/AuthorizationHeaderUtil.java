package org.example.security.util;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderUtil {

    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);

}
