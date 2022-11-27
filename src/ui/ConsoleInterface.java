package ui;

import exception.ExitAppException;
import logger.Logger;
import logger.LoggerFactory;
import model.User;
import service.UserService;
import service.validation.UserValidationRequest;
import service.validation.UserValidationResult;

import java.util.Scanner;

public class ConsoleInterface {
    private static final Logger log = LoggerFactory.getInstance(ConsoleInterface.class);

    private final Scanner scanner;
    private final Session session = Session.getInstance();
    private final UserService userService = UserService.getInstance();

    public ConsoleInterface(final Scanner scanner) {
        this.scanner = scanner;
    }

    public void loginLoop() {
        while (true) {
            System.out.println("����� ���������� � �������. ������� ��� �����������������");
            System.out.println("1 �����");
            System.out.println("2 ������������������");
            System.out.println("0 ��������� ������");

            int loginChoose = scanner.nextInt();
            switch (loginChoose) {
                case 1: {
                    System.out.println("������� �����");
                    String usernameInput = scanner.next();
                    System.out.println("������� ������");
                    String passwordInput = scanner.next();

                    User loginResult = userService.checkLogin(usernameInput, passwordInput);
                    if (loginResult != null) {
                        System.out.printf("����� ���������� � �������, %s%n", loginResult.getFirstName());
                        session.createSession(loginResult);
                        log.info("�������� ���� ��� %s".formatted(loginResult.getUserName()));
                        return;
                    } else {
                        System.out.println("�������� ������, ��������� � ���������");
                        log.info("������� �������� ������");
                        break;
                    }
                }
                case 2: {
                    System.out.println("������� �����");
                    String userName = scanner.next();
                    System.out.println("������� ������");
                    String password = scanner.next();
                    System.out.println("������� ���");
                    String firstName = scanner.next();
                    System.out.println("������� �������");
                    String lastName = scanner.next();
                    System.out.println("������� ���� ��������");
                    String birthDate = scanner.next();
                    System.out.println("������� ���");
                    String sex = scanner.next();
                    System.out.println("������� email");
                    String email = scanner.next();

                    UserValidationRequest request = new UserValidationRequest(userName, password, firstName,
                            lastName, birthDate, sex, email);
                    UserValidationResult result = userService.validate(request);

                    if (result.isSuccess()) {
                        User user = userService.create(request);
                        session.createSession(user);
                        System.out.printf("����� ���������� � �������, %s \n", user.getFirstName());
                        return;
                    } else {
                        System.out.println("������ ��� �������� ������������. \n" + result.getValidationMessage());
                        continue;
                    }
                }
                case 0: {
                    throw new ExitAppException();
                }
                default: {
                    break;
                }
            }
        }
    }

    public void mainLoop() {
        while (true) {
            System.out.println("1 �������� �� ������");
            System.out.println("2 ������ � �������� ��������");
            System.out.println("0 ��������� ������");

            int mainChoose = scanner.nextInt();
            switch (mainChoose) {
                case 0: {
                    throw new ExitAppException();
                }
                default: {
                    System.out.println("Unimplemented");
                    return;
                }
            }
        }
    }
}
