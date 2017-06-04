package Shopping;

import java.awt.EventQueue;
import javax.swing.text.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.awt.Color;

public class Main{

	public static JFrame frame;
	public static JTextField Usernamer;
	public static JPasswordField passwordr;
	public static JPasswordField cpasswordr;
	public static JFormattedTextField PhoneNo;
	public static String usernamem,passwordm,phonem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Main() {
		initialize();
	}
	private void initialize(){
		frame = new JFrame();
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 532, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel RegPanel = new JPanel();
		frame.getContentPane().add(RegPanel, BorderLayout.CENTER);
		RegPanel.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to The Shopping App!");
		lblWelcomeToThe.setBounds(172, 6, 199, 16);
		RegPanel.add(lblWelcomeToThe);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(174, 59, 66, 16);
		RegPanel.add(lblUsername);
		
		Usernamer = new JTextField();
		Usernamer.setBounds(172, 87, 189, 26);
		RegPanel.add(Usernamer);
		Usernamer.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(174, 129, 66, 16);
		RegPanel.add(lblPassword);
		
		passwordr = new JPasswordField();
		passwordr.setBounds(174, 157, 189, 26);
		RegPanel.add(passwordr);
		
		
		
		JLabel lblRegister = new JLabel("Register!");
		lblRegister.setFont(new Font("PingFang TC", Font.PLAIN, 14));
		lblRegister.setBounds(232, 31, 73, 16);
		RegPanel.add(lblRegister);
		
		PhoneNo = new JFormattedTextField();
		PhoneNo.setBounds(172, 223, 189, 26);
		RegPanel.add(PhoneNo);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernamem=Usernamer.getText();
				    passwordm=passwordr.getText();
				 phonem=PhoneNo.getText();
				 Customer.makeAcct(usernamem, passwordm, phonem);
			  ShopHome s1=new ShopHome();
        	  s1.setVisible(true);
			  frame.dispose();
			}
		});
		btnRegister.setBounds(203, 272, 123, 29);
		RegPanel.add(btnRegister);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setBounds(172, 195, 133, 16);
		
        RegPanel.add(lblMobileNumber);
        
        JLabel lblExistingUser = new JLabel("Existing User?");
        lblExistingUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblExistingUser.setBounds(191, 324, 146, 16);
        RegPanel.add(lblExistingUser);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.remove(RegPanel);
        		frame.setContentPane(new LoginPane());
        		frame.repaint();
        		frame.revalidate();
        		
        		}
        });
        btnNewButton.setBounds(203, 341, 123, 29);
        RegPanel.add(btnNewButton);
        
        
		RegPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblWelcomeToThe, lblUsername, Usernamer, lblPassword, passwordr, lblRegister}));
	}
}
class Customer
{
	public static void makeAcct(String usernamem,String passwordm,String phonem){
		String driverName = "com.mysql.jdbc.Driver";
	    try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	    String serverName = "localhost";
	    String mydatabase = "Visitors";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 

	    String username = "root";
	    String password = "";
	  
	    try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `CustomersDB`(Username,Password,MobileNo) VALUE (?,?,?)");
			pstmt.setString(1,usernamem);
			pstmt.setString(2,passwordm);
			pstmt.setString(3,phonem);
			pstmt.executeUpdate();
	        System.out.println("SUCCESS!");
	         connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
