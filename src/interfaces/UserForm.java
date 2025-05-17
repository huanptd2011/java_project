package interfaces;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ConnectData;
import controller.OrderController;
import controller.ProductController;
import controller.UserController;
import models.Orders;
import models.Products;
import models.Users;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.event.ActionEvent;

public class UserForm extends JFrame {

	private boolean isShowOldPass = false;
	private boolean isShowNewPass = false;
	private boolean isShowConfirmNewPass = false;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearchProduct;
	private JTable tableProducts;
	private JTable table;
	private JTextField textFieldUsername;
	private JTextField textFieldFullName;
	private JTextField textFieldEmail;
	private JTextField textFieldAddress;
	private JTextField textFieldPhone;
	private JPasswordField passwordFieldOld;
	private JPasswordField passwordFieldNew;
	private JPasswordField passwordFieldConfirmNew;
    private Users user;
    private ProductController productController;
    private OrderController orderController;
    private UserController userc;

	private void updateOrdersTable(String state) {
	    ArrayList<Orders> orders = orderController.getOrdersByUserId(user.getUserID());
	    ArrayList<Orders> filteredOrders = new ArrayList<>();
	    if (state != null) {
	        for (Orders order : orders) {
	            if (order.getOrderState().equals(state)) {
	                filteredOrders.add(order);
	            }
	        }
	    } else {
	        filteredOrders = orders;
	    }
	    String[] columnNames = {"Order ID", "Order Date", "Status", "Total Amount","Quantity"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    for (Orders order : filteredOrders) {
	        Object[] row = {
	            order.getOrderID(),
	            order.getOrderDate(),
	            order.getOrderState(),
	            order.getTotalAmount(),
	            order.getQuantity(),
	        };
	        model.addRow(row);
	    }
	    
	    table.setModel(model);
	}
	private void updateProductsTable(ArrayList<Products> products) {
	    String[] columnNames = {"ID", "Product Name", "Description", "Brand","Price:", "Quantity"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    for (Products product : products) {
	        Object[] row = {
	            product.getProductID(),
	            product.getProductName(),
	            product.getDescription(),
	            product.getBrand(),
	            product.getPrice(),
	            product.getQuantity()
	        };
	        model.addRow(row);
	    }
	    
	    tableProducts.setModel(model);
	}

	private void updateProductsTable() {
	    ArrayList<Products> products = productController.getAllProduct();
	    
	    // Create table model
	    String[] columnNames = {"ID", "Product Name", "Description", "Brand","Price", "Quantity"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    for (Products product : products) {
	        Object[] row = {
	            product.getProductID(),
	            product.getProductName(),
	            product.getDescription(),
	            product.getBrand(),
	            product.getPrice(),
	            product.getQuantity()
	        };
	        model.addRow(row);
	    }
	    
	    tableProducts.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public UserForm(Users user, Connection con) {
		this.orderController = new OrderController(con);
		this.productController = new ProductController(con);
		this.userc = new UserController(con);
	    this.user = user;
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Xin chào quý khách");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelTitle.add(lblNewLabel);
		
		JPanel panelOption = new JPanel();
		panelOption.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelOption, BorderLayout.WEST);
		panelOption.setLayout(new GridLayout(9, 1, 0, 10));
		
		JButton btnNewButtonHome = new JButton("Trang chủ");
		panelOption.add(btnNewButtonHome);
		
		JButton btnNewButtonMyOrder = new JButton("Đơn hàng của tôi");
		panelOption.add(btnNewButtonMyOrder);
		
		JButton btnNewButtonProfile = new JButton("Thông tin cá nhân");
		panelOption.add(btnNewButtonProfile);
		
		JButton btnNewButtonChangePass = new JButton("Đổi mật khẩu");
		panelOption.add(btnNewButtonChangePass);
		
		JButton btnNewButtonLock = new JButton("Khóa tài khoản");
		panelOption.add(btnNewButtonLock);
		
		JButton btnNewButtonLogout = new JButton("Đăng xuất");
		panelOption.add(btnNewButtonLogout);
		
		JPanel panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new CardLayout(0, 0));
		
		JPanel panelHome = new JPanel();
		panelHome.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelHome, "name_48751060787700");
		panelHome.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelHome.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1);
		
		textFieldSearchProduct = new JTextField();
		panel_1.add(textFieldSearchProduct);
		textFieldSearchProduct.setColumns(40);
		
		JButton btnNewButtonSearchProduct = new JButton("Tìm kiếm");
		panel_1.add(btnNewButtonSearchProduct);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		
		JButton btnNewButtonProductDetail = new JButton("Chi tiết sản phẩm");
		panel_2.add(btnNewButtonProductDetail);
		
		JComboBox comboBoxFillter = new JComboBox();
		comboBoxFillter.setModel(new DefaultComboBoxModel(new String[] {"Giá thấp đến cao", "Giá cao đến thấp"}));
		panel_2.add(comboBoxFillter);
		
		tableProducts = new JTable();
		panelHome.add(tableProducts, BorderLayout.CENTER);
		
		JPanel panelMyOrder = new JPanel();
		panelMyOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelMyOrder, "name_48751081286700");
		panelMyOrder.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panelMyOrder.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel_4);
