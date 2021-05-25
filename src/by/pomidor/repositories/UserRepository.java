package by.pomidor.repositories;

import by.pomidor.domain.User;

import java.util.Comparator;
import java.util.List;

public interface UserRepository {

    User findUserByLogin(String login);

    boolean doesUserLoginExist(String login);

    boolean saveUser(User user);

    List<User> findLimitedAndSortedUsers(Comparator<User> c, int limit);
}
