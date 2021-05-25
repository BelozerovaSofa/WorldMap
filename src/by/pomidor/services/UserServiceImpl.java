package by.pomidor.services;

import by.pomidor.domain.User;
import by.pomidor.exceptions.InvalidPasswordException;
import by.pomidor.exceptions.UserAlreadyExistsException;
import by.pomidor.exceptions.UserNotFoundException;
import by.pomidor.exceptions.WorldMapGameException;
import by.pomidor.repositories.UserRepository;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь с именем \"%s\" не найден.";
    private static final String INVALID_PASSWORD_MESSAGE = "Введено неверное кодовое слово.";
    private static final String INVALID_REPEATED_PASSWORD_MESSAGE = "Повторно введенное кодовое слово не совпадает с первым.";
    private static final String USER_ALREADY_EXIST_MESSAGE = "Пользователь с именем \"%s\" уже существует.";
    private static final String SAVE_USER_MESSAGE = "При сохранении произошла ошибка. Ваши данные не будут сохранены.";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User logIn(String login, String password) throws UserNotFoundException, InvalidPasswordException {
        User user = userRepository.findUserByLogin(login);
        if (user == null) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, login));
        }
        if (!user.getPasswordHash().equals(getMD5PasswordHash(password))) {
            throw new InvalidPasswordException(INVALID_PASSWORD_MESSAGE);
        }
        return user;
    }

    @Override
    public User register(String login, String password, String repeatedPassword) throws UserAlreadyExistsException, WorldMapGameException {
        if (userRepository.doesUserLoginExist(login)) {
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXIST_MESSAGE, login));
        }
        if (!password.equals(repeatedPassword)) {
            throw new InvalidPasswordException(INVALID_REPEATED_PASSWORD_MESSAGE);
        }
        String passwordHash = getMD5PasswordHash(password);
        User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        if (!userRepository.saveUser(user)) {
            throw new WorldMapGameException(SAVE_USER_MESSAGE);
        }
        return user;
    }

    private static String getMD5PasswordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
