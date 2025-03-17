package exceptions;

public class InvalidUserRoleException extends RuntimeException {
    public InvalidUserRoleException() {
        super("Invalid user role. Allowed roles: ADMIN, REGULAR.");
    }
}