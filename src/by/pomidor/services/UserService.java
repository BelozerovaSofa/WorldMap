package by.pomidor.services;

import by.pomidor.domain.User;
import by.pomidor.exceptions.InvalidPasswordException;
import by.pomidor.exceptions.UserAlreadyExistsException;
import by.pomidor.exceptions.UserNotFoundException;
import by.pomidor.exceptions.WorldMapGameException;

public interface UserService {

    User logIn(String login, String password) throws UserNotFoundException, InvalidPasswordException;

    User register(String login, String password, String repeatedPassword) throws UserAlreadyExistsException, WorldMapGameException;
}
