package com.paymybuddy.paywebapp.databaseconfig;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class dataBaseConfig {


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        log.info("Create DB connection");
        Class.forName("com.mysql.jdbc.Driver");
        InputStream input = dataBaseConfig.class.getClassLoader().getResourceAsStream("config.properties");
        Properties props = new Properties();

        try {
            props.load(input);
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //username and password are securised in a properties file
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prod", username, password);
    }

    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                log.info("Closing DB connection");
            } catch (SQLException e) {
                log.error("Error" +
                        " while closing connection", e);
            }
        }
    }
}
