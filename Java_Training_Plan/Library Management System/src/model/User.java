package model;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    public int count=0;
    ArrayList<Book> borrowedBooks=new ArrayList<Book>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", borrowedBooks=" + borrowedBooks;
    }
}
