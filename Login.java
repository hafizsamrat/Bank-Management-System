import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	private JLabel userLabel,passLabel,bmsName1,bmsName2,bmsName3;
	private JTextField userTF;
	private JPasswordField passTF;
	private JButton login,exitBtn;
	private JPanel panel;
	
	public Login()
	{
		super("Bank Management System - Login Window");
		this.setSize(800,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0,200,250));
		
		bmsName1= new JLabel("Bank");
		bmsName1.setBounds(50,10,600,200);
		Font myFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 70);
		bmsName1.setFont(myFont);
		bmsName1.setForeground(Color.black);
		panel.add(bmsName1);
		
		bmsName2= new JLabel("Management");
		bmsName2.setBounds(150,100,600,200);
		bmsName2.setFont(myFont);
		bmsName2.setForeground(Color.black);
		panel.add(bmsName2);
		
		bmsName3= new JLabel("System");
		bmsName3.setBounds(500,200,600,200);
		bmsName3.setFont(myFont);
		bmsName3.setForeground(Color.black);
		panel.add(bmsName3);
		
		userLabel= new JLabel("User ID ");
		userLabel.setBounds(150,330,600,200);
		userLabel.setFont(new Font("Cambrige",Font.BOLD, 40));
		userLabel.setForeground(Color.black);
		panel.add(userLabel);
		
		passLabel= new JLabel("Password ");
		passLabel.setBounds(100,400,600,200);
		passLabel.setFont(new Font("Cambrige",Font.BOLD, 40));
		passLabel.setForeground(Color.black);
		panel.add(passLabel);
		
		userTF= new JTextField();
		userTF.setBounds(330,410,300,40);
		userTF.setFont(new Font("Cambrige",Font.BOLD, 30));
		userTF.setForeground(Color.white);
		userTF.setBackground(new Color(0,100,100));
		panel.add(userTF);
		
		passTF= new JPasswordField();
		passTF.setBounds(330,480,300,40);
		passTF.setFont(new Font("Cambria",Font.BOLD, 30));
		passTF.setForeground(Color.white);
		passTF.setBackground(new Color(0,100,100));
		passTF.addActionListener(this);
		panel.add(passTF);
		
		login= new JButton("Login");
		login.setBounds(300,560,150,50);
		login.setFont(new Font("Cambria",Font.BOLD, 20));
		login.setForeground(Color.white);
		login.setBackground(new Color(0,0,200));
		login.addActionListener(this);
		panel.add(login);
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(500, 560, 150, 50);
		exitBtn.setBackground(new Color(200,0,0));
		exitBtn.setForeground(Color.white);
		exitBtn.setFont(new Font("Cambria",Font.BOLD, 20));
		exitBtn.addActionListener(this);
		panel.add(exitBtn);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource()==login)
		{
			checkLogin();
		}
		else if(ae.getSource()==exitBtn)
		{
			System.exit(0);
		}
	}
	
	public void checkLogin()
	{
		String query = "SELECT `userId`, `password`, `status` FROM `login` where userId='" + userTF.getText() + "';";     
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
			
			boolean flag = false;			
			while(rs.next())
			{
                String userId = rs.getString("userId");
				String password = rs.getString("password");
				int status = rs.getInt("status");

				if(userId.equals(userTF.getText()) && password.equals(passTF.getText()))
				{
					flag=true;
					if(status==0)
					{
						Employee eh = new Employee(userId);
						eh.setVisible(true);
						this.setVisible(false);
					}
					else if(status==1)
					{
						Customer ch = new Customer(userId);
						ch.setVisible(true);
						this.setVisible(false);
					}
					else if(status==2)
					{
						Manager mh= new Manager(userId);
						mh.setVisible(true);
						this.setVisible(false);
					}
				}
			}
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password"); 
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
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