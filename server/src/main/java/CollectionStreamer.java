import java.util.Collection;
/**
 * Interface for saving and reading collection to/from file.
 */
public interface CollectionStreamer<T extends Collection<?>> {
    void saveToFile(T collection);
    T readFromFile();
}
