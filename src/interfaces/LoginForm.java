package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConnectData;
import controller.UserController;
import models.Users;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {
	private boolean isShowPass = false;

	private JPanel contentPane;
	private JTextField textFieldUsernameEmail;
	private JPasswordField passwordField;

	public static void main(String[] args){
		LoginForm frame = new LoginForm();
		frame.setVisible(true);
	}
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("              Username          ");
		panel_1.add(lblNewLabel_1);
		
		textFieldUsernameEmail = new JTextField();
		panel_1.add(textFieldUsernameEmail);
		textFieldUsernameEmail.setColumns(25);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("               Password          ");
		panel_2.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panel_2.add(passwordField);
		
		JButton btnNewButtonShowPass = new JButton("O");
		panel_2.add(btnNewButtonShowPass);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton btnNewButtonLogin = new JButton("Đăng nhập");
		panel_3.add(btnNewButtonLogin);
		
		JButton btnNewButtonRegister = new JButton("Đăng ký");
		panel_3.add(btnNewButtonRegister);

		//action
		btnNewButtonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new RegisterForm().setVisible(true);
			}
		});
		btnNewButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButtonShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isShowPass) {
					btnNewButtonShowPass.setText("X");
					passwordField.setEchoChar((char) 0);
					isShowPass = true;
				} else {
					btnNewButtonShowPass.setText("O");
					passwordField.setEchoChar('*');
					isShowPass = false;
				}
			}
		});
		
		btnNewButtonLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = textFieldUsernameEmail.getText();
		        String password = new String(passwordField.getPassword());

		        Connection con = ConnectData.getConnection();
		        UserController userController = new UserController(con);
		        Users user = userController.login(username, password);  
                
		        if (user != null) {
		        	if(user.getStatus() == 0) {
	                	JOptionPane.showMessageDialog(null, "Tài khooản đã bị khóa!");
	                    return;
	                }
		            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");

		            dispose();
		            if ("admin".equalsIgnoreCase(user.getRole())) {
		                new AdminForm(user,con).setVisible(true);
		            } else {
		                new UserForm(user,con).setVisible(true);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

}
}


