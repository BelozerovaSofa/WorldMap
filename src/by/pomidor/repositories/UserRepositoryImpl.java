package by.pomidor.repositories;

import by.pomidor.domain.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    public UserRepositoryImpl(String pathToFile) {
        super(pathToFile);
    }

    @Override
    public User findUserByLogin(String login) {
        return loadData().stream().filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst().orElse(null);
    }

    @Override
    public boolean doesUserLoginExist(String login) {
        return findUserByLogin(login) != null;
    }

    @Override
    public boolean saveUser(User user) {
        List<User> data = loadData();
        Optional<User> userOptional = data.stream()
                .filter(i -> i.getLogin().equalsIgnoreCase(user.getLogin()))
                .findFirst();
        if (userOptional.isPresent()) {
            User u = userOptional.get();
            u.setMaxCount(user.getMaxCount());
            u.setPasswordHash(user.getPasswordHash());
        } else {
            data.add(user);
        }
        return saveData(data);
    }

    @Override
    public List<User> findLimitedAndSortedUsers(Comparator<User> c, int limit) {
        return loadData().stream().sorted(c).limit(limit).collect(Collectors.toList());
    }
}
