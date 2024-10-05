package cl.facitest.exceptions.test;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(String message) {
        super(message);
    }
}
