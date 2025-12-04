package mainPro;

import java.util.*;

import bankDAO.adminDAO;
import bankDAO.bankingDAO;

public class BankingApp {

	public static void main(String[] args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		bankingDAO dao=new bankingDAO();
		adminDAO adao=new adminDAO();
		
		while(true) {
			System.out.println("\n===== Welcome to Online Banking System =====");
			System.out.println("1. Admin Login");
			System.out.println("2. User Banking Menu");
			System.out.println("3. Exit");
			System.out.println("Choose: ");
			int mainChoice=sc.nextInt();
			switch (mainChoice) {
			//-----------Admin Login------------------
			case 1:
				System.out.print("Admin Username: ");
				String aU=sc.next();
				System.out.print("Admin Password: ");
				String aP=sc.next();
				
				
				if(adao.adminLogin(aU,aP)) {
					System.out.println("\n*** Admin Login Successful ***");
					
					while (true) {
                        System.out.println("\n--- ADMIN PANEL ---");
                        System.out.println("1. View All Users");
                        System.out.println("2. View All Accounts");
                        System.out.println("3. View All Transactions");
                        System.out.println("4. Logout");
                        
                        System.out.print("Choose: ");
                        int adminChoice=sc.nextInt();
                        
                        switch (adminChoice) {
                        
                        case 1:
                        	adao.viewUsers();
                        	break;
                        	
                        case 2:
                        	adao.viewAccounts();
                        	break;
                        	
                        case 3:
                        	adao.viewTransactions();
                        	break;
                        	
                        case 4:
                        	System.out.println("Admin Logged Out.");
                        	break;
                        
                        default:
                        	System.out.println("Invalid Option");
                        }
                        
                        if(adminChoice == 4)
                        	break;
                        
					}
				}else 
					System.out.println("Invalid admin credentials!");

			
			break;
		
			//----------------------User Menu---------------------
			case 2:
				while(true) {
					System.out.println("\n-----ONLINE BANKING-----");
					System.out.println("1. Create User");
					System.out.println("2. Create Account");
					System.out.println("3. Check Balance");
					System.out.println("4. Deposit");
					System.out.println("5. Withdraw");
					System.out.println("6. Transfer");
					System.out.println("7. Back to main menu");
					
					System.out.print("Choose: ");
					int choice =sc.nextInt();
		//			int choice;
		//			
		//			while(true) {
		//				System.out.println("Choose: ");
		//				if(sc.hasNextInt()) {
		//					choice=sc.nextInt();
		//					sc.nextLine();
		//					break;
		//				}
		//				else {
		//					System.out.println("Invalid input! Please enter a number.");
		//					sc.next();
		//				}
		//			}
					
					switch (choice) {
					case 1:
						System.out.print("Userid: ");
						int i=sc.nextInt();
						System.out.print("Username: ");
						String u=sc.next();
						System.out.print("password: ");
						String p=sc.next();
						System.out.print("Fullname: ");
						String f=sc.next();
						dao.createUser(i, u, p, f);
						break;
					
					case 2:
						System.out.print("Userid: ");
						dao.createAccount(sc.nextInt());
						break;
						
					case 3:
		                System.out.print("Account ID: ");
		                System.out.println("Balance: " + dao.getBalance(sc.nextInt()));
		                break;
		            
					case 4:
		                System.out.print("Account ID: ");
		                int a1 = sc.nextInt();
		                System.out.print("Amount: ");
		                dao.deposit(a1, sc.nextDouble());
		                break;
		                
					case 5:
		                System.out.print("Account ID: ");
		                int a2 = sc.nextInt();
		                System.out.print("Amount: ");
		                dao.withdraw(a2, sc.nextDouble());
		                break;
		               
					case 6:
		                System.out.print("From Account: ");
		                int fAcc = sc.nextInt();
		                System.out.print("To Account: ");
		                int tAcc = sc.nextInt();
		                System.out.print("Amount: ");
		                dao.transfer(fAcc, tAcc, sc.nextDouble());
		                break;
		            
					case 7:
						break;
						
					default:
						System.out.println("Invalid choice");
					}
					if(choice==7)
						break;
				}
				break;
				
			case 3:
				System.exit(0);
				
			default:
				System.out.println("Invalid Choice");
			}
		}
	}

}
