package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Orders;

public class OrderController extends BaseController {
    public OrderController(Connection con) {
        super(con);
    }

    public ArrayList<Orders> getOrdersByUserId(int userId) {
        ArrayList<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE userID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders o = extractOrderFromResultSet(rs);
                    orders.add(o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Orders> getAllOrders() {
        ArrayList<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Orders o = extractOrderFromResultSet(rs);
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public boolean createOrder(Orders order) {
        String sql = "INSERT INTO orders (userID,productId, paymentMethodId, orderDate,quantity, totalAmount, address, phone, orderState) VALUES (?,?,?, ?,?, ?, ?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, order.getUserID());
            ps.setInt(2, order.getProductID());
            ps.setInt(3, order.getPaymentMethodID());
            ps.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setInt(5, order.getQuantity());
            ps.setDouble(6, order.getTotalAmount());
            ps.setString(7, order.getAddress());
            ps.setString(8,order.getPhone());
            ps.setString(9, order.getOrderState());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean confirmOrder(int orderId) {
        return updateOrderState(orderId, "Confirmed");
    }
    public boolean cancelOrder(int orderId) {
        return updateOrderState(orderId, "Canceled");
    }
    private boolean updateOrderState(int orderId, String newState) {
        String sql = "UPDATE orders SET orderState = ? WHERE orderID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newState);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Orders extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Orders o = new Orders();
        o.setOrderID(rs.getInt("orderID"));
        o.setUserID(rs.getInt("userID"));
        o.setOrderDate(rs.getDate("orderDate"));
        o.setOrderState(rs.getString("orderState"));
        o.setTotalAmount(rs.getDouble("totalAmount"));
        o.setQuantity(rs.getInt("quantity"));
        return o;
    }
}

