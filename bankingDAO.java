package bankDAO;

import java.sql.*;

import DBCon.DBconn;

public class bankingDAO {
//	create new user
	public void createUser(int userid, String username,String passwords,String fullname) throws Exception{
		String sql= "INSERT INTO users(userid, username,passwords,fullname) values (?,?,?,?)";
		Connection con=DBconn.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1,userid);
		ps.setString(2,username);
		ps.setString(3,passwords);
		ps.setString(4,fullname);
		ps.executeUpdate();
		con.close();
		System.out.println("user created successfully");
	}
	//create bank Account
	public void createAccount(int userid) throws Exception{
		String sql= "INSERT INTO accounts(userid, balance) VALUES (?, 0)";
		Connection con=DBconn.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, userid);
		
		ps.executeUpdate();
		con.close();
		System.out.println("Account created successfully");
	}
	//check balance
	public double getBalance(int accountid) throws Exception{
		String sql = "SELECT balance FROM accounts WHERE accountid=?";
		Connection con=DBconn.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, accountid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			return rs.getDouble("balance");
		}
		else {
			throw new Exception("Account not found");
		}
		
	}
	//deposit money
	public void deposit(int accountid,double amount) throws Exception{
		String sql= "UPDATE accounts set balance=balance + ? Where accountid=?";
		Connection con=DBconn.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setDouble(1, amount);
		ps.setInt(2,accountid);
		ps.executeUpdate();
		con.close();
		System.out.println("Deposit Successfully");
	}
	//withdraw money
	public void withdraw(int accountid,double amount) throws Exception{
		double current=getBalance(accountid);
		if(current<amount) {
			System.out.println("Insufficient balance");
			return;
		}
		String sql="UPDATE accounts SET balance=balance- ? WHERE accountid=?";
		Connection con=DBconn.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2,accountid);
		ps.executeUpdate();
		con.close();
		System.out.println("Withdraw Successfully");
	}
	//transfer money
	public void transfer(int fromAcc,int toAcc, double amount) throws Exception{
		double current= getBalance(fromAcc);
		if(current<amount) {
			System.out.print("Insufficient balance");
			return;
		}
		
		Connection con=DBconn.getConnection();
		con.setAutoCommit(false);
		
		try {
			PreparedStatement withdraw=con.prepareStatement(
					"UPDATE accounts SET balance=balance- ? WHERE accountid= ?");
			withdraw.setDouble(1, amount);
			withdraw.setInt(2,fromAcc );
			withdraw.executeUpdate();
			
	        PreparedStatement deposit = con.prepareStatement(
	                "UPDATE accounts SET balance = balance + ? WHERE accountid=?");
	            deposit.setDouble(1, amount);
	            deposit.setInt(2, toAcc);
	            deposit.executeUpdate();
	            
	        con.commit();
	        System.out.println("transfer Successfully");
		}catch(Exception e) {
			con.rollback();
			System.out.println("Transfer failed");
		}finally {
			con.close();
		}
	}
}
