package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
    private static final String serverName = "127.0.0.1";    
    private static final String portNumber = "3306";
    private static final String databaseName = "javafinal";        
    private static final String username = "root";             
    private static final String password = "Huan610193@";           

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối MySQL thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver JDBC cho MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến MySQL:");
//            System.err.println("SQLState: " + e.getSQLState());
//            System.err.println("Error Code: " + e.getErrorCode());
//            System.err.println("Message: " + e.getMessage());
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println(" Đã đóng kết nối.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
