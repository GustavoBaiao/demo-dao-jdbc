package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static  Connection con = null;

    public static Connection getConnection()  {
        if (con == null) {
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                con = DriverManager.getConnection(url,prop);
            }catch(SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
