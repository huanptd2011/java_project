package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import controller.PaymentMethodController;
import controller.ProductController;
import models.Orders;
import models.PaymentMethod;
import models.Products;
import models.Users;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class OrderDetailForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldQuantity;
	private JTextField textFieldTotalAmount;
	private JTextField textFieldAddress;
	private JTextField textField;
	private Users user;
	private Products product;
	private OrderController orderc;
	private PaymentMethodController paymentMethodc;
	private ProductController productc;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		OrderDetailForm frame = new OrderDetailForm();
//		frame.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	 public OrderDetailForm(Products product, Users user, Connection con, int quantity, double totalAmount) {
	        this.product = product;
	        this.user = user;
	        this.orderc = new OrderController(con);
	        this.paymentMethodc = new PaymentMethodController(con);
	        this.productc = new ProductController(con);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Chi tiết đơn hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm");
		panel_1.add(lblNewLabel_1);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setText(product.getProductName());
		textFieldProductName.setEditable(false);
		panel_1.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Địa chỉ");
		panel_2.add(lblNewLabel_4);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setText(user.getAddress());
		panel_2.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Số lượng");
		panel_3.add(lblNewLabel_2);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setText(String.valueOf(quantity)); 
		textFieldQuantity.setEditable(false);
		panel_3.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Số điện thoại");
		panel_4.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setText(user.getPhone());
		panel_4.add(textField);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Tổng tiền");
		panel_5.add(lblNewLabel_3);
		
		textFieldTotalAmount = new JTextField();
		textFieldTotalAmount.setEditable(false);
		textFieldTotalAmount.setText(String.valueOf(totalAmount));
		panel_5.add(textFieldTotalAmount);
		textFieldTotalAmount.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Phương thức thanh toán");
		panel_6.add(lblNewLabel_6);
		
		JComboBox<PaymentMethod> comboBoxPaymentMethod = new JComboBox<>(); // Specify type for clarity
		ArrayList<PaymentMethod> paymentMethods = paymentMethodc.getAllPaymentMethods();

		// Clear any existing items in the JComboBox
		comboBoxPaymentMethod.removeAllItems();

		// Add each PaymentMethod to the JComboBox
		for (PaymentMethod method : paymentMethods) {
		    comboBoxPaymentMethod.addItem(method);
		}

		// Set a custom renderer to display only the payment method name
		comboBoxPaymentMethod.setRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        if (value instanceof PaymentMethod) {
		            setText(((PaymentMethod) value).getPaymentMethodName());
		        } else {
		            setText("Select Payment Method"); // Optional: for null/placeholder
		        }
		        return this;
		    }
		});

		// Add the JComboBox to the panel
		panel_6.add(comboBoxPaymentMethod);
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnNewButtonCancel = new JButton("Quay lại");
		panel_7.add(btnNewButtonCancel);
		
		JButton btnNewButtonRent = new JButton("Đặt hàng");
		panel_7.add(btnNewButtonRent);

		//action
		btnNewButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButtonRent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Parse input fields
		            int quantity = Integer.parseInt(textFieldQuantity.getText());
		            double totalAmount = Double.parseDouble(textFieldTotalAmount.getText());
		            PaymentMethod selectedPaymentMethod = (PaymentMethod) comboBoxPaymentMethod.getSelectedItem();
		            String address = textFieldAddress.getText();
		            String phone = textField.getText();

		            // Validate inputs
		            if (selectedPaymentMethod == null) {
		                JOptionPane.showMessageDialog(null, 
		                    "Vui lòng chọn phương thức thanh toán.", 
		                    "Lỗi", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            if (address.isEmpty() || phone.isEmpty()) {
		                JOptionPane.showMessageDialog(null, 
		                    "Vui lòng nhập đầy đủ địa chỉ và số điện thoại.", 
		                    "Lỗi", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Kiểm tra số lượng tồn kho
		            if (quantity > product.getQuantity()) {
		                JOptionPane.showMessageDialog(null, 
		                    "Số lượng đặt vượt quá số lượng tồn kho. Hiện chỉ còn " + product.getQuantity() + " sản phẩm.", 
		                    "Lỗi", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Create new order
		            Orders newOrder = new Orders();
		            newOrder.setOrderID(quantity);
		            newOrder.setUserID(user.getUserID());
		            newOrder.setOrderDate(new java.util.Date());
		            newOrder.setPhone(phone);
		            newOrder.setAddress(address);
		            newOrder.setQuantity(quantity);
		            newOrder.setProductID(product.getProductID());
		            newOrder.setOrderState("Pending");
		            newOrder.setTotalAmount(totalAmount);
		            newOrder.setPaymentMethodID(selectedPaymentMethod.getPaymentMethodID());

		            // Save to database
		            boolean orderCreated = orderc.createOrder(newOrder);

		            if (orderCreated) {
		                // Cập nhật số lượng sản phẩm trong kho
		                product.setQuantity(product.getQuantity() - quantity);
		                boolean productUpdated = productc.updateProductQuantity(product);
		                
		                if (productUpdated) {
		                    // Display success message
		                    JOptionPane.showMessageDialog(null, 
		                        "Đặt hàng thành công!\n" +
		                        "Sản phẩm: " + product.getProductName() + "\n" +
		                        "Số lượng: " + quantity + "\n" +
		                        "Tổng tiền: " + String.format("%,.0f VND", totalAmount) + "\n" +
		                        "Phương thức thanh toán: " + selectedPaymentMethod.getPaymentMethodName(),
		                        "Thành công",
		                        JOptionPane.INFORMATION_MESSAGE);
		                    
		                    dispose(); // Close the form
		                } else {
		                    JOptionPane.showMessageDialog(null, 
		                        "Đặt hàng thành công nhưng cập nhật số lượng sản phẩm thất bại", 
		                        "Cảnh báo", 
		                        JOptionPane.WARNING_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, 
		                    "Có lỗi xảy ra khi đặt hàng", 
		                    "Lỗi", 
		                    JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, 
		                "Vui lòng nhập số hợp lệ cho số lượng và tổng tiền.", 
		                "Lỗi", 
		                JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Đã xảy ra lỗi: " + ex.getMessage(), 
		                "Lỗi", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

	}

}
