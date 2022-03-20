package com.blockchainspace.ecommerce.config;

public class QueryConstants {

    public static final String AUTHENTICATION_QUERY = "SELECT password FROM user WHERE username = ?";

    public static final String QUERY_ROLE_QUERY = String.format("SELECT role_name FROM user_role WHERE user_id = %s",
            AUTHENTICATION_QUERY);

    public static final String PERMISSIONS_QUERY = String.format("SELECT permission FROM role_permissions WHERE " +
            "role_name IN (%s)", QUERY_ROLE_QUERY);

}
