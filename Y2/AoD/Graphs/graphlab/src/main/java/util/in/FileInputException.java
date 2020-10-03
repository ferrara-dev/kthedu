package util.in;

public class FileInputException extends RuntimeException {

    public FileInputException(){
        super();
    }

    public FileInputException(String message, Exception cause){
        super(message,cause);
    }
}
