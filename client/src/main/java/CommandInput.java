import lombok.Getter;

import java.util.Arrays;

@Getter
public class CommandInput {
    private final String name;
    private String[] args;

    public CommandInput(String line) {
        String[] words = line.split("\\s");
        this.name = words[0];
        if (words.length > 1) {
            this.args = Arrays.copyOfRange(words, 1, words.length);
        }
    }

}
