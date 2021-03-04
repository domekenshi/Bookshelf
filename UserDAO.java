package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/bookshelf";
    private final String DB_USER = "user04";
    private final String DB_PASS = "user04pass";

    public UserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        // ユーザーリストの作成
        List<User> userList = new ArrayList<>();

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SQL分を変数に代入
            String sql = "SELECT*FROM USERLIST ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            // 実行結果を取得した結果を代入
            ResultSet rs = pStmt.executeQuery();
            // 1行１行調べていく
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String password = rs.getString("PASSWORD");
                String address = rs.getString("MAILADDRESS");
                // ユーザーbeansのコンストラクタ呼び出し
                User user = new User(id, userName, password, address);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
        return userList;
    }

    public List<String> findAddress() {
        List<String> userAddress = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SQL分を変数に代入
            String sql = "SELECT*FROM USERLIST ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            // 実行結果を取得した結果を代入
            ResultSet rs = pStmt.executeQuery();
            // 1行１行調べていく
            while (rs.next()) {
                String address = rs.getString("MAILADDRESS");
                // ユーザーbeansのコンストラクタ呼び出し
                userAddress.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
        return userAddress;
    }

    public boolean addUser(User user) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SQL分を変数に代入
            String sql = "INSERT INTO USERLIST (NAME,PASSWORD,MAILADDRESS) VALUES(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // 受け取ったユーザー情報の取得

            String userName = user.getUserName();
            String password = user.getPassword();
            String address = user.getAddress();
            // ？に値を代入

            pStmt.setString(1, userName);
            pStmt.setString(2, password);
            pStmt.setString(3, address);
            // アップデートできるか
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int getId(User user) {
        int id = -1;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT ID,MAILADDRESS FROM USERLIST WHERE MAILADDRESS IN (\'" + user.getAddress() + "\')";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            String userAddress = user.getAddress();
            rs.next();
            String address = rs.getString("MAILADDRESS");
            id = rs.getInt("ID");
            if (!userAddress.equals(address)) {
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;

        }
        return id;
    }

}
