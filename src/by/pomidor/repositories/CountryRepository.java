package by.pomidor.repositories;

import by.pomidor.domain.Country;

import java.util.List;

public interface CountryRepository {

    Country findCountryByName(String name);

    List<Country> getAllCountries();
}
