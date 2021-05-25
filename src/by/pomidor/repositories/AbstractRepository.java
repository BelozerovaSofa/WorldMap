package by.pomidor.repositories;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final String pathToFile;

    protected AbstractRepository(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    protected List<T> loadData() {
        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(pathToFile))) {
            return (List<T>) xmlDecoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean saveData(List<T> data) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(pathToFile))) {
            xmlEncoder.writeObject(data);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
