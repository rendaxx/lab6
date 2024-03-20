
import collectionobject.Organization;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Log
public class CsvOrganizationStreamer implements CollectionStreamer<LinkedHashSet<Organization>> {

    private static CsvOrganizationStreamer singleton;
    private Path pathToFile;

    public static CsvOrganizationStreamer getInstance() {
        if (singleton == null)
            singleton = new CsvOrganizationStreamer();
        return singleton;
    }

    private CsvOrganizationStreamer() {
        pathToFile = Paths.get("D:\\projects\\lab6\\server\\src\\main\\resources\\data.csv");
    }

    public void setPathToFile(Path p) {
        this.pathToFile = p;
    }

    @Override
    public void saveToFile(LinkedHashSet<Organization> collection) {
        if (pathToFile == null || Files.notExists(pathToFile)) {
            log.warning("File not found");
            return;
        }
        try (Writer writer = new PrintWriter(pathToFile.toFile())) {
            StatefulBeanToCsv<Organization> beanToCsv = new StatefulBeanToCsvBuilder<Organization>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(collection.stream());
            log.info("Saved successfully");
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            // TODO
        }
    }

    @Override
    public LinkedHashSet<Organization> readFromFile() {
        if (pathToFile == null || Files.notExists(pathToFile)) {
            log.warning("File not found");
            return new LinkedHashSet<>();
        }
        try {
            return new CsvToBeanBuilder<>(Files.newBufferedReader(pathToFile))
                    .withType(Organization.class)
                    .build().parse().stream().map(q -> (Organization) q).collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (IOException e) {
            return new LinkedHashSet<>();
        }
    }
}
