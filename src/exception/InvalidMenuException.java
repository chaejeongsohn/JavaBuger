package exception;

/**
 * 유효하지 않는 메뉴입력시 예외
 */
public class InvalidMenuException extends Exception {
    public InvalidMenuException(String message) {
        super(message);
    }
}
