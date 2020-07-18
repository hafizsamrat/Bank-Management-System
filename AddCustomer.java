import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener
{
	private JLabel welcomeLabel,nameLabel,locationLabel,phnLabel,changePasswordLabel,idLabel;
	private JTextField name,location,phnNo,id;
	private JPasswordField changePassword;
	private JButton home,back,logoutBtn,save,showPass;
	private JPanel panel;
	private String userId;
	
	public AddCustomer(String userId)
	{
		super("Bank Management System - Add Customer Window");
		
		this.userId = userId;
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("Entry List");
		welcomeLabel.setBounds(10, 5, 200, 50);
		welcomeLabel.setFont(new Font("Cambrige",Font.BOLD, 20));
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		back = new JButton("Back");
		back.setBounds(440, 50, 80, 30);
		back.addActionListener(this);
		panel.add(back);
		
		idLabel = new JLabel("Id        ");
		idLabel.setBounds(100, 200, 200, 50);
		panel.add(idLabel);
		
		id = new JTextField();
		id.setBounds(250, 200, 300, 50);
		id.setFont(new Font("Cambrige",Font.BOLD, 15));
		id.setForeground(Color.black);
		panel.add(id);
				
		nameLabel = new JLabel("Name      ");
		nameLabel.setBounds(100, 250, 200, 50);
		panel.add(nameLabel);
		
		name = new JTextField();
		name.setBounds(250, 250, 300, 50);
		name.setFont(new Font("Cambrige",Font.BOLD, 15));
		name.setForeground(Color.black);
		panel.add(name);
		
		changePasswordLabel = new JLabel("Password ");
		changePasswordLabel.setBounds(100, 300, 200, 50);
		panel.add(changePasswordLabel);
		
		changePassword	= new JPasswordField();
		changePassword.setBounds(250, 300, 300, 50);
		changePassword.setFont(new Font("Cambrige",Font.BOLD,25));
		changePassword.setEchoChar('*');
		panel.add(changePassword);
		
		locationLabel = new JLabel("Location  ");
		locationLabel.setBounds(100, 350, 200, 50);
		panel.add(locationLabel);
		
		location = new JTextField();
		location.setBounds(250, 350, 300, 50);
		location.setFont(new Font("Cambrige",Font.BOLD, 15));
		panel.add(location);
		
		phnLabel = new JLabel("Phone No  ");
		phnLabel.setBounds(100, 400, 200, 50);
		panel.add(phnLabel);
		
		phnNo = new JTextField();
		phnNo.setBounds(250, 400, 300, 50);
		phnNo.setFont(new Font("Cambrige",Font.BOLD, 15));
		panel.add(phnNo);

		showPass = new JButton("Show password");
		showPass.setBounds(555, 300, 140, 50);
		showPass.addActionListener(this);
		panel.add(showPass);
		
		save = new JButton("Add");
		save.setBounds(250, 490, 100, 50);
		save.setFont(new Font("Cambrige",Font.BOLD, 25));
		save.addActionListener(this);
		panel.add(save);
				
				
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
			Employee c=new Employee(userId);
			c.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource()==showPass)
		{
			changePassword.setEchoChar((char)0);
		}
		else if(ae.getSource()==save)
		{
			int k=0;
			String add=location.getText();
			String names=name.getText();
			String phone=phnNo.getText();
			String pass=changePassword.getText();
			String Id=id.getText();
			for(int i=0;i<add.length();i++)
			{
				if(add.charAt(i)!=' ')
				{
					k++;break;
				}
			}
			for(int i=0;i<names.length();i++)
			{
				if(names.charAt(i)!=' ')
				{
					k++;break;
				}
			}
			for(int i=0;i<pass.length();i++)
			{
				if(pass.charAt(i)!=' ')
				{
					k++;break;
				}
			}
			for(int i=0;i<Id.length();i++)
			{
				if(Id.charAt(i)!=' ')
				{
					k++;break;
				}
			}
			for(int i=0;i<phone.length();i++)
			{
				if (i==0 && phone.charAt(i)=='+') 
				{
					continue;
				}
				else if(phone.charAt(i)>='0' && phone.charAt(i)<='9')
				{
					continue;
				}
				else k=-2;
			}
			k++;
			if(phone.length()<11)k=-1;
			
			if(k==5)
			{
				insert(names,add,phone,pass,Id);
			}
			else if(k==-1)
			{
				JOptionPane.showMessageDialog(this, "Invalid Phone Number!");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "No field should be left empty!");
			}
		}
	}
	
	public void insert(String names, String add, String phone,String pass,String Id)
	{
		double d=0.00;
		int status=1;
		String query = "INSERT INTO customer VALUES ('"+Id+"','"+names+"','"+phone+"','"+add+"' , "+d+");";
		String query2 = "INSERT INTO login VALUES ('"+Id+"','"+pass+"',"+status+");";
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
			JOptionPane.showMessageDialog(this, "Success !!!");
			name.setText("");
			changePassword.setText("");
			id.setText("");
			location.setText("");
			phnNo.setText("");
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			JOptionPane.showMessageDialog(this, "Opps !!! Something Error !!!");
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
	
	
}