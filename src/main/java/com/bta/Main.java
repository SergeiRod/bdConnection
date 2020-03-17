package com.bta;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(inputStream == null) {
                System.out.println("Problem with property file! ");
                return;
            }
            properties.load(inputStream);
        }

        final String url = properties.getProperty("db.url");
        final String username = properties.getProperty("db.username");
        final String password = properties.getProperty("db.password");


        /*final String url = "jdbc:postgresql://localhost:5432/";
        final String username = "postgres";
        final String password = "siLLa82!sr";*/

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book");

            System.out.println("Book name | Description ");

            while (resultSet.next()) {
                String bookName = resultSet. getString("name");
                String bookDescription = resultSet. getString("description");
                System.out.println(bookName + " | " + bookDescription);
            }
            System.out.println("All data processed!!!");
        }

    }
}
