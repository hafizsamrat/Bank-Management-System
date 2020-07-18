import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerTransaction extends JFrame implements ActionListener
{
	private JLabel balance,welcomeLabel,amount,rec;
	private JTextField confirm,receiver;
	private JButton deposit,withdraw,transfer, logoutBtn,back,home,okay,delete,yes,no;
	private JPanel panel;
	private String userId,userId2;
	private int sz=0;
	private JTable myTable;
	private JScrollPane tableScrollPane;
	private double Balance=0;
	int status=0;
	
	public CustomerTransaction(String Id,String userId,int stu)
	{
		super("Bank Management System - Customer Transaction Window");
		
		this.userId = userId;
		this.userId2= Id;
		this.status=stu;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("Transaction of "+Id);
		welcomeLabel.setBounds(10, 5, 200, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 70, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		delete = new JButton("Delete");
		delete.setBounds(610, 120, 80, 30);
		delete.addActionListener(this);
		panel.add(delete);
		
		yes = new JButton("YES");
		yes.setBounds(570, 155, 70, 25);
		yes.addActionListener(this);
		yes.setVisible(false);
		yes.setEnabled(false);
		panel.add(yes);
		
		no = new JButton("no");
		no.setBounds(670, 155, 70, 25);
		no.addActionListener(this);
		no.setVisible(false);
		no.setEnabled(false);
		panel.add(no);
		
		home = new JButton("Home");
		home.setBounds(50, 70, 100, 30);
		home.addActionListener(this);
		panel.add(home);
		
		back = new JButton("Back");
		back.setBounds(490, 70, 100, 30);
		back.addActionListener(this);
		panel.add(back);
		
		deposit = new JButton("Deposit");
		deposit.setBounds(160, 70, 100, 30);
		deposit.addActionListener(this);
		panel.add(deposit);
		
		withdraw = new JButton("Withdraw");
		withdraw.setBounds(270, 70, 100, 30);
		withdraw.addActionListener(this);
		panel.add(withdraw);
		
		transfer = new JButton("Transfer");
		transfer.setBounds(380, 70, 100, 30);
		transfer.addActionListener(this);
		panel.add(transfer);
		
		amount = new JLabel("Amount");
		amount.setBounds(110, 115, 120, 30);
		amount.setEnabled(false);
		amount.setVisible(false);
		panel.add(amount);
		
		rec = new JLabel("Receiver");
		rec.setBounds(150, 150, 70, 30);
		rec.setEnabled(false);
		rec.setVisible(false);
		panel.add(rec);
		
		confirm = new JTextField();
		confirm.setBounds(250, 115, 140, 30);
		confirm.addActionListener(this);
		confirm.setEnabled(false);
		confirm.setVisible(false);
		panel.add(confirm);
		
		receiver = new JTextField();
		receiver.setBounds(250, 150, 140, 30);
		receiver.setEnabled(false);
		receiver.setVisible(false);
		panel.add(receiver);
		
		okay = new JButton("Confirm");
		okay.setBounds(400, 130, 80, 30);
		okay.addActionListener(this);
		okay.setEnabled(false);
		okay.setVisible(false);
		panel.add(okay);
		
		balance= new JLabel("Balance : 0");
		balance.setBounds(60,200,400,50);
		balance.setFont(new Font("Cambria",Font.BOLD, 25));
		panel.add(balance);
		
		balan(Id);
		Size(Id);
		
		String [][] s=new String[sz][6];
		String col[] = {"Account No","Type","Sender","Receiver","Amount","Date"};
		
		String query = "SELECT * FROM transaction WHERE userId='"+Id+"';";     
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
		tableScrollPane.setBounds(50,250,600,300);
		
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
		else if(ae.getSource()==home)
		{
			if(status==0)
			{
				Employee l = new Employee(userId);
				l.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				Manager l=new Manager(userId);
				l.setVisible(true);
				this.setVisible(false);
			}
		}
		else if(ae.getSource()==back)
		{
			CustomerView l = new CustomerView(userId,this.status);
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==deposit)
		{
			amount.setText("Amount Deposit");
			flag();
		}
		else if(ae.getSource()==withdraw)
		{
			amount.setText("Amount Withdraw");
			flag();
		}
		else if(ae.getSource()==transfer)
		{
			amount.setText("Amount Transfer");
			flag2();
		}
		else if(ae.getSource()==delete)
		{
			flag1();
		}
		else if(ae.getSource()==no)
		{
			yes.setVisible(false);
			yes.setEnabled(false);
			no.setVisible(false);
			no.setEnabled(false);
		}
		else if(ae.getSource()==yes)
		{
			remove(userId2);
			CustomerView c=new CustomerView(userId,status);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==okay)
		{
			if("Amount Deposit".equals(amount.getText()))
			{
				System.out.println("Deposit");
				String amt=confirm.getText();
				try
				{
					double b=Double.parseDouble(amt);
					if(b<=0)
					{
						JOptionPane.showMessageDialog(this, "Invalid Amount!");
					}
					else
					{
						Balance+=b;
						update(userId2,"Deposit",userId2,userId2,b,Balance,1);
						confirm.setText("");
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this, "Invalid Amount!");
				}
				
			}
			else if("Amount Withdraw".equals(amount.getText()))
			{
				System.out.println("Withdraw");
				String amt=confirm.getText();
				try
				{
					double b=Double.parseDouble(amt);
					if(b<=0)
					{
						JOptionPane.showMessageDialog(this, "Invalid Amount!");
					}
					else if(Balance-b>=0)
					{
						Balance-=b;
						update(userId2,"Withdraw",userId2,userId2,b,Balance,1);
					}
					else JOptionPane.showMessageDialog(this, "you don't have enough Balance!");
					
					confirm.setText("");
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this, "Invalid Amount!");
				}
			}
			else if("Amount Transfer".equals(amount.getText()))
			{
				System.out.println("Transfer");
				String amt=confirm.getText();
				String id2=receiver.getText();
				double k=check(id2);
				if(userId2.equals(id2))
				{
					JOptionPane.showMessageDialog(this,"You cannot transfer in your own Account!");
				}
				else if(k>=0)
				{
					try
					{
						double b=Double.parseDouble(amt);
						if(b<=0)
						{
							JOptionPane.showMessageDialog(this, "Invalid Amount!");
						}
						else if(Balance-b>=0)
						{
							update(userId2,"Withdraw",userId2,id2,b,Balance-b,0);
							update(id2,"Deposit",userId2,id2,b,k+b,1);
						}
						else JOptionPane.showMessageDialog(this, "you don't have enough Balance to transfer!");
						
						confirm.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(this, "Invalid Amount!");
					}
				}
				else JOptionPane.showMessageDialog(this, "Invalid ID!");
			}
		}
	}
	public void flag()
	{
		okay.setEnabled(true);
		okay.setVisible(true);
		confirm.setEnabled(true);
		confirm.setVisible(true);
		rec.setEnabled(false);
		rec.setVisible(false);
		amount.setEnabled(true);
		amount.setVisible(true);
		receiver.setEnabled(false);
		receiver.setVisible(false);
		yes.setVisible(false);
		yes.setEnabled(false);
		no.setVisible(false);
		no.setEnabled(false);
	}
	public void flag2()
	{
		okay.setEnabled(true);
		okay.setVisible(true);
		confirm.setEnabled(true);
		confirm.setVisible(true);
		rec.setEnabled(true);
		rec.setVisible(true);
		amount.setEnabled(true);
		amount.setVisible(true);
		receiver.setEnabled(true);
		receiver.setVisible(true);
		yes.setVisible(false);
		yes.setEnabled(false);
		no.setVisible(false);
		no.setEnabled(false);
	}
	public void flag1()
	{
		
		okay.setEnabled(false);
		okay.setVisible(false);
		confirm.setEnabled(false);
		confirm.setVisible(false);
		rec.setEnabled(false);
		rec.setVisible(false);
		amount.setEnabled(false);
		amount.setVisible(false);
		receiver.setEnabled(false);
		receiver.setVisible(false);
		yes.setVisible(true);
		yes.setEnabled(true);
		no.setVisible(true);
		no.setEnabled(true);
	}
	
	public void balan(String Id)
	{
		String query = "SELECT `Balance` FROM customer WHERE userId='"+Id+"';";     
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
				this.Balance=bal;
				balance.setText("Balance : "+String.format("%.3f",bal));
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}
	
	public void Size(String Id)
	{
		String query = "SELECT COUNT(*) FROM transaction WHERE userId='"+Id+"';";     
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
	
	public void remove(String Id)
	{
		String query = "DELETE FROM customer WHERE userId='"+Id+"';"; 
		String query2= "DELETE FROM login WHERE userId='"+Id+"';"; 	
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
			st.execute(query);//getting result
			st.execute(query2);
			System.out.println("Deleted");
			JOptionPane.showMessageDialog(this,"Deleted!");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
	
	public double check(String Id)
	{
		String query = "SELECT Balance FROM customer WHERE userId='"+Id+"';";     
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
				System.out.println("Id found");
				return rs.getDouble("Balance");
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		return -1;
	}
	
	public void update(String Id,String type,String sender,String recever,double am,double b,int k)
	{
		Date d=new Date(System.currentTimeMillis());
		String query = "INSERT INTO transaction VALUES ('"+Id+"','"+type+"','"+sender+"','"+recever+"' , "+am+" , '"+d+"');";
		String query2 = "UPDATE customer SET Balance="+b+" WHERE userId='"+Id+"';";
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query);
			System.out.println("results received");
			st.execute(query2);
			
			if(k==1)
			{
				JOptionPane.showMessageDialog(this, "Succes!");
				CustomerTransaction cc=new CustomerTransaction(userId2,userId,this.status);
				cc.setVisible(true);
				this.setVisible(false);
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			JOptionPane.showMessageDialog(this, "Opps !!! Something Error || (Duplicate ID) !!!");
        }
	}
	
}