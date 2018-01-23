package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBConnectionTest_getStatement {

    @Test
    public void getStatement() {
        assertNotNull(DBConnection.getStatement(DBConnection.getConnection()));
    }
}