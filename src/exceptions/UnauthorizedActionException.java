package exceptions;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String action) {
        super("Unauthorized action: " + action + ". You do not have the required permissions to perform this action.");
    }
}