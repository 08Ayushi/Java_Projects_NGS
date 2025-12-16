package exception;

public class BookUnavailable extends Exception {

    public String getMessage(){
        return "Book is not available";
    }
}
