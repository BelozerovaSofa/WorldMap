package by.pomidor.repositories;

import by.pomidor.domain.svg.SvgRootElement;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;

public class SvgMapRepositoryImpl implements MapRepository {
    private SvgRootElement svgRootElement;//это вся наша карта

    @Override
    public SvgRootElement getSvgRootElement(String pathToSvgMap) {
        try {
            byte[] xmlBytes = Files.readAllBytes(java.nio.file.Path.of(pathToSvgMap));
            //читает файл и создает массив байтов и из него создает строку (которая содержит инфу из svg)
            String xmlString = new String(xmlBytes);
            XmlMapper xmlMapper = new XmlMapper();
            svgRootElement = xmlMapper.readValue(xmlString, SvgRootElement.class);
            //передает строку из svg и объект класса SvgRootElement
            //Mapper достает из .class что требуется
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return svgRootElement;
    }
}
