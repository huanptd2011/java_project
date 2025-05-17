package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import models.Orders;
import models.Products;
import models.Users;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class ProductDetailForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldDescription;
	private JTextField textFieldBrand;
	private JTextField textFieldPrice;
	private JTextField textFieldQuantity;
	private JTextField textFieldCount;
	private Orders order;
	private OrderController orderController;
	private Users currentUser;
	private Products currentProduct;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Products product = new Products();
//		ProductDetailForm frame = new ProductDetailForm(product);
//		frame.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	public ProductDetailForm(Products product, Users user, Connection con) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.EAST);

		JButton btnNewButtonDecrement = new JButton("-");
		panel_6.add(btnNewButtonDecrement);

		textFieldCount = new JTextField();
		textFieldCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCount.setText("1");
		panel_6.add(textFieldCount);
		textFieldCount.setEditable(false);
		textFieldCount.setColumns(2);

		JButton btnNewButtonIncrement = new JButton("+");
		panel_6.add(btnNewButtonIncrement);

		JButton btnNewButtonRent = new JButton("Đặt hàng");
		panel_6.add(btnNewButtonRent);

		JPanel panel_7 = new JPanel();
		panel.add(panel_7, BorderLayout.WEST);

		JButton btnNewButtonCancel = new JButton("Quay lại");
		panel_7.add(btnNewButtonCancel);


		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("Tên sản phẩm"); 		
		panel_2.add(lblNewLabel_1);  		
		textFieldProductName = new JTextField(); 		
		textFieldProductName.setText(product.getProductName()); 		
		textFieldProductName.setEditable(false); // Không cho chỉnh sửa
		panel_2.add(textFieldProductName); 		
		textFieldProductName.setColumns(30);  

		JPanel panel_3 = new JPanel(); 		
		panel_1.add(panel_3); 		
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));  		

		JLabel lblNewLabel_2 = new JLabel("    Mô tả sp    "); 		
		panel_3.add(lblNewLabel_2);  		

		textFieldDescription = new JTextField(); 		
		textFieldDescription.setText(product.getDescription()); 		
		textFieldDescription.setEditable(false); 
		panel_3.add(textFieldDescription); 		
		textFieldDescription.setColumns(30);  

		JPanel panel_4 = new JPanel(); 		
		panel_1.add(panel_4); 		
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));  		

		JLabel lblNewLabel_3 = new JLabel("    Hãng sp     "); 		
		panel_4.add(lblNewLabel_3);  		

		textFieldBrand = new JTextField(); 		
		textFieldBrand.setText(product.getBrand()); 		
		textFieldBrand.setEditable(false); 
		panel_4.add(textFieldBrand); 		
		textFieldBrand.setColumns(30);  

		JPanel panel_5 = new JPanel(); 		
		panel_1.add(panel_5); 		
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));  		

		JLabel lblNewLabel_4 = new JLabel("    Giá bán     "); 		
		panel_5.add(lblNewLabel_4);  		

		textFieldPrice = new JTextField(); 		
		textFieldPrice.setText(String.valueOf(product.getPrice())); 		
		textFieldPrice.setEditable(false); 
		panel_5.add(textFieldPrice); 		
		textFieldPrice.setColumns(10);  

		JLabel lblNewLabel_5 = new JLabel("       Số lượng    "); 		
		panel_5.add(lblNewLabel_5);  		

		textFieldQuantity = new JTextField(); 		
		textFieldQuantity.setText(String.valueOf(product.getQuantity())); 		
		textFieldQuantity.setEditable(false);
		panel_5.add(textFieldQuantity); 		
		textFieldQuantity.setColumns(10);

		JLabel lblNewLabel = new JLabel("Chi Tiết Sản Phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		btnNewButtonCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Close this form
		    }
		});
		btnNewButtonDecrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count  = Integer.parseInt(textFieldCount.getText());
				if(count > 1){
					count--;
					textFieldCount.setText(Integer.toString(count));
				}
			}
		});
		btnNewButtonRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonIncrement.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int count = Integer.parseInt(textFieldCount.getText());
		        int availableQuantity = Integer.parseInt(textFieldQuantity.getText());
		        
		        if (count < availableQuantity) {
		            count++;
		            textFieldCount.setText(Integer.toString(count));
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Không thể thêm, đã đạt số lượng tối đa (" + availableQuantity + ")",
		                "Thông báo",
		                JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		// In the btnNewButtonRent action listener
		btnNewButtonRent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int quantityToOrder = Integer.parseInt(textFieldCount.getText());
		        int availableQuantity = Integer.parseInt(textFieldQuantity.getText());
		        double price = Double.parseDouble(textFieldPrice.getText());
		        double totalAmount = quantityToOrder * price;
		        
		        if (quantityToOrder > availableQuantity) {
		            JOptionPane.showMessageDialog(null, 
		                "Số lượng đặt hàng vượt quá số lượng có sẵn",
		                "Lỗi",
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        dispose(); // Close the product detail form
		        // Pass the quantity and total amount to OrderDetailForm
		        OrderDetailForm odf = new OrderDetailForm(product, user, con, quantityToOrder, totalAmount);
		        odf.setVisible(true);
		    }
		});
	}
}


