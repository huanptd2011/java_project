package controller;

import java.sql.Connection;
import java.util.ArrayList;

import models.Orders;
import models.Products;
import models.Users;

public class MainProject {
	static Connection con;
	 public static void main(String[] args) {
		  con = ConnectData.getConnection();
		   ProductController text1 = new ProductController(con);
	        ArrayList<Products> dsCongViec = text1.getAllProduct();
	        UserController text2 = new UserController(con);
	        ArrayList<Users> dsnguoidung = text2.getAllUsers();
	        OrderController text3 = new OrderController(con);
	        ArrayList<Orders> dsdonhang = text3.getAllOrders();
//	        Products newProduct = new Products();
//	        newProduct.setProductName("Laptop HP");
//	        newProduct.setDescription("Core i5, 8GB RAM");
//	        newProduct.setBrand("HP");
//	        newProduct.setQuantity(7);
//
//	        // 2. Gọi addProduct
//	        boolean result = text1.addProduct(newProduct);
//	        if (result) {
//	            System.out.println("Thêm sản phẩm thành công!");
//	        } else {
//	            System.out.println("Thêm sản phẩm thất bại!");
//	        }
	        // Printing out the congViec objects from the list
//	        String ProductName = "Laptop HP";
//	        boolean result = text1.deleteProduct(ProductName);
//	        if (result) {
//	            System.out.println("Xoa sản phẩm thành công!");
//	        } else {
//	            System.out.println("Xoa sản phẩm thất bại!");
//	        }
//	        for (Products x : dsCongViec) {
//	            System.out.println(x);  // This assumes the congViec class has a proper toString method
//	        }
//	        for (Users x : dsnguoidung) {
//	            System.out.println(x);  // This assumes the congViec class has a proper toString method
//	        }
	        for (Orders x : dsdonhang) {
	            System.out.println(x);  // This assumes the congViec class has a proper toString method
	        }
	        
	    }
}
