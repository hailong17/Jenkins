import java.sql.*;

public class JDBCParserData {
    public static void main(String[] args) {
        String jdbcUrl  = "jdbc:mysql://localhost:3306/assignment10"; 
        String username = "root"; 
        String password = ""; 

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Get metadata about the database
            DatabaseMetaData metaData = connection.getMetaData();

            // Get all table names
            ResultSet tableResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (tableResultSet.next()) {
                String tableName = tableResultSet.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                // Get all column names and types for the current table
                ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
                while (columnResultSet.next()) {
                    String columnName = columnResultSet.getString("COLUMN_NAME");
                    String columnType = columnResultSet.getString("TYPE_NAME");
                    System.out.println("Column: " + columnName + " - Type: " + columnType);
                }
                columnResultSet.close();
                System.out.println("\n");
            }
            tableResultSet.close();

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}