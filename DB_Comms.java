import java.sql.*;

public class DB_Comms {
    String dLocation = "jdbc:ucanaccess://Exercises.accdb";
    private Connection connection;
    private PreparedStatement statement;
    public DB_Comms() {
        try {
            // connect to database
                connection = DriverManager.getConnection(
                        dLocation);
            //System.out.println("Connection successful");
        } catch (SQLException ex) {
            System.out.println("Unable to connect");
        }
        
    }
    /*
    Method Update
    Executes an update query
    @parameters String update query
    @return nothing
    */
    public void update(String update) throws SQLException {
        statement = connection.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }
    /*
    Method query
    Executes a query and then returns the result of it 
    @parameters: String Select Query
    @return: resultset of the result of the query
    */
    public ResultSet query(String stmt) throws SQLException {
        statement = connection.prepareStatement(stmt);
        return statement.executeQuery();
    }

    public String processResultSet(ResultSet rs, String delimiter) {
        String temp = "";
        try {

            ResultSetMetaData meta = rs.getMetaData();
            int size = meta.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= size; i++) {
                    Object value = rs.getObject(i);
                    temp += delimiter + value;
                }
                temp += "\n";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }
}
