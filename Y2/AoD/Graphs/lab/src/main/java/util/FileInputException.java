package util;

public class FileInputException extends RuntimeException {

    public FileInputException(){
        super();
    }

    public FileInputException(String message, Exception cause){
        super(message,cause);
    }
}
