package com.blockchainspace.ecommerce.config;

import org.apache.shiro.authc.credential.PasswordService;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordService implements PasswordService {

    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        return BCrypt.hashpw((String) o, BCrypt.gensalt());
    }

    @Override
    public boolean passwordsMatch(Object o, String encrypted) {
        String submittedPlainText = (String) o;
        return BCrypt.checkpw(submittedPlainText, encrypted);
    }

}
