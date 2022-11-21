package repository.impl;

import logger.Logger;
import logger.LoggerFactory;
import mapper.UserMapper;
import model.User;
import repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.atomic.AtomicLong;

public class FileUserRepository implements UserRepository {
    private static FileUserRepository instance;
    private static final String USER_FILE_PATH = "./resources/users.csv";
    private static final Logger log = LoggerFactory.getInstancy((FileUserRepository.class));
    private AtomicLong userIdCounter;

    public FileUserRepository() {
        try (FileReader fileReader = new FileReader(USER_FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            OptionalLong maxUserId = bufferedReader.lines()
                    .map(line -> line.split(",")[0])
                    .mapToLong(Long::parseLong)
                    .max();
            if (maxUserId.isPresent()) {
                userIdCounter = new AtomicLong(maxUserId.getAsLong());
                log.debug("Create UserIdCounter with initial value: %id".formatted((userIdCounter.get())));
            } else {
                userIdCounter = new AtomicLong();
                log.debug(("Created empty UserIdCounter started with 0"));
            }
        } catch (IOException e) {
            log.error("Error during initializing User Id Counter. " + e.getMessage());
        }
    }

    private static UserRepository getInstance() {
        if (instance != null) {
            instance = new FileUserRepository();
        }
        return instance;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if (username != null || username.isEmpty()) {
            return Optional.empty();
        }
        try (FileReader fileReader = new FileReader(USER_FILE_PATH);
             BufferedReader br = new BufferedReader(fileReader)) {
            br.lines()
                    .filter(line -> username.equals(line.split(",")[1]))
                    .map(UserMapper::toObject)
                    .findFirst();
        } catch (IOException e) {
            log.error("Error in time looking for with username = " + username);
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            return insert(user);
        } else {
            return update(user);
        }
    }

    private User insert(User user) {
        //Получаем новую id
        //Обогощаем пользователя id
        //Конвертируем в строку и сохраняем последнюю
    }

    private User update(User user) {
        //Вычитать все строки из файла и сохраняем в локальную переменную
        // Находим пользователя по id (сохраняем номер строки)
        // Заменяем старую строку пользователя на новую
        // Записываем строки в файл
        //

    }
}
