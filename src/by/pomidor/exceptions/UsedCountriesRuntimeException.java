package by.pomidor.exceptions;

public class UsedCountriesRuntimeException extends RuntimeException {
    public UsedCountriesRuntimeException() {
        super();
    }

    public UsedCountriesRuntimeException(String message) {
        super(message);
    }

    public UsedCountriesRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedCountriesRuntimeException(Throwable cause) {
        super(cause);
    }

    protected UsedCountriesRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
