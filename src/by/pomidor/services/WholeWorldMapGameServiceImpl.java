package by.pomidor.services;

import by.pomidor.domain.Country;
import by.pomidor.domain.User;
import by.pomidor.repositories.CountryRepository;
import by.pomidor.repositories.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class WholeWorldMapGameServiceImpl implements WorldMapGameService {

    private static final String QUESTION_TEMPLATE = "Где находидся %s?";

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final int countriesNumber;
    private Country currentCountry;
    private int count = 0;
    private Set<String> usedCountriesNames;
    private User currentUser;

    public WholeWorldMapGameServiceImpl(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.countriesNumber = countryRepository.getAllCountries().size();
        this.usedCountriesNames = new HashSet<>();
        this.userRepository = userRepository;
    }

    @Override
    public void start(User user) {
        currentUser = user;
        count = 0;
        usedCountriesNames.clear();
    }

    @Override
    public String nextQuestion() {
        currentCountry = getRandomCountry();
        if (currentCountry == null) {
            return null;
        }
        return String.format(QUESTION_TEMPLATE, currentCountry.getName());
    }

    @Override
    public Boolean answer(String answer) {
        if (currentCountry == null) {
            return null;
        }
        boolean result = currentCountry.getName().equals(answer);
        if (result) {
            count++;
        }
        return result;
    }

    @Override
    public boolean isEnded() {
        return usedCountriesNames.size() == countriesNumber;
    }

    @Override
    public void end() {
        if (currentUser != null) {
//            Integer maxCount = currentUser.getMaxCount();
//            maxCount = maxCount == null ? 0 : maxCount;
            if (count > currentUser.getMaxCount()) {
                currentUser.setMaxCount(count);
                userRepository.saveUser(currentUser);
            }
        }
    }

    @Override
    public Integer count() {
        return count;
    }

    @Override
    public Integer totalCount() {
        return countryRepository.getAllCountries().size();
    }

    @Override
    public Country getCurrentCountry() {
        return currentCountry;
    }

    private Country getRandomCountry() {
        if (usedCountriesNames.size() == countriesNumber) {
            return null;
        }

        Country country = countryRepository.getAllCountries().get(getRandomCountryIndex());
        if (usedCountriesNames.contains(country.getName())) {
            return getRandomCountry();
        } else {
            usedCountriesNames.add(country.getName());
            return country;
        }
    }

    private int getRandomCountryIndex() {
        Random random = new Random();
        return random.nextInt(countriesNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WholeWorldMapGameServiceImpl)) return false;
        WholeWorldMapGameServiceImpl that = (WholeWorldMapGameServiceImpl) o;
        return countriesNumber == that.countriesNumber &&
                count == that.count &&
                Objects.equals(countryRepository, that.countryRepository) &&
                Objects.equals(currentCountry, that.currentCountry) &&
                Objects.equals(usedCountriesNames, that.usedCountriesNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryRepository, countriesNumber, currentCountry, count, usedCountriesNames);
    }
}
