package dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest_getResultSet {

    @Test
    public void getResultSet() throws SQLException {
        assertNotNull(DBConnection.getStatement(ConnectionPool.getInstance().getConnection()).executeQuery("SELECT * FROM users"));
    }
}