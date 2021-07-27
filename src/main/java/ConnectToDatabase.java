import java.sql.*;

public class ConnectToDatabase {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String connectionUrl = "jdbc:postgresql://localhost:5432/mikesdb";
            Connection connection = DriverManager.getConnection(connectionUrl,
                    "mike", "abcd1234");
            createTableIfNotExists(connection);
            deleteAllRows(connection);
            insertRow(connection, "INSERT INTO spaceship VALUES (1, 'Mike', 99, 'Round');");
            insertRow(connection, "INSERT INTO spaceship VALUES (2, 'Susan', 10, 'Pyramid');");
            selectShips(connection);


            connection.close();
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void deleteAllRows(Connection connection) throws SQLException {
        Statement statementInsert = connection.createStatement();
        String sql = "DELETE FROM SPACESHIP";
        System.out.println("SQL: " + sql);
        statementInsert.execute(sql);
    }

    private static void insertRow(Connection connection, String s) throws SQLException {
        Statement statementInsert = connection.createStatement();
        String sqlInsert = s;
        System.out.println("SQL: " + sqlInsert);
        statementInsert.execute(sqlInsert);
    }

    private static void createTableIfNotExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS spaceship (\n" +
                "    id     integer PRIMARY KEY," +
                "    captain       varchar(14) NOT NULL," +
                "    fuel         integer NOT NULL," +
                "    shape      varchar(255)" +
                ");";
        System.out.println("SQL: " + sqlCreateTable);
        statement.execute(sqlCreateTable);
    }

    private static void selectShips(Connection connection){

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM spaceship;";
            System.out.println("SQL: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String captain = resultSet.getString("captain");
                int fuel = resultSet.getInt("fuel");
                String shape = resultSet.getString("shape");
                System.out.println(String.format("We found a row! %s, %s, %s", captain,
                        fuel,
                        shape
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
