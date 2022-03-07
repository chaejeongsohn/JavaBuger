package exception;

/**
 * 중복된 값 발생시 예외
 */
public class DuplicatedException extends Exception {

    public DuplicatedException(String message) {
        super(message);
    }
}
