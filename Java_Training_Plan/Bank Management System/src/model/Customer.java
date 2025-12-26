package model;

public class Customer extends User{
    private long accountNumber;

    public Customer(String username, String password, long accountNumber) {
        super(username,password);
        this.accountNumber = accountNumber;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public String getRole() {
        return "Customer";
    }
}
