package librarymanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-j to Libraries.", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT 1 FROM users WHERE username=? AND password=?";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<LibraryItem> fetchBooks() {
        List<LibraryItem> list = new ArrayList<>();
        String sql = "SELECT id, title, author, year, isbn FROM books";
        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                String isbn = rs.getString("isbn");
                Book b = new Book(id, title, author, year, isbn);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void addBook(String title, String author, int year, String isbn) throws SQLException {
        String sql = "INSERT INTO books(title, author, year, isbn) VALUES(?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setInt(3, year);
            pst.setString(4, isbn);
            pst.executeUpdate();
        }
    }

    public static void deleteBookById(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection conn = connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
}
