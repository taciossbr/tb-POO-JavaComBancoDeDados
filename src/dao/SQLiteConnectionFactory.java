
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tacioss
 */
public class SQLiteConnectionFactory {
    public static Connection getConnection(){
        String url = "jdbc:sqlite:cds.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
