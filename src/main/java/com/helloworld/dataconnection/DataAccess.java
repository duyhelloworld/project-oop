package com.helloworld.dataconnection;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DataAccess {

    private static DataAccess access;

    private DataAccess() {
        getConn();
    };

    public static DataAccess getInstance(){
        if (access == null) {
            access = new DataAccess();
        }
        return access;
    }

    private static Connection getConn(){
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Reader loader = new FileReader("src/main/resources/app.properties");

            Properties properties = new Properties();
            properties.load(loader);

            String url = "";
            String usr = properties.getProperty("username");
            String pss = properties.getProperty("password");
            String dbName = properties.getProperty("database.name");
            if (properties.containsKey("url")) {
                url = properties.getProperty("url");
            } else {
                url = "jdbc:mysql://localhost:3306/" + dbName;
            }

            conn = DriverManager.getConnection(url, usr, pss);

            if (conn == null) {
                throw new SQLException("Cannot validate connection: some information is incorrect");
            }

            return conn;
        } catch (IOException e) {
            System.out.println("IO exception : " + e.getLocalizedMessage());
        } catch (SQLException e){
            System.out.println("SQL exception : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Not found MySQL driver!");
        }
        return null;
    }

    public PreparedStatement getStatement(String query) {
        PreparedStatement stm = null;
        Connection conn = getConn();
        try {
            if (conn == null || conn.isClosed() ) {
                throw new SQLException("Something wrong when create query");
            }
            stm = conn.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return stm;
    }


    public void changeQuery(PreparedStatement statement, String newQuery) {
        try {
            statement.close();
            statement = getConn().prepareStatement(newQuery);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
