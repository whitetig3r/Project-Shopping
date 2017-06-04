package Shopping;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginPane extends JPanel {
	private JTextField Userl;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPane() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("PingFang TC", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(207, 18, 138, 25);
		add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(144, 102, 93, 16);
		add(lblUsername);
		
		JLabel lblInc = new JLabel("");
		lblInc.setForeground(Color.RED);
		lblInc.setHorizontalAlignment(SwingConstants.CENTER);
		lblInc.setBounds(183, 260, 194, 16);
		add(lblInc);

		
		Userl= new JTextField();
		Userl.setBounds(144, 130, 268, 26);
		add(Userl);
		Userl.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(144, 187, 93, 16);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 215, 268, 26);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user=Userl.getText();
	            String password=passwordField.getText();
				
				if(SQLChecker.checkAcct(user,password))
				{
					ShopHome s2=new ShopHome();
					Main.frame.dispose();
					s2.setVisible(true);
				}
				else
				{
					lblInc.setText("Incorrect Username/Password!");
					Userl.setText("");
					passwordField.setText("");
				}
			}
		});
		btnLogin.setBounds(222, 288, 117, 29);
		add(btnLogin);
		
	
	}
}

class SQLChecker{
	public static boolean checkAcct(String usernamem,String passwordm){
		boolean val=true;
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
			String selectSQL = new String("SELECT Username,Password FROM CustomersDB where Username=? AND Password=?");
			PreparedStatement preparedStatement;
		    preparedStatement = connection.prepareStatement(selectSQL);              
		                preparedStatement.setString(1, usernamem);
		                preparedStatement.setString(2, passwordm);

		  ResultSet rs = preparedStatement.executeQuery();
            
          val=rs.next();
	     }
		  catch (SQLException e) {
			e.printStackTrace();
		}
	    return val;
	}
}
