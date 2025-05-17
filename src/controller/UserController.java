package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Users;
import models.Users;

public class UserController extends BaseController{
    public UserController(Connection con) {
    	super(con);
    }
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();

            while (rs.next()) {
                Users u = new Users();
                u.setUserID(rs.getInt("userID"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullName(rs.getString("fullName"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setStatus(rs.getInt("status"));
                u.setRole(rs.getString("role"));
                userList.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pre != null) pre.close(); } catch (Exception e) {}
        }

        return userList;
    }

    public Users login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setUserID(rs.getInt("userID"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("fullName"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setStatus(rs.getInt("status"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean lockAccount(int userId) {
        String sql = "UPDATE users SET status = 0 WHERE userID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean UnLockAccount(int userId) {
        String sql = "UPDATE users SET status = 1 WHERE userID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean isUserExists(String username, String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addUser(Users user) {
        String sql = "INSERT INTO users (username, password, fullName, email, phone, address, status, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setInt(7, user.getStatus());
            ps.setString(8, user.getRole());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean Register(String username, String password) {
        if (isUserExists(username, password)) {
            System.out.println("Username hoặc email đã tồn tại.");
            return false;
        }
        String sql = "Insert into users (username,password) values (?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInfo(Users user) {
        if (user == null || user.getUserID() == 0) {
            System.out.println("User không hợp lệ để cập nhật.");
            return false;
        }

        String updateSql = "UPDATE users SET fullName = ?, email = ?, phone = ?, address = ?, status = ?, role = ? WHERE userID = ?";
        try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {
            updateStmt.setString(1, user.getFullName());
            updateStmt.setString(2, user.getEmail());
            updateStmt.setString(3, user.getPhone());
            updateStmt.setString(4, user.getAddress());
            updateStmt.setInt(5, user.getStatus());
            updateStmt.setString(6, user.getRole());
            updateStmt.setInt(7, user.getUserID());

            int rows = updateStmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkOldPassword(int userId, String oldPassword) throws SQLException {
        String sql = "SELECT password FROM users WHERE userID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                return oldPassword.equals(dbPassword);
            }
            return false;
        }
    }
    public boolean changePassword(int userId, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE userID = ?";
        try (PreparedStatement updateStmt = con.prepareStatement(sql)) {
            updateStmt.setString(1, newPassword); 
            updateStmt.setInt(2, userId);
            int rows = updateStmt.executeUpdate();
            return rows > 0;
        }
    }
}
