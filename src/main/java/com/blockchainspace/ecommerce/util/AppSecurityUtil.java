package com.blockchainspace.ecommerce.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public final class AppSecurityUtil {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static <T> T getAttribute(String attributeName) {
        return (T) getSession().getAttribute(attributeName);
    }

}
