package by.pomidor.exceptions;

public class WorldMapGameException extends Exception {
    public WorldMapGameException() {
    }

    public WorldMapGameException(String message) {
        super(message);
    }

    public WorldMapGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorldMapGameException(Throwable cause) {
        super(cause);
    }

    public WorldMapGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
