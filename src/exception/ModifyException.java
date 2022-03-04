package exception;

/**
 * 수정시 발생하는 예외
 */
public class ModifyException extends Exception {
    public ModifyException(String message) {
        super(message);
    }
}
