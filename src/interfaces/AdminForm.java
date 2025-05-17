package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.ProductController;
import controller.UserController;
import models.Orders;
import models.Products;
import models.Users;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminForm extends JFrame {
	private JTextField textFieldSearchProduct;
	private JTable tableProducts;
	private JTable tableOrders;
	private JTextField textFieldSearchUser;
	private JTable tableUsers;
    private Users user;
    private ProductController productController;
    private OrderController orderController;
    private UserController userc;

//	public static void main(String[] args) {
//		AdminForm frame = new AdminForm();
//		frame.setVisible(true);
//	}
	private void updateOrdersTable(String state) {
	    ArrayList<Orders> orders = orderController.getAllOrders();
	    ArrayList<Orders> filteredOrders = new ArrayList<>();
	    
	    // Filter orders by state if not null
	    if (state != null) {
	        for (Orders order : orders) {
	            if (order.getOrderState().equals(state)) {
	                filteredOrders.add(order);
	            }
	        }
	    } else {
	        filteredOrders = orders;
	    }
	    
	    // Create table model
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
	    
	    tableOrders.setModel(model);
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
	private void updateUsersTable() {
	    ArrayList<Users> users = userc.getAllUsers();
	    
	    // Tên cột phù hợp với Users
	    String[] columnNames = {"User ID", "Username", "Full Name", "Email", "Phone", "Address", "Status", "Role"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    for (Users user : users) {
	        Object[] row = {
	            user.getUserID(),
	            user.getUsername(),
	            user.getFullName(),
	            user.getEmail(),
	            user.getPhone(),
	            user.getAddress(),
	            user.getStatus(),
	            user.getRole()
	        };
	        model.addRow(row);
	    }
	    
	    tableUsers.setModel(model);  // Nếu đây là bảng user, nên đặt tên là tableUsers
	}
	private void updateUsersTable(ArrayList<Users> users) {
	    // Tên cột phù hợp với Users
	    String[] columnNames = {"User ID", "Username", "Full Name", "Email", "Phone", "Address", "Status", "Role"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
	    for (Users user : users) {
	        Object[] row = {
	            user.getUserID(),
	            user.getUsername(),
	            user.getFullName(),
	            user.getEmail(),
	            user.getPhone(),
	            user.getAddress(),
	            user.getStatus(),
	            user.getRole()
	        };
	        model.addRow(row);
	    }
	    
	    tableUsers.setModel(model);  // Nếu đây là bảng user, nên đặt tên là tableUsers
	}

	public AdminForm(Users user, Connection con) {
		this.orderController = new OrderController(con);
		this.productController = new ProductController(con);
		this.userc = new UserController(con);
	    this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
        JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelOption = new JPanel();
		panelOption.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelOption, BorderLayout.WEST);
		panelOption.setLayout(new GridLayout(6, 1, 0, 20));
		
		JButton btnNewButtonProductManagerment = new JButton("Quản lý sản phẩm");
		panelOption.add(btnNewButtonProductManagerment);
		
		JButton btnNewButtonOrderManagerment = new JButton("Quản lý đơn hàng");
		panelOption.add(btnNewButtonOrderManagerment);
		
		JButton btnNewButtonUserManagerment = new JButton("Quản lý khách hàng");
		panelOption.add(btnNewButtonUserManagerment);
		
		JButton btnNewButtonLogout = new JButton("Đăng xuất");
		panelOption.add(btnNewButtonLogout);
		
		JPanel panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new CardLayout(0, 0));
		
		JPanel panelProductManagerment = new JPanel();
		panelProductManagerment.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelProductManagerment, "name_42019455860000");
		panelProductManagerment.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton = new JPanel();
		panelProductManagerment.add(panelButton, BorderLayout.NORTH);
		panelButton.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton.add(panel_4);
		
		JButton btnNewButtonAddProduct = new JButton("Thêm sản phẩm");
		panel_4.add(btnNewButtonAddProduct);
		
		JButton btnNewButtonEditProduct = new JButton("Sửa sản phẩm");
		panel_4.add(btnNewButtonEditProduct);
		
		JButton btnNewButtonDeleteProduct = new JButton("Xóa sản phẩm");
		panel_4.add(btnNewButtonDeleteProduct);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton.add(panel_3);
		
		textFieldSearchProduct = new JTextField();
		panel_3.add(textFieldSearchProduct);
		textFieldSearchProduct.setColumns(40);
		
		JButton btnNewButtonSearch = new JButton("Tìm kiếm");
		panel_3.add(btnNewButtonSearch);
		
		tableProducts = new JTable();
		panelProductManagerment.add(tableProducts);
		
		JPanel panelOrderManagerment = new JPanel();
		panelOrderManagerment.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelOrderManagerment, "name_42081510628500");
		panelOrderManagerment.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton1 = new JPanel();
		panelOrderManagerment.add(panelButton1, BorderLayout.NORTH);
		panelButton1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton1.add(panel);
//		
//		JButton btnNewButtonOrderConfirm = new JButton("Chờ xác nhận");
//		panel.add(btnNewButtonOrderConfirm);
//		
//		JButton btnNewButtonOrderDelivery = new JButton("Đang giao");
//		panel.add(btnNewButtonOrderDelivery);
//		
//		JButton btnNewButtonOrderComplete = new JButton("Đã nhận hàng");
//		panel.add(btnNewButtonOrderComplete);
//		
//		JButton btnNewButtonOrderCancel = new JButton("Đã hủy");
//		panel.add(btnNewButtonOrderCancel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton1.add(panel_1);
		
		JButton btnNewButtonConfirm = new JButton("Xác nhận đơn");
		panel_1.add(btnNewButtonConfirm);
		
		JButton btnNewButtonCancel = new JButton("Hủy đơn");
		panel_1.add(btnNewButtonCancel);
		
		tableOrders = new JTable();
		panelOrderManagerment.add(tableOrders, BorderLayout.CENTER);
		
		JPanel panelUserManagerment = new JPanel();
		panelUserManagerment.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMain.add(panelUserManagerment, "name_42107534293200");
		panelUserManagerment.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton2 = new JPanel();
		panelUserManagerment.add(panelButton2, BorderLayout.NORTH);
		panelButton2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton2.add(panel_2);
		
		textFieldSearchUser = new JTextField();
		panel_2.add(textFieldSearchUser);
		textFieldSearchUser.setColumns(40);
		
		JButton btnNewButtonSearchUser = new JButton("Tìm kiếm");
		panel_2.add(btnNewButtonSearchUser);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButton2.add(panel_5);
		
		JButton btnNewButtonUnLock = new JButton("Mở tài khoản");
		panel_5.add(btnNewButtonUnLock);
		
		JButton btnNewButtonLock = new JButton("Khóa tài khoản");
		panel_5.add(btnNewButtonLock);
		
		tableUsers = new JTable();
		panelUserManagerment.add(tableUsers, BorderLayout.CENTER);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panelTitle.add(lblNewLabel);
		
		
		//Action
		//quan ly san pham
		btnNewButtonProductManagerment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProductManagerment.setVisible(true);
				panelUserManagerment.setVisible(false);
				panelOrderManagerment.setVisible(false);
				 updateProductsTable();
			}
		});
		//quan ly don hang
		btnNewButtonOrderManagerment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProductManagerment.setVisible(false);
				panelUserManagerment.setVisible(false);
				panelOrderManagerment.setVisible(true);
				updateOrdersTable(null);
			}
		});
		
		//quan ly nguoi dung
		btnNewButtonUserManagerment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProductManagerment.setVisible(false);
				panelUserManagerment.setVisible(true);
				panelOrderManagerment.setVisible(false);
				updateUsersTable();
			}
		});
		//dang xuat
		btnNewButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});

		
		//Product Managerment action
		//them sp
		btnNewButtonAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductForm addProductForm = new AddProductForm(con);
				addProductForm.setVisible(true);
			}
		});
		//sua sp
		btnNewButtonEditProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableProducts.getSelectedRow();
		        if (selectedRow >= 0) {
		            int productId = (int) tableProducts.getValueAt(selectedRow, 0);
		            Products product = productController.getProductById(productId);
		            if (product != null) {
		                // Open the product detail form
		            	EditProductForm editProductForm = new EditProductForm(product, con);
		            	editProductForm.setVisible(true);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước");
		        }
			}
		});
		//xoa sp
		btnNewButtonDeleteProduct.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableProducts.getSelectedRow(); // Lấy dòng được chọn

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa.");
		            return;
		        }

		        // Lấy tên sản phẩm từ cột thứ 1 (cột "Product Name")
		        String productName = tableProducts.getValueAt(selectedRow, 1).toString();

		        // Hỏi xác nhận người dùng
		        int confirm = JOptionPane.showConfirmDialog(null, 
		            "Bạn có chắc chắn muốn xóa sản phẩm: " + productName + " không?", 
		            "Xác nhận xóa", 
		            JOptionPane.YES_NO_OPTION);

		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean success = productController.deleteProduct(productName);

		            if (success) {
		                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công.");
		                updateProductsTable(); // Cập nhật lại bảng sau khi xóa
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại.");
		            }
		        }
		    }
		});

		//tim sp
		btnNewButtonSearch.addActionListener(new ActionListener() {
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


//		//Order Managerment action
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
		btnNewButtonCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableOrders.getSelectedRow();
		        if (selectedRow >= 0) {
		            int orderId = (int) tableOrders.getValueAt(selectedRow, 0);
		            String status = (String) tableOrders.getValueAt(selectedRow, 2);
		            int confirm = JOptionPane.showConfirmDialog(
		                null, 
		                "Bạn có chắc chắn muốn hủy đơn hàng này?", 
		                "Xác nhận hủy đơn", 
		                JOptionPane.YES_NO_OPTION
		            );
		            if ("Canceled".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã bị hủy.");
		                return;
		            }
		            if ("Confirmed".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã giao hàng thành công, không thể hủy.");
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

		btnNewButtonConfirm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableOrders.getSelectedRow();
		        if (selectedRow >= 0) {
		            int orderId = (int) tableOrders.getValueAt(selectedRow, 0);
		            String status = (String) tableOrders.getValueAt(selectedRow, 2); // cột trạng thái
		            
		            if ("Canceled".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã bị hủy, không thể xác nhận nhận hàng.");
		                return;
		            }
		            if ("Confirmed".equalsIgnoreCase(status)) {
		                JOptionPane.showMessageDialog(null, "Đơn hàng này đã giao hàng thành công.");
		                return;
		            }
		            
		            int confirm = JOptionPane.showConfirmDialog(
		                null, 
		                "Xác nhận đã nhận hàng?", 
		                "Xác nhận", 
		                JOptionPane.YES_NO_OPTION
		            );
		            
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


		//User Managerment action
		btnNewButtonSearchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = textFieldSearchUser.getText().toLowerCase();
				 ArrayList<Users> allUsers = userc.getAllUsers();
				 ArrayList<Users> filteredUsers = new ArrayList<>();
				 for (Users user : allUsers) {
			            if (user.getUsername().toLowerCase().contains(searchText))
			                {
			                filteredUsers.add(user);
			            }
			        }
			        
			        updateUsersTable(filteredUsers);
			    }
			
		});
	
		btnNewButtonUnLock.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableUsers.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để mở khóa.");
		            return;
		        }

		        int userId = Integer.parseInt(tableUsers.getValueAt(selectedRow, 0).toString()); // cột 0 là userID

		        int confirm = JOptionPane.showConfirmDialog(null, 
		            "Bạn có chắc muốn mở khóa tài khoản này?", 
		            "Xác nhận", 
		            JOptionPane.YES_NO_OPTION);

		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean success = userc.UnLockAccount(userId);
		            if (success) {
		                JOptionPane.showMessageDialog(null, "Mở khóa tài khoản thành công.");
		                updateUsersTable();
		            } else {
		                JOptionPane.showMessageDialog(null, "Mở khóa tài khoản thất bại.");
		            }
		        }
		    }
		});

		btnNewButtonLock.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableUsers.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để khóa.");
		            return;
		        }

		        int userId = Integer.parseInt(tableUsers.getValueAt(selectedRow, 0).toString()); // cột 0 là userID

		        int confirm = JOptionPane.showConfirmDialog(null, 
		            "Bạn có chắc muốn khóa tài khoản này?", 
		            "Xác nhận", 
		            JOptionPane.YES_NO_OPTION);

		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean success = userc.lockAccount(userId);
		            if (success) {
		                JOptionPane.showMessageDialog(null, "Khóa tài khoản thành công.");
		                updateUsersTable();
		            } else {
		                JOptionPane.showMessageDialog(null, "Khóa tài khoản thất bại.");
		            }
		        }
		    }
		});

	}
}
