package by.pomidor.repositories;

import by.pomidor.domain.Country;

import java.util.List;

public class CountryRepositoryImpl extends AbstractRepository<Country> implements CountryRepository {

    public CountryRepositoryImpl(String pathToFile) {
        super(pathToFile);
    }

    @Override
    public Country findCountryByName(String name) {
        return loadData().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Country> getAllCountries() {
        return loadData();
    }
}
