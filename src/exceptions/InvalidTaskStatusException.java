package exceptions;

public class InvalidTaskStatusException extends RuntimeException {
    public InvalidTaskStatusException(String status) {
        super("Invalid task status: '" + status + "'. Allowed values: PENDING, IN_PROGRESS, COMPLETED.");
    }
}

