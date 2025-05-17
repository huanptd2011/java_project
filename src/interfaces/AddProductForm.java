package interfaces;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import models.Products;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class AddProductForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProductName;
	private JTextField textFieldDescription;
	private JTextField textFieldBrand;
	private JTextField textFieldPrice;
	private JTextField textFieldQuantity;
	private ProductController productController;


	public AddProductForm(Connection con) {
		this.productController = new ProductController(con);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Thêm/Sửa sản phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm");
		panel.add(lblTnSnPhm);
		
		textFieldProductName = new JTextField();
		panel.add(textFieldProductName);
		textFieldProductName.setColumns(28);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("    Mô tả sp    ");
		panel_1.add(lblNewLabel_1);
		
		textFieldDescription = new JTextField();
		panel_1.add(textFieldDescription);
		textFieldDescription.setColumns(28);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("    Hãng sp     ");
		panel_2.add(lblNewLabel_2);
		
		textFieldBrand = new JTextField();
		panel_2.add(textFieldBrand);
		textFieldBrand.setColumns(28);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("    Giá bán     ");
		panel_3.add(lblNewLabel_3);
		
		textFieldPrice = new JTextField();
		panel_3.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Số lượng    ");
		panel_3.add(lblNewLabel_4);
		
		textFieldQuantity = new JTextField();
		panel_3.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JButton btnNewButtonCancel = new JButton("Quay lại");
		panel_4.add(btnNewButtonCancel);
		
		JButton btnNewButtonClear = new JButton("Làm mới");
		panel_4.add(btnNewButtonClear);
		
		JButton btnNewButtonSave = new JButton("Lưu sp");
		panel_4.add(btnNewButtonSave);

		//action
		btnNewButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnNewButtonClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        textFieldProductName.setText("");
		        textFieldDescription.setText("");
		        textFieldBrand.setText("");
		        textFieldPrice.setText("");
		        textFieldQuantity.setText("");
		    }
		});


		btnNewButtonSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy dữ liệu từ các TextField
		        String name = textFieldProductName.getText().trim();
		        String description = textFieldDescription.getText().trim();
		        String brand = textFieldBrand.getText().trim();
		        String priceStr = textFieldPrice.getText().trim();
		        String quantityStr = textFieldQuantity.getText().trim();

		        // Kiểm tra dữ liệu nhập vào
		        if (name.isEmpty() || description.isEmpty() || brand.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sản phẩm.");
		            return;
		        }

		        double price = 0;
		        int quantity = 0;
		        try {
		            price = Double.parseDouble(priceStr);
		            quantity = Integer.parseInt(quantityStr);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Giá và số lượng phải là số hợp lệ.");
		            return;
		        }

		        // Tạo đối tượng sản phẩm
		        Products product = new Products();
		        product.setProductName(name);
		        product.setDescription(description);
		        product.setBrand(brand);
		        product.setPrice(price);
		        product.setQuantity(quantity);

		        // Gọi controller để thêm sản phẩm vào DB
//		        ProductController productController = new ProductController(); // hoặc bạn truyền từ ngoài vào
		        boolean success = productController.addProduct(product);

		        if (success) {
		            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công.");
		            setVisible(false); // đóng form sau khi thêm
		        } else {
		            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại.");
		        }
		    }
		});

	}
}
