import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerViewTransaction extends JFrame implements ActionListener
{
	private JLabel welcomeLabel,balance;
	private JButton back,logoutBtn;
	private JPanel panel;
	private String userId;
	private JTable myTable;
	private JScrollPane tableScrollPane;
	private int sz=0;
	
	public CustomerViewTransaction(String userId)
	{
		super("Bank Management System - View Transactions Window");
		
		this.userId = userId;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("View Transactions");
		welcomeLabel.setBounds(10, 5, 200, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		balance= new JLabel("Balance : 0");
		balance.setBounds(60,50,400,50);
		balance.setFont(new Font("Cambria",Font.BOLD, 25));
		panel.add(balance);
		
		balan();
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 80, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		back = new JButton("Back");
		back.setBounds(500, 50, 80, 30);
		back.addActionListener(this);
		panel.add(back);
		
		Size();
		String [][] s=new String[sz][6];
		String col[] = {"Account No","Type","Sender","Receiver","Amount","Date"};
		
		
		String query = "SELECT * FROM transaction WHERE userId='"+userId+"';";     
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
			
			int i=0;			
			while(rs.next())
			{
				s[i][0]=rs.getString("userId");
				s[i][1]=rs.getString("Type");
				s[i][2]=rs.getString("sender");
				s[i][3]=rs.getString("Receiver");
				double amt=rs.getDouble("Amount");
				s[i][4]=Double.toString(amt);
				s[i][5]=rs.getDate("Date").toString();
				i++;
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		myTable=new JTable(s,col);
		myTable.setEnabled(false);
			
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(50,150,600,500);
		
		panel.add(tableScrollPane);
		
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
	}
	public void balan()
	{
		String query = "SELECT `Balance` FROM customer WHERE userId='"+userId+"';";     
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
				double bal = rs.getDouble("Balance");
				
				balance.setText("Balance : "+String.format("%.3f",bal));
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}
	
	public void Size()
	{
		String query = "SELECT COUNT(*) FROM transaction WHERE userId='"+userId+"';";     
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
				sz = rs.getInt(1);
				System.out.println(sz);
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}