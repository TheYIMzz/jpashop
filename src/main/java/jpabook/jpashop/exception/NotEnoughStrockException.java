package jpabook.jpashop.exception;

public class NotEnoughStrockException extends  RuntimeException {
    public NotEnoughStrockException() {
        super();
    }

    public NotEnoughStrockException(String message) {
        super(message);
    }

    public NotEnoughStrockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStrockException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStrockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
