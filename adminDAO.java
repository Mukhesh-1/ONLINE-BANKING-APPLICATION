package bankDAO;

import java.sql.*;

import DBCon.DBconn;

public class adminDAO {
    // Admin Login
    public boolean adminLogin(String username, String password) throws Exception {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        Connection con = DBconn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    // View all users
    public void viewUsers() throws Exception {
        String sql = "SELECT * FROM users";
        Connection con = DBconn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- USERS LIST ---");
        while (rs.next()) {
            System.out.println("UserID: " + rs.getInt("userid") +
                    ", Username: " + rs.getString("username") +
                    ", Fullname: " + rs.getString("fullname"));
        }
        con.close();
    }

    // View all accounts
    public void viewAccounts() throws Exception {
        String sql = "SELECT * FROM accounts";
        Connection con = DBconn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- ACCOUNTS LIST ---");
        while (rs.next()) {
            System.out.println("AccountID: " + rs.getInt("accountid") +
                    ", UserID: " + rs.getInt("userid") +
                    ", Balance: " + rs.getDouble("balance"));
        }
        con.close();
    }

    // View all transactions
    public void viewTransactions() throws Exception {
        String sql = "SELECT * FROM transactions";
        Connection con = DBconn.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- TRANSACTIONS LIST ---");
        while (rs.next()) {
            System.out.println("TxnID: " + rs.getInt("tnxid") +
                    ", AccountID: " + rs.getInt("accountid") +
                    ", Type: " + rs.getString("tnx_type") +
                    ", Amount: " + rs.getDouble("amount") +
                    ", Time: " + rs.getTimestamp("tnx_time"));
        }
        con.close();
    }
}
