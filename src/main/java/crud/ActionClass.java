package crud;

import java.sql.*;
import java.util.HashMap;

public class ActionClass {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void dropTable() throws SQLException {
        stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS student;");
    }

    static void createTable() throws SQLException {
        pstmt = connection.prepareStatement("CREATE TABLE student ("+
                "       student_id integer primary key autoincrement\n" +
                "    , student_name varchar(100)\n" +
                "    , score tinyint);");
        pstmt.execute();
    }

    static void createRow(String student_name, int score) throws SQLException {
        pstmt = connection.prepareStatement("INSERT INTO student (student_name, score)" +
                "VALUES (?, ?)");
        pstmt.setString(1, student_name);
        pstmt.setInt(2, score);
        pstmt.execute();
    }

    static void updateScore(String student_name, int score) throws SQLException {
        pstmt.executeUpdate("UPDATE student SET score = ? WHERE student_name = ?");
        pstmt.setInt(1, score);
        pstmt.setString(2, student_name);
        pstmt.execute();
    }

    public static void main (String ... args) throws SQLException {
        connect();
        dropTable();
        createTable();

        HashMap<String, Integer> sar = new HashMap<String, Integer>();
        sar.put("Ivanov", 1);
        sar.put("Petrov", 2);
        sar.put("Sidorov", 3);
        sar.forEach((k, v) -> {
            try {
                createRow(k, v);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        disconnect();
    }
}
