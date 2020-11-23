package connector;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileConnector {

  private static final String EMPTY = "";
  private static final Random RANDOM = new Random();

  /**
   * Get random big decimals from file
   */
  public BigDecimal getRandomBigDecimalFromFile(String fileName) {
    String line = getLineFromFile(fileName);
    List<BigDecimal> bigDecimals = mapStringToListOfBigDecimals(line);

    return bigDecimals.get(RANDOM.nextInt(bigDecimals.size()));
  }

  /**
   * Parse line from file in resources to get String line from it.
   */
  private String getLineFromFile(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    File file = new File(EMPTY);
    if (nonNull(resource)) {
      file = new File(resource.getFile());
    }
    try(Scanner scanner = new Scanner(file)) {
      return scanner.nextLine();
    } catch (FileNotFoundException exception) {
      log.debug("FileConnector.getLineFromFile() | Exception for file {}", fileName, exception);
    }
    return EMPTY;
  }

  /**
   * Map String line from file and map it to List of BigDecimals
   */
  private List<BigDecimal> mapStringToListOfBigDecimals(String line) {
    // split string from file by coma and space and even more than one space
    return stream(line.split(",\\s*"))
      .map(BigDecimal::new)
      .collect(toList());
  }
}
