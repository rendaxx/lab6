import collectionobject.Organization;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.StringReader;
import java.nio.file.Files;
import java.util.NoSuchElementException;

public class CsvParser {
    public static Organization parse(String str) {
        try {
            return (Organization) new CsvToBeanBuilder<>(new StringReader(str))
                    .withType(Organization.class)
                    .build().parse().getFirst();
        } catch (IllegalStateException | NoSuchElementException e) {
            return null;
        }
    }
}