//		
//		JButton btnNewButtonOrderConfirm = new JButton("Chờ xác nhận");
//		panel_4.add(btnNewButtonOrderConfirm);
//		
//		JButton btnNewButtonOrderDelivery = new JButton("Đang giao");
//		panel_4.add(btnNewButtonOrderDelivery);
//		
//		JButton btnNewButtonOrderComplete = new JButton("Đã giao");
//		panel_4.add(btnNewButtonOrderComplete);
//		
//		JButton btnNewButtonOrderCancel = new JButton("Đã hủy");
//		panel_4.add(btnNewButtonOrderCancel);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JButton btnNewButtonCancel = new JButton("Hủy đơn");
		panel_5.add(btnNewButtonCancel);
		
		JButton btnNewButtonComplete = new JButton("Nhận hàng");
		panel_5.add(btnNewButtonComplete);
		
		table = new JTable();
		panelMyOrder.add(table, BorderLayout.CENTER);
		JPanel panelProfile = new JPanel();
		panelProfile.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelProfile, "name_48751093078000");
		panelProfile.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panelProfile.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("   Tên người dùng   ");
		panel_8.add(lblNewLabel_2);

		textFieldUsername = new JTextField();
		textFieldUsername.setText(user.getUsername());
		textFieldUsername.setEditable(false); // Không cho sửa
		panel_8.add(textFieldUsername);
		textFieldUsername.setColumns(40);

		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_3 = new JLabel("       Họ và tên        ");
		panel_9.add(lblNewLabel_3);

		textFieldFullName = new JTextField();
		textFieldFullName.setText(user.getFullName());
		panel_9.add(textFieldFullName);
		textFieldFullName.setColumns(40);

		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_4 = new JLabel("          Email           ");
		panel_10.add(lblNewLabel_4);

		textFieldEmail = new JTextField();
		textFieldEmail.setText(user.getEmail());
		textFieldEmail.setEditable(false); // Không cho sửa
		panel_10.add(textFieldEmail);
		textFieldEmail.setColumns(40);

		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_5 = new JLabel("         Địa chỉ          ");
		panel_11.add(lblNewLabel_5);

		textFieldAddress = new JTextField();
		textFieldAddress.setText(user.getAddress());
		panel_11.add(textFieldAddress);
		textFieldAddress.setColumns(40);

		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_6 = new JLabel("    Số điện thoại     ");
		panel_12.add(lblNewLabel_6);

		textFieldPhone = new JTextField();
		textFieldPhone.setText(user.getPhone());
		panel_12.add(textFieldPhone);
		textFieldPhone.setColumns(40);

		
		JLabel lblNewLabel_1 = new JLabel("Thông tin cá nhân");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelProfile.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panelProfile.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnNewButtonEditInfo = new JButton("Chỉnh sửa thông tin");
		panel_7.add(btnNewButtonEditInfo);
		
		JButton btnNewButtonSaveInfo = new JButton("Lưu thông tin");
		panel_7.add(btnNewButtonSaveInfo);
		
		JPanel panelChangePass = new JPanel();
		panelChangePass.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelChangePass, "name_48751104678700");
		panelChangePass.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("  Đổi mật khẩu");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelChangePass.add(lblNewLabel_7);
		
		JPanel panel_13 = new JPanel();
		panelChangePass.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_8 = new JLabel("       Mật khẩu cũ       ");
		panel_13.add(lblNewLabel_8);
		
		passwordFieldOld = new JPasswordField();
		passwordFieldOld.setColumns(30);
		panel_13.add(passwordFieldOld);
		
		JButton btnNewButtonShowOldPass = new JButton("O");
		panel_13.add(btnNewButtonShowOldPass);
		
		JPanel panel_14 = new JPanel();
		panelChangePass.add(panel_14);
		panel_14.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_9 = new JLabel("      Mật khẩu mới       ");
		panel_14.add(lblNewLabel_9);
		
		passwordFieldNew = new JPasswordField();
		passwordFieldNew.setColumns(30);
		panel_14.add(passwordFieldNew);
		
		JButton btnNewButtonShowNewPass = new JButton("O");
		panel_14.add(btnNewButtonShowNewPass);
		
		JPanel panel_15 = new JPanel();
		panelChangePass.add(panel_15);
		panel_15.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_10 = new JLabel("Nhập lại mật khẩu mới");
		panel_15.add(lblNewLabel_10);
		
		passwordFieldConfirmNew = new JPasswordField();
		passwordFieldConfirmNew.setColumns(30);
		panel_15.add(passwordFieldConfirmNew);
		
		JButton btnNewButtonShowConfirmNewPass = new JButton("O");
		panel_15.add(btnNewButtonShowConfirmNewPass);
		
		JPanel panel_16 = new JPanel();
		panelChangePass.add(panel_16);
		
		JButton btnNewButtonSavePass = new JButton("Lưu thay đổi");
		panel_16.add(btnNewButtonSavePass);


		//Action
		btnNewButtonHome.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        panelMyOrder.setVisible(false);
		        panelProfile.setVisible(false);
		        panelChangePass.setVisible(false);
		        panelHome.setVisible(true);
		        updateProductsTable(); // Load products when home is clicked
		    }
		});
		btnNewButtonMyOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        panelMyOrder.setVisible(true);
		        panelProfile.setVisible(false);
		        panelChangePass.setVisible(false);
		        panelHome.setVisible(false);
		        updateOrdersTable(null); // Show all orders initially
		    }
		});
		btnNewButtonProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMyOrder.setVisible(false);
				panelProfile.setVisible(true);
				panelChangePass.setVisible(false);
				panelHome.setVisible(false);

				textFieldFullName.setEditable(false);
				textFieldAddress.setEditable(false);
				textFieldPhone.setEditable(false);
			}
		});
		btnNewButtonChangePass.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        panelMyOrder.setVisible(false);
		        panelProfile.setVisible(false);
		        panelChangePass.setVisible(true);
		        panelHome.setVisible(false);
		        
		        // Xóa các trường nhập liệu khi chuyển sang tab đổi mật khẩu
		        passwordFieldOld.setText("");
		        passwordFieldNew.setText("");
		        passwordFieldConfirmNew.setText("");
		    }
		});

		// Thêm sự kiện cho nút lưu mật khẩu mới
		btnNewButtonSavePass.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String oldPass = new String(passwordFieldOld.getPassword());
		        String newPass = new String(passwordFieldNew.getPassword());
		        String confirmPass = new String(passwordFieldConfirmNew.getPassword());
		        
		        // 1. Kiểm tra các trường không được trống
		        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", 
		                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 2. Kiểm tra mật khẩu mới và xác nhận khớp nhau
		        if (!newPass.equals(confirmPass)) {
		            JOptionPane.showMessageDialog(null, "Mật khẩu mới và xác nhận không khớp", 
		                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 3. Kiểm tra mật khẩu mới không trùng mật khẩu cũ
		        if (oldPass.equals(newPass)) {
		            JOptionPane.showMessageDialog(null, "Mật khẩu mới phải khác mật khẩu cũ", 
		                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // 4. Xác thực mật khẩu cũ
		        try {
		            // Giả sử có phương thức checkOldPassword trong UserController
		            UserController userController = new UserController(con);
		            if (!userController.checkOldPassword(user.getUserID(), oldPass)) {
		                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác", 
		                                            "Lỗi", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            // 5. Cập nhật mật khẩu mới
		            if (userController.changePassword(user.getUserID(), newPass)) {
		                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", 
		                                            "Thành công", JOptionPane.INFORMATION_MESSAGE);
		                // Xóa các trường nhập liệu sau khi đổi thành công
		                passwordFieldOld.setText("");
		                passwordFieldNew.setText("");
		                passwordFieldConfirmNew.setText("");
		            } else {
		                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại", 
		                                            "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi hệ thống", 
		                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButtonLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						null,
						"Bạn chắc chắn muốn khóa tài khoản?",
						"Xác nhận",
						JOptionPane.YES_NO_OPTION
				);

				if (result == JOptionPane.YES_OPTION) {
					userc.lockAccount(user.getUserID());
					JOptionPane.showConfirmDialog(null, "Locked");
				}
			}
		});
		btnNewButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});


		btnNewButtonSearchProduct.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchText = textFieldSearchProduct.getText().toLowerCase();
		        ArrayList<Products> allProducts = productController.getAllProduct();
		        ArrayList<Products> filteredProducts = new ArrayList<>();
		        
		        for (Products product : allProducts) {
		            if (product.getProductName().toLowerCase().contains(searchText)
		               ) {
		                filteredProducts.add(product);
		            }
		        }
		        
		        updateProductsTable(filteredProducts);
		    }
		});

		// Overloaded method for filtered products
		
		btnNewButtonProductDetail.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableProducts.getSelectedRow();
		        if (selectedRow >= 0) {
		            int productId = (int) tableProducts.getValueAt(selectedRow, 0);
		            Products product = productController.getProductById(productId);
		            if (product != null) {
		                // Open the product detail form
		                ProductDetailForm detailForm = new ProductDetailForm(product,user, con);
		                detailForm.setVisible(true);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước");
		        }
		    }
		});

		//My order action
		//get order
