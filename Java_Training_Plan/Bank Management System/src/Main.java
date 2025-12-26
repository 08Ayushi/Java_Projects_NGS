import exception.InsufficientBalanceException;
import service.BankService;

import java.util.Scanner;
public class Main {
    public static long readLong(Scanner scanner, String message)
    {
        long number;
        while(true){
            System.out.println(message);
            try{
                number = scanner.nextLong();
                scanner.nextLine();
                return number;
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number!");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(Scanner scanner, String message)
    {
        double number;
        while(true){
            System.out.println(message);
            try{
                number = scanner.nextDouble();
                scanner.nextLine();
                return number;
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number!");
                scanner.nextLine();
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       // AuthService authService = new AuthService();
        BankService bankService = new BankService();
        boolean aflag = true;
        boolean flag = true;
        while (aflag) {
            System.out.println("\nLogin As:");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int loginChoice = scanner.nextInt();
            switch (loginChoice) {
                case 1:{
                    scanner.nextLine();
                    System.out.println("Enter admin name:");
                    String admin=scanner.nextLine();
                    System.out.println("Enter admin password:");
                    String password=scanner.nextLine();
                    if(!admin.equals("admin") && !password.equals("admin123")){
                        System.out.println("Please enter correct credentials!");
                        break;
                    }
                    boolean acase = true;
                    while(acase){
                        System.out.println("\nADMIN MENU");
                        System.out.println("1. Create Account");
                        System.out.println("2. View All Accounts");
                        System.out.println("3. Logout");
                        int adminChoice = scanner.nextInt();
                        switch (adminChoice) {
                            case 1:{
                                scanner.nextLine();
                                System.out.print("Enter Customer Name: ");
                                String name = scanner.nextLine();
                                long accNum = readLong(scanner, "Enter Account Number: ");
                                double bal = readDouble(scanner, "Enter Initial Balance: ");
                                bankService.createAccount(name, accNum, bal);
                                System.out.println("Account created successfully!");
                                acase = true;
                                break;
                            }
                            case 2:{
                                bankService.viewAccountAdmin();
                                acase = true;
                                break;
                            }
                            case 3:{
                                acase = false;
                                System.out.println("Logout as Admin successfully!");
                                break;
                            }
                            default:{
                                System.out.println("Please enter correct choice!");
                            }
                        }

                    }
                    aflag = true;
                    break;
                }

                case 2:{
                    boolean ccase = true;
                    while(ccase){
                        System.out.println("\nCUSTOMER MENU");
                        System.out.print("1. Create Account\n" +
                                "2. View Account\n" +
                                "3. Deposit Amount\n"+
                                "4. Withdraw Amount\n"+
                                "5. Check Balance\n"+
                                "6. View Transactions\n"+
                                "7. Exit\n");
                        System.out.println("Enter your choice: ");
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:{
                                scanner.nextLine();
                                System.out.println("Enter your name: ");
                                String cname = scanner.nextLine();
                                long account=readLong(scanner,"Enter your account number: ");
                                double balance=readDouble(scanner,"Enter your balance amount: ");
                                bankService.createAccount(cname,account,balance);
                                ccase=true;
                                break;
                            }
                            case 2:{
                                scanner.nextLine();
                                System.out.println("Enter Customer Name:");
                                String cname = scanner.nextLine();
                                bankService.viewAccountCustomer(cname);
                                ccase=true;
                                break;
                            }
                            case 3:{
                                scanner.nextLine();
                                System.out.println("Enter your name: ");
                                String dname = scanner.nextLine();
                                long account=readLong(scanner,"Enter your account number: ");
                                double amount=readDouble(scanner,"Enter your amount to deposit: ");
                                bankService.deposit(dname,amount,account);
                                ccase=true;
                                break;
                            }
                            case 4: {
                                try {
                                    scanner.nextLine();
                                    System.out.println("Enter your name: ");
                                    String wname = scanner.nextLine();
                                    long account=readLong(scanner,"Enter your account number: ");
                                    double amount=readDouble(scanner,"Enter your amount to withdraw: ");
                                    bankService.withdraw(wname,amount,account);
                                }
                                catch (InsufficientBalanceException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                ccase=true;
                                break;
                            }
                            case 5: {
                                scanner.nextLine();
                                System.out.println("Enter your name: ");
                                String cname = scanner.nextLine();
                                long account=readLong(scanner,"Enter your account number: ");
                                bankService.checkBalance(cname,account);
                                ccase=true;
                                break;
                            }
                            case 6: {
                                long acc=readLong(scanner,"Enter account number: ");
                                bankService.viewTransactions(acc);
                                ccase=true;
                                break;
                            }
                            case 7: {
                                System.out.println("Logged out successfully.");
                                ccase = false;
                                break;
                            }
                            default:{
                                System.out.println("Invalid choice!");
                                break;
                            }
                        }

                    }
                    aflag = true;
                    break;
                }
                case 3:{
                    aflag = false;
                    System.out.println("Thank you for using Bank Management System!");
                    break;
                }
                default:{
                    System.out.println("Invalid choice!");
                }
            }

        }


    }
}