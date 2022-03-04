package exception;

public class NotFoundException extends Exception {
    /**
     * 검색시 찾으려는 정보가 없을 경우 예외
     */
    public NotFoundException(String message) {
        super(message);
    }
}
