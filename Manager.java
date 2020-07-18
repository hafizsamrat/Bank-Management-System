import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Manager extends JFrame implements ActionListener
{
	private JLabel welcomeLabel;
	private JButton manageEmployeeBtn, viewDetailsBtn, logoutBtn,customerBtn,customer,employee;
	private JPanel panel;
	private String userId;
	
	public Manager(String userId)
	{
		super("Bank Management System - Manager Home Window");
		
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
		
		customerBtn = new JButton("Add Customers");
		customerBtn.setBounds(200, 170, 150, 30);
		customerBtn.addActionListener(this);
		panel.add(customerBtn);
		
		manageEmployeeBtn = new JButton("Add Employees");
		manageEmployeeBtn.setBounds(400, 170, 150, 30);
		manageEmployeeBtn.addActionListener(this);
		panel.add(manageEmployeeBtn);
		
		customer = new JButton("View Customers");
		customer.setBounds(200, 230, 150, 30);
		customer.addActionListener(this);
		panel.add(customer);
		
		employee = new JButton("view Employees");
		employee.setBounds(400, 230, 150, 30);
		employee.addActionListener(this);
		panel.add(employee);
		
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
			ManagerInfo m=new ManagerInfo(userId);
			m.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==customerBtn)
		{
			AddCustomerM a= new AddCustomerM(userId);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==manageEmployeeBtn)
		{
			AddEmployee a=new AddEmployee(userId);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==customer)
		{
			CustomerView c=new CustomerView(userId,2);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==employee)
		{
			EmployeeView e=new EmployeeView(userId);
			e.setVisible(true);
			this.setVisible(false);
		}
	}
}