import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeePro extends JFrame implements ActionListener
{
	private JLabel welcomeLabel,rec;
	private JTextField confirm;
	private JButton salary,logoutBtn,back,home,okay,delete,yes,no;
	private JPanel panel;
	private String userId,userId2;
	private int sz=0;
	private JTable myTable;
	private JScrollPane tableScrollPane;
	
	public EmployeePro(String Id,String userId)
	{
		super("Bank Management System - Employees profile Window");
		
		this.userId = userId;
		this.userId2= Id;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("View of "+Id);
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
		
		
		salary = new JButton("Manage Salary");
		salary.setBounds(280, 70, 160, 30);
		salary.addActionListener(this);
		panel.add(salary);
		
		rec = new JLabel("New Salary");
		rec.setBounds(150, 150, 70, 30);
		rec.setEnabled(false);
		rec.setVisible(false);
		panel.add(rec);
		
		confirm = new JTextField();
		confirm.setBounds(250, 150, 140, 30);
		confirm.addActionListener(this);
		confirm.setEnabled(false);
		confirm.setVisible(false);
		panel.add(confirm);
		
		okay = new JButton("Confirm");
		okay.setBounds(400, 160, 80, 30);
		okay.addActionListener(this);
		okay.setEnabled(false);
		okay.setVisible(false);
		panel.add(okay);
		
		String [][] s=new String[1][4];
		String col[] = {"UserId","Name","Phone No","Salary"};
		
		String query = "SELECT * FROM employee WHERE userId='"+Id+"';";     
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
				s[i][1]=rs.getString("employeeName");
				s[i][2]=rs.getString("phoneNumber");
				double amt=rs.getDouble("salary");
				s[i][3]=Double.toString(amt);
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
			Manager l=new Manager(userId);
			l.setVisible(true);
			this.setVisible(false);		
		}
		else if(ae.getSource()==back)
		{
			EmployeeView l = new EmployeeView(userId);
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==salary)
		{
			flag();
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
			EmployeeView c=new EmployeeView(userId);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==okay)
		{
			try
			{
				String amt=confirm.getText();
				double d=Double.parseDouble(amt);
				if(d<=0)
				{
					JOptionPane.showMessageDialog(this,"Invalid Salary");
				}
				else
				{
					update(userId2,d);
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"Invalid Salary");
			}
		}
	}
	public void flag()
	{
		okay.setEnabled(true);
		okay.setVisible(true);
		confirm.setEnabled(true);
		confirm.setVisible(true);
		rec.setEnabled(true);
		rec.setVisible(true);
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
		yes.setVisible(true);
		yes.setEnabled(true);
		no.setVisible(true);
		no.setEnabled(true);
	}
	public void remove(String Id)
	{
		String query = "DELETE FROM employee WHERE userId='"+Id+"';"; 
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
	public void update(String Id,double b)
	{
		String query2 = "UPDATE employee SET salary="+b+" WHERE userId='"+Id+"';";
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query2);
			System.out.println("results received");
			
			JOptionPane.showMessageDialog(this, "Succes!");
			EmployeePro cc=new EmployeePro(userId2,userId);
			cc.setVisible(true);
			this.setVisible(false);
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			JOptionPane.showMessageDialog(this, "Opps !!! Something Error !!!");
        }
	}
	
}