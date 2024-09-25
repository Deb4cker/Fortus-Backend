package com.pmnato.fortus.commons.constants;

public final class RestRoutes {

    public static final String BASE_ROUTE = "/";

    public static final String CREATE = BASE_ROUTE + "create";
    public static final String ALL    = BASE_ROUTE + "all";
    public static final String ID     = BASE_ROUTE + "{id}";
    public static final String EDIT   = BASE_ROUTE + "edit/{id}";
    public static final String DELETE = BASE_ROUTE + "delete/{id}";

    public static final String LOGIN  = BASE_ROUTE + "login";

    private RestRoutes() {}
}
