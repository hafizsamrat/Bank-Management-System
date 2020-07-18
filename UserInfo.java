import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserInfo extends JFrame implements ActionListener
{
	private JLabel welcomeLabel,balanceLabel,nameLabel,idLabel,name,id,balance,locationLabel,location,phnLabel,phnNo;
	private JButton edit,delete,back,logoutBtn;
	private JPanel panel;
	private String userId;
	
	public UserInfo(String userId)
	{
		super("Bank Management System - User Information Window");
		
		this.userId = userId;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("My Account (Customer)");
		welcomeLabel.setBounds(10, 5, 400, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		edit = new JButton("Edit Account");
		edit.setBounds(150, 170, 200, 30);
		edit.addActionListener(this);
		panel.add(edit);
		
		back = new JButton("Back");
		back.setBounds(450, 170, 80, 30);
		back.addActionListener(this);
		panel.add(back);
		
		
		String query = "SELECT * FROM `customer` WHERE `userId`='"+userId+"';";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
						
			while(rs.next())
			{
				String names = rs.getString("customerNamer");
				String add = rs.getString("address");
				String phone = rs.getString("phoneNumber");
				String Id= rs.getString("userId");
				double bal= rs.getDouble("Balance");
				
				idLabel = new JLabel("User Id  : ");
				idLabel.setBounds(100, 250, 200, 50);
				idLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
				panel.add(idLabel);
				
				id = new JLabel(Id);
				id.setBounds(200, 250, 400, 50);
				id.setFont(new Font("Cambrige",Font.BOLD, 15));
				panel.add(id);
				
				nameLabel = new JLabel("Name     : ");
				nameLabel.setBounds(100, 300, 200, 50);
				nameLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
				panel.add(nameLabel);
				
				name = new JLabel(names);
				name.setBounds(200, 300, 400, 50);
				name.setFont(new Font("Cambrige",Font.BOLD, 15));
				panel.add(name);
				
				balanceLabel = new JLabel("Balance  : ");
				balanceLabel.setBounds(100, 350, 200, 50);
				balanceLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
				panel.add(balanceLabel);
				
				balance = new JLabel(String.format("%.3f",bal));
				balance.setBounds(200, 350, 400, 50);
				balance.setFont(new Font("Cambrige",Font.BOLD, 15));
				panel.add(balance);
				
				locationLabel = new JLabel("Location : ");
				locationLabel.setBounds(100, 400, 200, 50);
				locationLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
				panel.add(locationLabel);
				
				location = new JLabel(add);
				location.setBounds(200, 400, 400, 50);
				location.setFont(new Font("Cambrige",Font.BOLD, 15));
				panel.add(location);
				
				phnLabel = new JLabel("PhoneNo : ");
				phnLabel.setBounds(100, 450, 200, 50);
				phnLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
				panel.add(phnLabel);
				
				phnNo = new JLabel(phone);
				phnNo.setBounds(200, 450, 400, 50);
				phnNo.setFont(new Font("Cambrige",Font.BOLD, 15));
				panel.add(phnNo);
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
			
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
		else if(ae.getSource()==back)
		{
			Customer c=new Customer(userId);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==edit)
		{
			EditMyInfo c=new EditMyInfo(userId);
			c.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
}