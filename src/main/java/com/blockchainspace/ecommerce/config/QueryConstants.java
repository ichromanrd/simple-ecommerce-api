package com.blockchainspace.ecommerce.config;

public class QueryConstants {

    public static final String AUTHENTICATION_QUERY = "SELECT password FROM users WHERE username = ?";

    public static final String USER_ID_QUERY = "SELECT id FROM users WHERE username = ?";

    public static final String QUERY_ROLE_QUERY = String.format("SELECT role_name FROM user_roles WHERE user_id = %s",
            USER_ID_QUERY);

    public static final String PERMISSIONS_QUERY = "SELECT permission FROM roles_permissions WHERE role_name = ?";

}
