package com.blockchainspace.ecommerce.config;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;

@Log4j2
public class JdbcRealm extends org.apache.shiro.realm.jdbc.JdbcRealm {

    AuthorizationInfo getAuthorizationInfo(Subject subject) {
        return doGetAuthorizationInfo(subject.getPrincipals());
    }
}
