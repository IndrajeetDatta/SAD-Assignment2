import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.sql.*;

public class ATMUI extends JFrame
{
	JButton button_insertCard, button_ejectCard, button_insertPin, button_requestCash;
	JPanel thePanel = new JPanel();
	JTextField tf_accountNo, tf_pinNo, tf_cash;
	JLabel lb_accountNo, lb_pinNo, lb_cash, lb_balance;
	int pinNo, accountNo, balance;
	
	GetATMData atmProxy = new ATMProxy();
	ATMState currentATMState = atmProxy.getATMState();
	static Connection conn = null;

	public static void main(String[] args)
	{
		new ATMUI();
		
		
	}
	
	public ATMUI() 
	{
		System.out.println(currentATMState);

		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("ATM");
		
		
		button_insertCard = new JButton("Insert Card");
		button_ejectCard = new JButton("Eject Card");
		button_insertPin = new JButton("Insert Pin");
		button_requestCash = new JButton("Request Cash");
		
		tf_accountNo = new JTextField("", 10);
		tf_pinNo = new JTextField("", 6);
		tf_cash = new JTextField("", 10);
		
		lb_accountNo = new JLabel("Account Number: ");
		lb_pinNo = new JLabel("Pin Number: ");
		lb_cash = new JLabel("Cash to withdraw: ");
		lb_balance = new JLabel("");
		
		ListenForButton lForButton = new ListenForButton();
		button_insertCard.addActionListener(lForButton);
		button_ejectCard.addActionListener(lForButton);
		button_insertPin.addActionListener(lForButton);
		button_requestCash.addActionListener(lForButton);
		
		thePanel.add(lb_balance);
		thePanel.add(button_insertPin);
		thePanel.add(button_requestCash);
		thePanel.add(lb_accountNo);
		thePanel.add(tf_accountNo);
		thePanel.add(lb_pinNo);
		thePanel.add(tf_pinNo);
		thePanel.add(lb_cash);
		thePanel.add(tf_cash);
		thePanel.add(button_insertCard);
		thePanel.add(button_ejectCard);
		
		button_insertCard.setVisible(true);
		button_ejectCard.setVisible(false);
		button_insertPin.setVisible(false);
		button_requestCash.setVisible(false);
		tf_cash.setVisible(false);
		tf_pinNo.setVisible(false);
		tf_accountNo.setVisible(false);
		lb_accountNo.setVisible(false);
		lb_pinNo.setVisible(false);
		lb_cash.setVisible(false);
		lb_balance.setVisible(false);
		
		this.add(thePanel);
		this.setVisible(true);
		
	}
	
