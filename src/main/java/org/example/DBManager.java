package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import static org.example.Constants.*;

public class DBManager {
    private DBManager() {
    }
    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(JDBC_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createDB() {
        try (var conn = getConnection()) {
            var statement = conn.createStatement();
            statement.execute(CREATE_YEARS_TABLE);
            statement.execute(CREATE_TYPES_TABLE);
            statement.execute(CREATE_STREETS_TABLE);
            statement.execute(CREATE_GRANTS_TABLE);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCategories(String table, String value) {
        var sql = "insert into " + table + " values (?,?);";
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("INCORRECT " + value);
            e.printStackTrace();
        }
    }

    public static void insertGrants (Grant value) {
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(INSERT_GRANTS);
            preparedStatement.setString(1, value.name);
            preparedStatement.setInt(2, getId(INSERT_STREETS, value.street));
            preparedStatement.setDouble(3, value.grant_size);
            preparedStatement.setInt(4, getId(INSERT_YEARS, value.year));
            preparedStatement.setInt(5, getId(INSERT_TYPES, value.type));
            preparedStatement.setInt(6, value.workplaces);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getId(String query, String value) {
        var res = 0;
        try (var conn = getConnection()) {
            var preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, value);
            var resSet = preparedStatement.executeQuery();
            while (resSet.next()) {
                res = resSet.getInt(1);
            }
            preparedStatement.close();
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;

    }
}