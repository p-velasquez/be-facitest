package cl.facitest.exceptions.test;

public class ExamAlreadyExistsException extends RuntimeException{
    public ExamAlreadyExistsException(String message) {
        super(message);
    }
}
