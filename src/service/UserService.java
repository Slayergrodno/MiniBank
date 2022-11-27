package service;

import mapper.UserMapper;
import model.User;
import repository.UserRepository;
import repository.impl.FileUserRepository;
import service.validation.UserValidationRequest;
import service.validation.UserValidationResult;
import service.validation.UserValidator;

import java.util.Optional;

public class UserService {

    private static UserService instance;
    private final UserRepository userRepository = FileUserRepository.getInstance();
    private final UserValidator userValidator = UserValidator.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User checkLogin(final String loginInput, final String passwordInput) {
        String login = loginInput.trim();
        String password = passwordInput.trim();
        Optional<User> userOptional = userRepository.findByUsername(login);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                return userOptional.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public UserValidationResult validate(final UserValidationRequest request) {
        if (userRepository.findByUsername(request.getUserName()).isPresent()) {
            UserValidationResult nonUniqueUserName = new UserValidationResult();
            nonUniqueUserName.addError("���� ����� �����");
            return nonUniqueUserName;
        }
        return userValidator.validate(request);
    }

    public User create(final UserValidationRequest request) {
        return userRepository.save(UserMapper.toObject(request));
    }
}
