package juja.sqlcmd;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDatabaseManagerTest {
    protected static final String TEST_DB_NAME = "sqlcmd_test";
    protected static final String DB_USER_LOGIN = "sqlcmd";
    protected static final String DB_USER_PASSWORD = "sqlcmd";
    private static final String TEST_TABLE_NAME = "test_table";

    DatabaseManager databaseManager;

    public void init(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Test
    public void getTableNamesWithTwoTables() throws SQLException {
        createTestTableWithIdAndName("table1");
        createTestTableWithIdAndName("table2");
        String[] expectedArray = {"table1", "table2"};
        String[] actualArray = databaseManager.getTableNames();
        assertThat(actualArray, arrayContainingInAnyOrder(expectedArray));
    }

    @Test
    public void getTableNamesWhenDbHasNoTables() throws SQLException {
        String[] expectedArray = new String[]{};
        String[] actualArray = databaseManager.getTableNames();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void getTableDataWithEmptyTable() throws SQLException {
        createTestTableWithIdAndName(TEST_TABLE_NAME);
        DataSet[] expectedArray = new DataSet[0];
        Assert.assertArrayEquals(expectedArray, databaseManager.getTableData(TEST_TABLE_NAME));
    }

    @Test
    public void getTableDataWithNotExistingTable() throws SQLException {
        DataSet[] expectedArray = new DataSet[0];
        Assert.assertArrayEquals(expectedArray, databaseManager.getTableData("WrongTableName"));
    }

    @Test
    public void getTableDataWithValidTableTwoRows() throws SQLException {
        createTestTableWithIdAndName(TEST_TABLE_NAME);
        DataSet row1 = new DataSet(2);
        row1.insertValue(0, "1");
        row1.insertValue(1, "name1");
        insertData(TEST_TABLE_NAME, row1);
        DataSet row2 = new DataSet(2);
        row2.insertValue(0, "2");
        row2.insertValue(1, "name2");
        insertData(TEST_TABLE_NAME, row2);
        DataSet[] expectedArray = new DataSet[]{row1, row2};
        DataSet[] actualArray = databaseManager.getTableData(TEST_TABLE_NAME);
        assertThat(actualArray, arrayContainingInAnyOrder(expectedArray));
    }

    @Test
    public void insertWithExistingTable() throws SQLException {
        createTestTableWithIdAndName(TEST_TABLE_NAME);
        DataSet row = new DataSet(2);
        row.insertValue(0, "1");
        row.insertValue(1, "name1");
        assertTrue(databaseManager.insert(TEST_TABLE_NAME, row));
    }

    @Test
    public void insertWithNotExistingTable() {
        DataSet row = new DataSet(2);
        row.insertValue(0, "1");
        row.insertValue(1, "name1");
        assertFalse(databaseManager.insert("tableDoesNotExist", row));
    }

    @Test
    public void insertWithExtraParameters() throws SQLException {
        createTestTableWithIdAndName(TEST_TABLE_NAME);
        DataSet row = new DataSet(3);
        row.insertValue(0, "1");
        row.insertValue(1, "name1");
        row.insertValue(2, "name2");
        assertFalse(databaseManager.insert(TEST_TABLE_NAME, row));
    }

    @Test
    public void insertIntoTableRowWithoutId() throws SQLException {
        createTestTableWithIdAndName(TEST_TABLE_NAME);
        DataSet row = new DataSet(2);
        row.insertValue(1, "name1");
        assertFalse(databaseManager.insert(TEST_TABLE_NAME, row));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void delete() {
        databaseManager.delete(TEST_TABLE_NAME, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() {
        databaseManager.update(TEST_TABLE_NAME, 1);
    }

    void insertData(String testTableName, DataSet row) {
        databaseManager.insert(testTableName, row);
    }

    abstract void createTestTableWithIdAndName(String tableName) throws SQLException;

}
