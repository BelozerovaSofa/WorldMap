package by.pomidor.helpers;

import by.pomidor.repositories.CountryRepository;
import by.pomidor.repositories.CountryRepositoryImpl;
import by.pomidor.repositories.UserRepository;
import by.pomidor.repositories.UserRepositoryImpl;
import by.pomidor.services.UserService;
import by.pomidor.services.UserServiceImpl;
import by.pomidor.services.WholeWorldMapGameServiceImpl;
import by.pomidor.services.WorldMapGameService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanHelper {
    private static final String DEFAULT_PATH_TO_PROPERTIES = "resources/app.properties";
    private static final String PATH_TO_USERS = "app.user.repository.path.xml";
    private static final String PATH_TO_COUNTRIES = "app.country.repository.path.xml";
    private static BeanHelper INSTANCE;
    private Properties properties;
    private CountryRepository countryRepository;
    private UserRepository userRepository;
    private UserService userService;

    private BeanHelper() {
        properties = new Properties();
    }

    public static BeanHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeanHelper();
            INSTANCE.initialize();
        }
        return INSTANCE;
    }

    private void initialize() {
        try (InputStream inputStream = new FileInputStream(DEFAULT_PATH_TO_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            //TODO logger;
            throw new RuntimeException(e);
        }
    }

    public CountryRepository getCountryRepository() {
        if (countryRepository == null) {
            countryRepository = new CountryRepositoryImpl(properties.getProperty(PATH_TO_COUNTRIES));
        }
        return countryRepository;
    }

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl(properties.getProperty(PATH_TO_USERS));
        }
        return userRepository;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(getUserRepository());
        }
        return userService;
    }
}
