package service;

import exception.InsufficientBalanceException;

import model.*;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Customer> customers;
    List<Transaction> transactions = new ArrayList<>();
    private static final String ACCOUNT_FILE = "src/data/accounts.ser";
    private static final String CUSTOMER_FILE = "src/data/customers.ser";

    // ---------- LOAD DATA ON START ----------
    public BankService() {
        //this.auth = auth;
        bankAccounts = (ArrayList<BankAccount>) FileUtil.loadObject(ACCOUNT_FILE);
        customers = (ArrayList<Customer>) FileUtil.loadObject(CUSTOMER_FILE);

        if (bankAccounts == null) bankAccounts = new ArrayList<>();
        if (customers == null) customers = new ArrayList<>();
    }

    //as admin account creation
    public void createAccount(String name, long accountNumber, double balance) {
        if (name.isEmpty() || accountNumber <= 0 || balance <= 0) {
            System.out.println("Invalid details!");
            return;
        }

        for(BankAccount bankAccount : bankAccounts){
            if(bankAccount.getAccountNumber() == accountNumber){
                System.out.println("Account already exists!");
                return;
            }
        }
        BankAccount bankAccount = new BankAccount(name, accountNumber, balance);
        Customer customer = new Customer(name,"1234",accountNumber);

        bankAccounts.add(bankAccount);
        customers.add(customer);
        saveData();
    }
    //View as admin
    public void viewAccountAdmin() {
        if (bankAccounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }

        for (BankAccount b : bankAccounts) {
            System.out.println("Name: " + b.getName());
            System.out.println("Account Number: " + b.getAccountNumber());
            System.out.println("Balance: " + b.getBalance());
        }
    }
    public void viewAccountCustomer(String cname) {
        if (bankAccounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }

        for (BankAccount b : bankAccounts) {
            if(b.getName().equalsIgnoreCase(cname)) {
                System.out.println("Name: " + b.getName());
                System.out.println("Account Number: " + b.getAccountNumber());
                System.out.println("Balance: " + b.getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }

    public void deposit(String name, double amount, long account) {
        if(amount <= 0){
            System.out.println("Invalid amount!");
            return;
        }
        for (BankAccount b : bankAccounts) {
            if (b.getAccountNumber() == account && b.getName().equalsIgnoreCase(name)) {
                b.deposit(amount);
                saveData();
                System.out.println("Deposit successful");
                return;
            }
        }

        System.out.println("Account not found!");
    }

    public void withdraw(String name, double amount, long account)
            throws InsufficientBalanceException {

        if(amount <= 0){
            System.out.println("Invalid amount!");
            return;
        }
        for (BankAccount b : bankAccounts) {
            if (b.getAccountNumber() == account && b.getName().equalsIgnoreCase(name)) {
                if (b.getBalance() < amount) {
                    throw new InsufficientBalanceException("Insufficient balance!");
                }
                b.withdraw(amount);
                saveData();
                System.out.println("Withdrawal successful");
                return;
            }
        }

        System.out.println("Account not found!");
    }

    public void checkBalance(String cname,long account) {
        for (BankAccount b : bankAccounts) {
            if(b.getName().equalsIgnoreCase(cname) && b.getAccountNumber() == account){
                System.out.println("Account: " + b.getAccountNumber());
                System.out.println("Balance: " + b.getBalance());
            }
            return;

        }
        System.out.println("Account not found!");
    }

    public void viewTransactions(long acc){
        for(BankAccount b : bankAccounts){
            if(b.getAccountNumber() == acc) {
                System.out.println("Transactions for "+acc+ " is:");
                b.printStatement();
                return;
            }
        }
        System.out.println("No transactions found!");

    }
    private void saveData() {
        FileUtil.saveObject(bankAccounts, ACCOUNT_FILE);
        FileUtil.saveObject(customers, CUSTOMER_FILE);
    }

}
