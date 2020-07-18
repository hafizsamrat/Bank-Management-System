import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Customer extends JFrame implements ActionListener
{
	private JLabel welcomeLabel;
	private JButton transactionBtn,viewDetailsBtn,logoutBtn;
	private JPanel panel;
	private String userId;
	
	public Customer(String userId)
	{
		super("Bank Management System - Customer Home Window");
		
		this.userId = userId;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("Welcome , "+userId);
		welcomeLabel.setBounds(300, 50, 400, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		transactionBtn = new JButton("View Transactions");
		transactionBtn.setBounds(300, 200, 150, 30);
		transactionBtn.addActionListener(this);
		panel.add(transactionBtn);
		
		viewDetailsBtn = new JButton("My Information");
		viewDetailsBtn.setBounds(300, 300, 150, 30);
		viewDetailsBtn.addActionListener(this);
		panel.add(viewDetailsBtn);
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==logoutBtn)
		{
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==viewDetailsBtn)
		{
			UserInfo u=new UserInfo(userId);
			u.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==transactionBtn)
		{
			CustomerViewTransaction c=new CustomerViewTransaction(userId);
			c.setVisible(true);
			this.setVisible(false);
		}
	}
}