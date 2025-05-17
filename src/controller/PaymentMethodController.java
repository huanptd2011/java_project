package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.PaymentMethod;

public class PaymentMethodController extends BaseController {
	  public PaymentMethodController(Connection con) {
	        super(con);
	    }
	public ArrayList<PaymentMethod> getAllPaymentMethods() {
        ArrayList<PaymentMethod> methods = new ArrayList<>();
        String sql = "SELECT * FROM paymentMethod";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PaymentMethod m = new PaymentMethod();
                m.setPaymentMethodID(rs.getInt("paymentMethodId"));
                m.setPaymentMethodName(rs.getString("paymentMethodName"));
                methods.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return methods;
    }
}


