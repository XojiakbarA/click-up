package uz.pdp.clickup.exception;

public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String resourceName, String field, Object value) {
        super(String.format("%s by %s '%s' already exists", resourceName, field, value));
    }
}
