package model;

public class Book {

    private int id;
    private String name;
    private String author;
    private int edition;
    private boolean isAvailable;

    public Book(int id, String name, String author, int edition) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.isAvailable = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getEdition() {
        return edition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String toString() {
        return "ID: " + id + " Name: " + name + " Author: " + author + " Edition: " + edition + " isAvailable: " + (isAvailable?"Yes":"No");
    }
}
