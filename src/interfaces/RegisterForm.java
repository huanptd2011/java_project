package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConnectData;
import controller.UserController;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class RegisterForm extends JFrame {
	private boolean isShowPass = false;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		RegisterForm frame = new RegisterForm();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Đăng ký tài khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("       Tên tài khoản      ");
		panel_1.add(lblNewLabel_1);
		
		textFieldUsername = new JTextField();
		panel_1.add(textFieldUsername);
		textFieldUsername.setColumns(25);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("        Mật khẩu             ");
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
		        String username = textFieldUsername.getText();
		        String password = new String(passwordField.getPassword());

		        Connection con = ConnectData.getConnection();
		        UserController userController = new UserController(con);
		        boolean check = userController.Register(username, password);  

		        if (check == true) {
		            JOptionPane.showMessageDialog(null, "Đăng ký thành công!");

		            dispose();
                  LoginForm login = new LoginForm();
                  login.setVisible(true);
		           
		        } else {
		            JOptionPane.showMessageDialog(null, "Lỗi khi tạo tài khoản", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButtonShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isShowPass){
					passwordField.setEchoChar((char) 0);
					btnNewButtonShowPass.setText("X");
					isShowPass = true;
				} else {
					passwordField.setEchoChar('*');
					btnNewButtonShowPass.setText("O");
					isShowPass = false;
				}
			}
		});
		btnNewButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});
	}

}

