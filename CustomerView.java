import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;

public class CustomerView extends JFrame implements ActionListener, ListSelectionListener
{
	private JLabel welcomeLabel,balance;
	private JButton back,logoutBtn,searchBtn;
	private JTextField search;
	private JPanel panel;
	private String userId;
	private JTable myTable;
	private JScrollPane tableScrollPane;
	private int sz=0;
	int statu;
	
	public CustomerView(String userId,int stu)
	{
		super("Bank Management System - View Customers Window");
		
		this.userId = userId;
		this.setSize(800,1000);
		this.statu=stu;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("Customer List");
		welcomeLabel.setBounds(10, 5, 200, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 80, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		back = new JButton("Back");
		back.setBounds(500, 50, 80, 30);
		back.addActionListener(this);
		panel.add(back);
		
		search = new JTextField();
		search.setBounds(250, 100, 200, 30);
		panel.add(search);
		
		searchBtn=new JButton("Search");
		searchBtn.setBounds(450,100,80,30);
		searchBtn.addActionListener(this);
		panel.add(searchBtn);
		
		Size();
		String [][] s=new String[sz][5];
		String col[] = {"Account No","Name","Phone No","Address","Balance"};
		
		
		String query = "SELECT * FROM customer;";     
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
				s[i][1]=rs.getString("customerNamer");
				s[i][2]=rs.getString("phoneNumber");
				s[i][3]=rs.getString("address");
				double amt=rs.getDouble("Balance");
				s[i][4]=Double.toString(amt);
				i++;
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		myTable=new JTable(s,col);
		//myTable.setEnabled(false);
		
		myTable.getSelectionModel().addListSelectionListener(this);
		
		myTable.setCellSelectionEnabled(false);
        myTable.setRowSelectionAllowed(true);
        myTable.setColumnSelectionAllowed(false);
        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		myTable.setDefaultEditor(Object.class, null);	
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(50,150,600,500);
		
		panel.add(tableScrollPane);
		
		this.add(panel);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		try {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			if (!e.getValueIsAdjusting()) {
				String accountNumber = myTable.getModel().getValueAt(lsm.getLeadSelectionIndex(), 0).toString();

				//System.out.println("VALUE: " + accountNumber);

				CustomerTransaction c=new CustomerTransaction(accountNumber,userId,statu);
				c.setVisible(true);
				this.setVisible(false);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,"ID not Found");
		}
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
			if(this.statu==0)
			{
				Employee c=new Employee(userId);
				c.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				Manager c=new Manager(userId);
				c.setVisible(true);
				this.setVisible(false);
			}
		}
		else if(ae.getSource()==searchBtn)
		{
			int k=0;
			String Id=search.getText();
			for(int i=0;i<Id.length();i++)
			{
				if(Id.charAt(i)!=' ')
				{
					k++;break;
				}
			}
			if(k==1)
			{
				int f=searc(Id);
				if(f==1)
				{
					CustomerTransaction c=new CustomerTransaction(Id,userId,statu);
					c.setVisible(true);
					this.setVisible(false);
				}
				else  JOptionPane.showMessageDialog(this, "Invalid ID!");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Search field empty!");
			}
			
		}
	}
	
	public void Size()
	{
		String query = "SELECT COUNT(*) FROM customer;";     
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
	public int searc(String Id)
	{
		String query = "SELECT userId FROM customer WHERE userId='"+Id+"';";     
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
				System.out.println(1);
				return 1;
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		return 0;
	}
}