package payroll.controller;

import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import payroll.model.enums.UserClass;

public class AuthenticationController {
	public UserClass m_UserClass;

	
	public AuthenticationController() {
		getAuthentication();
	}
	
	void getAuthentication() {
		JPanel loginPanel;
		String userName;
		char[] password;
		final String[] buttonText = {"Login", "Cancel"};
		final String dialogTitle = "Please Log In";
		
		// Create the elements
		JLabel userNameLabel = new JLabel("Login ID: ", JLabel.RIGHT);
		JTextField userNameField = new JTextField("");
		JLabel passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
		JPasswordField passwordField = new JPasswordField("");
		
		loginPanel = new JPanel(false);
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
		JPanel labelPanel = new JPanel(false);
		labelPanel.setLayout(new GridLayout(0,1));
		labelPanel.add(userNameLabel);
		labelPanel.add(passwordLabel);
		JPanel textPanel = new JPanel(false);
		textPanel.setLayout(new GridLayout(0,1));
		textPanel.add(userNameField);
		textPanel.add(passwordField);
		loginPanel.add(labelPanel);
		loginPanel.add(textPanel);
		
		System.out.println("Display the login Box.");
		boolean login = true;
		while(login) {
			
			int result = JOptionPane.showOptionDialog(null, loginPanel, dialogTitle, 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,	
					null, buttonText, buttonText[0]);
			
			if (result == 0)
			{
				login = false;
			
			
				userName = userNameField.getText();
				password = passwordField.getPassword();
				
				/* perform the authentication of user information and set the user class.
				 * Dummy testing checks below.*/
			
				System.out.println("Authenticate.");
				if(userName.equals("admin") && Arrays.equals(password, "pass".toCharArray()))
				{
					m_UserClass = UserClass.ADMIN;
					System.out.println("ADMIN user class.");
				}
				else if (userName.equals("user") && Arrays.equals(password, "pass".toCharArray()))
				{
					m_UserClass = UserClass.USER;
					System.out.println("USER user class.");
				}
				else {
					String error = "The log in information you provided is invalid.  Please try again.";
					JOptionPane.showMessageDialog(null, error, "Invalid Login", JOptionPane.ERROR_MESSAGE);
					login = true;  /* failed login repeats */
				}
			}
			else
				System.exit(0); /* exit if cancel pressed */
		}
			
	}
	
	public UserClass getUserClass() { return m_UserClass;}
}
