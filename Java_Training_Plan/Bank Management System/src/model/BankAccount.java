package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private String name;
    private long accountNumber;
    private double balance;
    private List<Transaction> transactions=new ArrayList<>();

    public BankAccount(String name,long accountNumber,double balance){
        this.name=name;
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    public String getName() {
        return name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){
        balance+=amount;
        transactions.add(new Transaction("Deposit",amount,balance));
    }
    public void withdraw(double amount){
        balance-=amount;
        transactions.add(new Transaction("Withdraw",amount,balance));
    }

    public void printStatement(){
        if(transactions.isEmpty()){
            System.out.println("No transactions found!");
            return;
        }
        transactions.forEach(System.out :: println);
    }



}
