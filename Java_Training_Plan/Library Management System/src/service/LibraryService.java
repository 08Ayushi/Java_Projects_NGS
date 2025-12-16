package service;

import exception.BookNotFound;
import exception.BookUnavailable;
import exception.MaxLimitReached;
import model.Book;
import model.User;

import java.util.ArrayList;


public class LibraryService {
    ArrayList <Book> books=new ArrayList<>();
    ArrayList<User>  users=new ArrayList<>();
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void viewBook() {

        if (books.isEmpty()) {
            System.out.println("No books to view! Add Book first!");

        }
        else {
            for (Book book : books) {
                System.out.println(book);
            }
        }

    }

    public void searchBook(int id) throws Exception,BookNotFound {

            if (books.isEmpty()) {
                System.out.println("No books to search! Add Book first!");
            }
            else if (id<books.size() || id>books.size()) {
                throw new Exception("Enter valid Book ID to search!");
            }
            else {
                for (Book book : books) {
                    if (book.getId() == id) {
                        System.out.println("Book found successfully!");
                        System.out.println(book);
                    }
                    throw new BookNotFound("Book not found of id: "+ id);

                }
            }

    }
    public void addUser(User user) {
            users.add(user);
            System.out.println("Member added successfully!");
            if (users.isEmpty()) {
                System.out.println("No Members to added!");
            }
            else {
                System.out.println("Added Members are:");
                for (User user1 : users) {
                    System.out.println(user1);
                }

            }
    }

    public void borrowBook(String name,String bname, String author, int edition) throws MaxLimitReached, BookUnavailable {
        if (books.isEmpty()) {
            System.out.println("No books to borrow! Add Book first!");
        }

        User foundUser=null;
        for(User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                foundUser=user;
                System.out.println("User found successfully!");
                break;
            }
        }
        if (foundUser==null) {
            System.out.println("User not registered!");
            return;
        }

        if (foundUser.count>2) {
            throw new MaxLimitReached("Maximum limit reached!");
        }
        Book foundBook=null;
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(bname) && book.getAuthor().equalsIgnoreCase(author) && book.getEdition() == edition) {
                foundBook=book;
                System.out.println("Book found successfully!");
                break;
            }
        }
        if (foundBook==null) {
            throw new BookUnavailable();
        }
        foundUser.getBorrowedBooks().add(foundBook);
        foundUser.count++;
        books.remove(foundBook);

        System.out.println("Book borrowed successfully!");
        System.out.println("Books borrowed are: "+foundBook);
    }

    public  void returnBook(String name,int id, String rname) throws Exception {
        User foundUser=null;
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                foundUser=user;
                System.out.println("User found successfully!");
                break;
            }
        }
        if (foundUser==null) {
            System.out.println("User not registered!");
            return;
        }

        if (foundUser.getBorrowedBooks().isEmpty()){
            System.out.println("No books to return! Take Book first!");
        }

        Book returnBook=null;
        for (Book book : foundUser.getBorrowedBooks()) {
            if (book.getName().equals(rname) && book.getId() == id) {
                returnBook=book;
                break;
            }
        }

        if (returnBook==null) {
            throw new Exception("Book not borrowed!");
        }

        foundUser.getBorrowedBooks().remove(returnBook);
        foundUser.count--;
        books.add(returnBook);
        System.out.println("Book returned successfully!");

    }

}
