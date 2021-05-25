package by.pomidor;

import by.pomidor.domain.Country;
import com.fasterxml.jackson.core.JsonProcessingException;

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
//        CountryRepositoryImpl repository = new CountryRepositoryImpl();
//        System.out.println(repository.findCountryByName("Греция").orElse(null));

        String pathToCsv = "resources/страны_1.csv";
        String pathToXml = "resources/countries.xml";
        convertCsvToXml(pathToCsv, pathToXml);

//        MapObject mapObject = new MapObject("Belarus");
//        mapObject.setVector(Collections.singletonList(new Point(45D, 75D)));


    }

    private static List<Path> findPathes(List<Path> allPathes, String clazz)
    {
//        List<MapRepositoryImpl.Path> newPathes = allPathes.stream().filter(p -> p.getClazz().equals(clazz)).collect(Collectors.toList());
//        return newPathes;
        return null;
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

    private void createNewSvg1() throws JsonProcessingException
    {
//        MapRepositoryImpl mapRepository = new MapRepositoryImpl();
//        MapRepositoryImpl.SvgRootElement svgRootElement = mapRepository.getSvgRootElement();
//        svgRootElement.setG(new ArrayList<>());
//
//        //List<MapRepositoryImpl.Path> r = svgRootElement.getPathes().stream().map(e -> e).collect(Collectors.toList());
//        Set<String> set = new HashSet<>();
//        for (MapRepositoryImpl.Path path : svgRootElement.getPathes())
//        {
//            if (path.getClazz() != null && !set.contains(path.getClazz()))
//            {
//                set.add(path.getClazz());
//                MapRepositoryImpl.G g = new MapRepositoryImpl.G();
//                g.setClazz(path.getClazz());
//                g.setPathes(findPathes(svgRootElement.getPathes(), path.getClazz()));
//                svgRootElement.getG().add(g);
//            }
//        }
//        svgRootElement.getPathes().forEach(e -> e.setClazz(null));
//        svgRootElement.setPathes(null);
//
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        System.out.println(xmlMapper.writeValueAsString(svgRootElement));
//        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("resources/world1.svg")))
//        {
//            stream.write(xmlMapper.writeValueAsBytes(svgRootElement));
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        System.out.println(svgRootElement);
    }
}
