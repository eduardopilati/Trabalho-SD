package database;

import java.sql.Connection;
import java.sql.DriverManager;

class ConnectionFactory {
    static String driver = "org.postgresql.Driver";
    static String user = "postgres";
    static String senha = "masterkey";
    static String url = "jdbc:postgresql://localhost:5432/sd";
    
    public static Connection getConnection() throws DatabaseException{
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return conn;
    }
}
