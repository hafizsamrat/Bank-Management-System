import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Employee extends JFrame implements ActionListener
{
	private JLabel welcomeLabel;
	private JButton manageEmployeeBtn, viewDetailsBtn, logoutBtn,viewCustomer;
	private JPanel panel;
	private String userId;
	
	public Employee(String userId)
	{
		super("Bank Management System - Employee Home Window");
		
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
		
		manageEmployeeBtn = new JButton("Add Customer");
		manageEmployeeBtn.setBounds(200, 170, 150, 30);
		manageEmployeeBtn.addActionListener(this);
		panel.add(manageEmployeeBtn);
		
		viewCustomer = new JButton("View Customers");
		viewCustomer.setBounds(400, 170, 150, 30);
		viewCustomer.addActionListener(this);
		panel.add(viewCustomer);
		
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
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==viewDetailsBtn)
		{
			EmployeeInfo e=new EmployeeInfo(userId);
			e.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==manageEmployeeBtn)
		{
			AddCustomer a= new AddCustomer(userId);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==viewCustomer)
		{
			CustomerView c=new CustomerView(userId,0);
			c.setVisible(true);
			this.setVisible(false);
		}
	}
	
}