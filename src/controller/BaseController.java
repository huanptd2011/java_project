package controller;

import java.sql.Connection;

public abstract class BaseController {
    protected Connection con;

    public BaseController(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return con;
    }
}