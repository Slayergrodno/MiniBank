package service.validation;

public class UserValidationResult {

    private final StringBuilder validationMessage = new StringBuilder();
    private boolean isSuccess = true;

    public void addError(final String errorMessage) {
        validationMessage.append(errorMessage).append("\n");
        isSuccess = false;
    }

    public String getValidationMessage() {
        return validationMessage.toString();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(final boolean success) {
        isSuccess = success;
    }
}