	private class ListenForButton implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == button_insertCard)
			{
	
				atmProxy.insertCard();
				currentATMState = atmProxy.getATMState();
				System.out.println(currentATMState);

				
				if(currentATMState.equals(atmProxy.getYesCardState()))
				{
					button_ejectCard.setVisible(true);
					button_insertPin.setVisible(true);
					lb_pinNo.setVisible(true);
					tf_pinNo.setVisible(true);
					lb_accountNo.setVisible(true);
					tf_accountNo.setVisible(true);
					button_insertCard.setVisible(false);
			
					
					thePanel.validate();

				}
			}
			if(event.getSource() == button_insertPin)
			{
				pinNo = Integer.parseInt(tf_pinNo.getText());
				accountNo = Integer.parseInt(tf_accountNo.getText());
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/atm_data", "root", "F+OL30yi");
					
					Statement sqlState = conn.createStatement();
					String selectStuff = "Select * from account where account_number = " + tf_accountNo.getText();
					ResultSet rows = sqlState.executeQuery(selectStuff);
					
					while(rows.next()) {
						if (Integer.parseInt(rows.getString("pin_number")) == pinNo)
						{
							atmProxy.insertPin();
							balance = Integer.parseInt(rows.getString("balance"));
							lb_balance.setText("Your account balance is: " + rows.getString("balance"));
							lb_balance.setVisible(true);
							System.out.println("Balance: " + balance);
						}
						else 
						{
							atmProxy.ejectCard();
							lb_balance.setVisible(false);
						}

					}
				}
				 catch (SQLException ex) {
			            
			        	// String describing the error
			        	
			            System.out.println("SQLException: " + ex.getMessage());
			            
			            // Vendor specific error code
			            
			            System.out.println("VendorError: " + ex.getErrorCode());
			        } 
			        
			        catch (ClassNotFoundException e) {
						// Executes if the driver can't be found
						e.printStackTrace();
					} 
			        
				
				
				currentATMState = atmProxy.getATMState();
				System.out.println(currentATMState);
				
				if(currentATMState.equals(atmProxy.getHasPin()))
				{
					button_insertCard.setVisible(false);
					button_insertPin.setVisible(false);
					lb_pinNo.setVisible(false);
					lb_accountNo.setVisible(false);
					tf_pinNo.setVisible(false);
					tf_accountNo.setVisible(false);
					button_requestCash.setVisible(true);
					lb_cash.setVisible(true);
					tf_cash.setVisible(true);
					
					thePanel.validate();
					
				}

				else if(currentATMState.equals(atmProxy.getNoCardState()))
				{
					button_insertCard.setVisible(true);
					button_ejectCard.setVisible(false);
					button_insertPin.setVisible(false);
					lb_pinNo.setVisible(false);
					lb_accountNo.setVisible(false);
					tf_pinNo.setVisible(false);
					tf_accountNo.setVisible(false);
					button_requestCash.setVisible(false);
					lb_cash.setVisible(false);
					tf_cash.setVisible(false);
					lb_balance.setVisible(false);
					
					thePanel.validate();
					
				}
			}
			if(event.getSource() == button_ejectCard)
			{
				atmProxy.ejectCard();
				currentATMState = atmProxy.getATMState();
				System.out.println(currentATMState);

				if(currentATMState.equals(atmProxy.getNoCardState()))
				{
					button_ejectCard.setVisible(false);
					button_insertPin.setVisible(false);
					tf_pinNo.setVisible(false);
					tf_accountNo.setVisible(false);
					button_insertCard.setVisible(true);
					button_requestCash.setVisible(false);
					tf_cash.setVisible(false);
					lb_cash.setVisible(false);
					lb_balance.setVisible(false);
			
					thePanel.validate();

				}
			}
			
			
			if(event.getSource() == button_requestCash)
			{
				int cashToWithdraw = Integer.parseInt(tf_cash.getText());
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/atm_data", "root", "F+OL30yi");
					
					Statement sqlState = conn.createStatement();
					String selectStuff = "Select * from account where account_number = " + tf_accountNo.getText();
					ResultSet rows = sqlState.executeQuery(selectStuff);
					
					while(rows.next()) 
					{
						if (Integer.parseInt(rows.getString("balance")) >= cashToWithdraw)
						{
							 atmProxy.requestCash(cashToWithdraw);
						}
						else 
						{
							atmProxy.ejectCard();
							lb_balance.setVisible(false);
						}

					}
				}
				 catch (SQLException ex) 
				{
			            
			        	// String describing the error
			        	
			            System.out.println("SQLException: " + ex.getMessage());
			            
			            // Vendor specific error code
			            
			            System.out.println("VendorError: " + ex.getErrorCode());
			        } 
			        
			        catch (ClassNotFoundException e) 
					{
						// Executes if the driver can't be found
							e.printStackTrace();
					} 
				 currentATMState = atmProxy.getATMState();
				 if(currentATMState.equals(atmProxy.getNoCardState()))
				 {
					 button_insertCard.setVisible(true);
					 button_ejectCard.setVisible(false);
					 button_requestCash.setVisible(false);
					 lb_cash.setVisible(false);
					 tf_cash.setVisible(false);
					 
				}
			}
		}
	}
}
