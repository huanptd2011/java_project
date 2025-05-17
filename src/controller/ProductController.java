package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.x.XProtocolError;

import models.Products;



public class ProductController extends BaseController {
    public ProductController(Connection con) {
        super(con);
    }
    public ArrayList<Products> getAllProduct() {
        ArrayList<Products> listProduct = new ArrayList<>();
        String sql = "SELECT * FROM products";
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                listProduct.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pre != null) pre.close(); } catch (Exception e) {}
        }

        return listProduct;
    }
    public Products getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE productId = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Products p = new Products();
                    p.setProductID(rs.getInt("productId"));
                    p.setProductName(rs.getString("productName"));
                    p.setDescription(rs.getString("description"));
                    p.setBrand(rs.getString("brand"));
                    p.setPrice(rs.getDouble("price"));
                    p.setQuantity(rs.getInt("quantity"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addProduct(Products product) {
        String sql = "INSERT INTO products(productName, description, brand,price, quantity) VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, product.getProductName());
            pre.setString(2, product.getDescription());
            pre.setString(3, product.getBrand());
            pre.setDouble(4, product.getPrice());
            pre.setInt(5, product.getQuantity());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteProduct(String ProductName) {
        String sql = "DELETE FROM products WHERE productName = ?";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, ProductName);  
            int rowsAffected = pre.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProduct(Products product) {
        String sql = "UPDATE products SET productName = ?, description = ?, brand = ?,price = ?, quantity = ? WHERE productID = ?";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, product.getProductName());
            pre.setString(2, product.getDescription());
            pre.setString(3, product.getBrand());
            pre.setDouble(4, product.getPrice());
            pre.setInt(5, product.getQuantity());
            pre.setInt(6, product.getProductID());
            int rowsAffected = pre.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        public boolean updateProductQuantity(Products product) {
            String sql = "UPDATE products SET quantity = ? WHERE productID = ?";
            try (PreparedStatement pre = con.prepareStatement(sql)) {
                pre.setInt(1, product.getQuantity());
                pre.setInt(2, product.getProductID());
                int rowsAffected = pre.executeUpdate();
                return rowsAffected > 0;  
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        
        
    }

    
}

