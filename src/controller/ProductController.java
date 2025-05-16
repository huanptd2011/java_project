package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Products;



public class ProductController {
    private Connection con;

    public ProductController(Connection con) {
        this.con = con;
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
                p.setProductID(rs.getInt(1));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setBrand(rs.getString("brand"));
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
    public boolean addProduct(Products product) {
        String sql = "INSERT INTO products(productName, description, brand, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, product.getProductName());
            pre.setString(2, product.getDescription());
            pre.setString(3, product.getBrand());
            pre.setInt(4, product.getQuantity());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

