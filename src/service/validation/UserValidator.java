package service.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class UserValidator {

    private static UserValidator instance;

    private UserValidator() {}

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    public UserValidationResult validate(final UserValidationRequest request) {
        String userNameRegexp = "^\\w{4,8}$";
        Pattern userNamePattern = Pattern.compile(userNameRegexp);
        String userNameInvalid = "������������ ��� ������������. ������ ���� ����� ������ �� ���������� ���� � ���� ������ �� 4 �� 8 ��������";

        String passwordRegexp = "^[0-9]{6,10}$";
        Pattern passwordPattern = Pattern.compile(passwordRegexp);
        String passwordInvalid = "������������ ������. ������ ���� �� ���� ������ �� 6 �� 10 ��������";

        String nameRegexp = "^[a-zA-Z]+$";
        Pattern namePattern = Pattern.compile(nameRegexp);
        String nameInvalid = "������������ ��� ��� �������. ������ ���� ����� ������ ������ �� ���������� ���� ������ ����c��� ������ 1 ������� ������";

        String birthDateInvalid = "������������ ���� ��������. ������ ���� ����-��-��, �������� 2022-12-24";

        String sexRegexp = "^[M,W]$";
        Pattern sexPattern = Pattern.compile(sexRegexp);
        String sexInvalid = "������������ ���. M ��� W ���������";

        String emailRegexp = "^\\w+@\\w+\\.\\w+$";
        Pattern emailPattern = Pattern.compile(emailRegexp);
        String emailInvalid = "������������ email. ������ ��������� \"@\" � \".\" ����� ���, � ����� ����� ����� ����";

        UserValidationResult result = new UserValidationResult();
        if (!userNamePattern.matcher(request.getUserName()).matches()) {
            result.addError(userNameInvalid);
        }
        if (!passwordPattern.matcher(request.getPassword()).matches()) {
            result.addError(passwordInvalid);
        }
        if (!namePattern.matcher(request.getFirstName()).matches()) {
            result.addError(nameInvalid);
        }
        if (!namePattern.matcher(request.getLastName()).matches()) {
            result.addError(nameInvalid);
        }

        try {
            LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            result.addError(birthDateInvalid);
        }

        if (!sexPattern.matcher(request.getSex()).matches()) {
            result.addError(sexInvalid);
        }
        if (!emailPattern.matcher(request.getEmail()).matches()) {
            result.addError(emailInvalid);
        }

        return result;
    }
}
