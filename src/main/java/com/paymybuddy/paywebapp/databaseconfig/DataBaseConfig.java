package com.paymybuddy.paywebapp.databaseconfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        logger.info("Create DB connection");
        Class.forName("com.mysql.jdbc.Driver");
        InputStream input = DataBaseConfig.class.getClassLoader().getResourceAsStream("config.properties");
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
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection", e);
            }
        }
    }
}
