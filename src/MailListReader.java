import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MailListReader {

    public List<String> read(String fileName) throws IOException {  
        return Files.readAllLines(Paths.get(fileName)).stream()
                .map(String::new)
                .collect(Collectors.toList());
    }

}
