import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;
import exception.*;
import service.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int readInt(Scanner scanner,String Message)
    {
        int input;
        while(true){
            System.out.println(Message);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                return input;
            }
            catch (InputMismatchException e){
                System.out.println("Enter numbers only!");
                scanner.nextLine();
            }
        }

    }

    public static String readString(Scanner scanner,String Message)
    {
        String input;
        while(true){
            System.out.println(Message);
            input = scanner.nextLine();
            if(input.matches("[a-zA-Z0-9 .,&:'()\\-+ ]+")){
                return input;
                }
            else{
                System.out.println("Invalid input! Enter alphabets only!");
            }
        }
    }
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner= new Scanner(System.in);
        boolean flag=true;
            while (flag == true) {
                System.out.println("Choose one option: ");
                System.out.print("1. Add Book\n" +
                        "2. View Books\n" +
                        "3. Search Book\n" +
                        "4. Register Member\n" +
                        "5. Borrow Book\n" + "" +
                        "6. Return Book\n" +
                        "7. Exit\n");
                int choice=scanner.nextInt();
                switch (choice) {
                    case 1: {
                        int id = readInt(scanner,"Enter Book ID: ");
                        String name = readString(scanner,"Enter Book Name: ");
                        String author = readString(scanner,"Enter Book Author: ");
                        int edition = readInt(scanner,"Enter Book Edition: ");
                        Book book = new Book(id, name, author, edition);
                        libraryService.addBook(book);
                        flag = true;
                        break;
                    }
                    case 2: {
                        libraryService.viewBook();
                        flag = true;
                        break;
                    }
                    case 3: {
                        try {
                            int searchId = readInt(scanner,"Enter Book ID to search: ");
                            libraryService.searchBook(searchId);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        flag = true;
                        break;
                    }
                    case 4: {
                        int registerMemberId=readInt(scanner,"Enter Member ID to register: ");
                        String memberName = readString(scanner,"Enter Member Name: ");
                        User user=new User(registerMemberId,memberName);
                        libraryService.addUser(user);
                        flag=true;
                        break;
                    }
                    case 5: {
                        try{
                        scanner.nextLine();
                        String name = readString(scanner,"Enter your Name: ");
                        String bname=readString(scanner,"Enter Book Name to borrow: ");
                        String bauthor=readString(scanner,"Enter Book Author to borrow: ");
                        int bedition=readInt(scanner,"Enter Book Edition to borrow: ");
                        libraryService.borrowBook(name,bname,bauthor,bedition);

                    } catch (MaxLimitReached e) {
                        System.out.println(e.getMessage());
                    } catch (BookUnavailable e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Something went wrong!");
                    }
                        flag=true;
                        break;
                    }
                    case 6: {
                        try {
                            scanner.nextLine();
                            String name = readString(scanner, "Enter your Name: ");
                            int id = readInt(scanner, "Enter Book ID to return: ");
                            String rname = readString(scanner, "Enter Book Name to return: ");
                            libraryService.returnBook(name, id, rname);
                        } catch (Exception e) {
                            System.out.println("Book not borrowed!");
                        }
                        flag=true;
                        break;
                    }
                    case 7: {
                        System.out.println("Thank you for using our service!");
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice!");
                        break;
                    }

                }
            }

    }
}