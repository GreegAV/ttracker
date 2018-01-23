package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest_getStatement {

    @Test
    public void getStatement() throws SQLException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        assertNotNull(DBConnection.getStatement(connection));
    }
}