//		btnNewButtonOrderConfirm.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButtonOrderDelivery.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButtonOrderComplete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButtonOrderCancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		//huy don
		btnNewButtonCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            int orderId = (int) table.getValueAt(selectedRow, 0);
		            String status = (String) table.getValueAt(selectedRow, 2); // cột trạng thái
		            int confirm = JOptionPane.showConfirmDialog(
		                null, 
		                "Bạn có chắc chắn muốn hủy đơn hàng này?", 
		                "Xác nhận hủy đơn", 
		                JOptionPane.YES_NO_OPTION
		            );
		            if ("Canceled".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã bị hủy, không thể xác nhận nhận hàng.");
		                return;
		            }
		            if ("Confirmed".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã giao hàng thành công.");
		                return;
		            }
		            
		            if (confirm == JOptionPane.YES_OPTION) {
		                boolean success = orderController.cancelOrder(orderId);
		                if (success) {
		                    JOptionPane.showMessageDialog(null, "Đã hủy đơn hàng thành công!");
		                    updateOrdersTable(null); // Refresh the table
		                } else {
		                    JOptionPane.showMessageDialog(null, "Hủy đơn hàng thất bại!");
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn hàng cần hủy");
		        }
		    }
		});

		btnNewButtonComplete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            int orderId = (int) table.getValueAt(selectedRow, 0);
		            String status = (String) table.getValueAt(selectedRow, 2); // cột trạng thái
		            int confirm = JOptionPane.showConfirmDialog(
		                null, 
		                "Xác nhận đã nhận hàng?", 
		                "Xác nhận", 
		                JOptionPane.YES_NO_OPTION
		            );
		            
	
		            
		            if ("Canceled".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã bị hủy, không thể xác nhận nhận hàng.");
		                return;
		            }
		            if ("Confirmed".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã giao hàng thành công.");
		                return;
		            }
		            
		            if (confirm == JOptionPane.YES_OPTION) {
		                boolean success = orderController.confirmOrder(orderId);
		                if (success) {
		                    JOptionPane.showMessageDialog(null, "Đã xác nhận nhận hàng thành công!");
		                    updateOrdersTable(null); // Refresh the table
		                } else {
		                    JOptionPane.showMessageDialog(null, "Xác nhận thất bại!");
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn hàng đã nhận");
		        }
		    }
		});



		//profile action
		//mo edit
		btnNewButtonEditInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFullName.setEditable(true);
				textFieldAddress.setEditable(true);
				textFieldPhone.setEditable(true);
				textFieldEmail.setEditable(true);
				textFieldUsername.setEditable(true); 

				
			}
		});
		btnNewButtonSaveInfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin từ các trường nhập liệu
		        String fullName = textFieldFullName.getText();
		        String email = textFieldEmail.getText();
		        String address = textFieldAddress.getText();
		        String phone = textFieldPhone.getText();
		        String username = textFieldUsername.getText();
		        
		        // Kiểm tra dữ liệu hợp lệ (có thể thêm các kiểm tra khác tùy nhu cầu)
		        if (fullName.isEmpty() || email.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        // Cập nhật thông tin vào đối tượng user
		        user.setFullName(fullName);
		        user.setEmail(email);
		        user.setAddress(address);
		        user.setPhone(phone);
		        user.getUsername();
		        
		        // Gọi phương thức updateInfo để lưu vào CSDL
		        boolean isUpdated = userc.updateInfo(user);
		        
		        if (isUpdated) {
		            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
		            // Khóa các trường sau khi lưu thành công
		            textFieldFullName.setEditable(false);
		            textFieldEmail.setEditable(false);
		            textFieldAddress.setEditable(false);
		            textFieldPhone.setEditable(false);
		            textFieldUsername.setEditable(false);
		        } else {
		            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		//change pass action
		//hien thi pass
		btnNewButtonShowOldPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isShowOldPass) {
					btnNewButtonShowOldPass.setText("X");
					passwordFieldOld.setEchoChar((char) 0);
					isShowOldPass = true;
				} else {
					btnNewButtonShowOldPass.setText("O");
					passwordFieldOld.setEchoChar('*');
					isShowOldPass = false;
				}
			}
		});
		btnNewButtonShowNewPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isShowNewPass) {
					btnNewButtonShowNewPass.setText("X");
					passwordFieldNew.setEchoChar((char) 0);
					isShowNewPass = true;
				} else {
					btnNewButtonShowNewPass.setText("O");
					passwordFieldNew.setEchoChar('*');
					isShowNewPass = false;
				}
			}
		});
		comboBoxFillter.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedFilter = (String) comboBoxFillter.getSelectedItem();
		        ArrayList<Products> products = productController.getAllProduct();
		        
		        if (selectedFilter.equals("Giá thấp đến cao")) {
		            products.sort(Comparator.comparingDouble(Products::getPrice));
		        } else if (selectedFilter.equals("Giá cao đến thấp")) {
		            products.sort(Comparator.comparingDouble(Products::getPrice).reversed());
		        }
		        
		        updateProductsTable(products);
		    }
		});
		btnNewButtonShowConfirmNewPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isShowConfirmNewPass) {
					btnNewButtonShowConfirmNewPass.setText("X");
					passwordFieldConfirmNew.setEchoChar((char) 0);
					isShowConfirmNewPass = true;
				} else {
					btnNewButtonShowConfirmNewPass.setText("O");
					passwordFieldConfirmNew.setEchoChar('*');
					isShowConfirmNewPass = false;
				}
			}
		});
		//luu pass
		btnNewButtonSavePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
