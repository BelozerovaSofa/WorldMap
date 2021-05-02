package by.pomidor;

import by.pomidor.domain.Country;
import by.pomidor.repositories.CountryRepositoryImpl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StarterTest
{
    public static void main(String[] args) throws IOException
    {
        CountryRepositoryImpl repository = new CountryRepositoryImpl();
        System.out.println(repository.findCountryByName("Греция").orElse(null));

//        String pathToCsv = "resources/страны.csv";
//        String pathToXml = "resources/countries.xml";
//        convertCsvToXml(pathToCsv, pathToXml);
    }

    public static void convertCsvToXml(String pathToCsv, String pathToXml) throws IOException
    {
        List<String> csvLines = Files.readAllLines(Path.of(pathToCsv));
        List<Country> countries = csvLines.stream().map(StarterTest::map).collect(Collectors.toList());
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(pathToXml)))
        {
            xmlEncoder.writeObject(countries);
        }
    }

    private static Country map(String str)
    {
        String[] array = str.split(";");
        Country country = new Country();
        country.setName(array[1]);
        country.setCapital(array[2]);
        country.setDescription(array[3]);
        country.setFlagImagePath(array[4]);
        return country;
    }
}
