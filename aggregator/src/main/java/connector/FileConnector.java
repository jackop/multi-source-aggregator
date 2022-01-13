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

@Slf4j
public class FileConnector implements Connector {
  private static final Random RANDOM = new Random();
  public static final String MARGE_FILE = "marge/currency-marge.txt";

  /**
   * Get random big decimals from file
   */
  @Override
  public BigDecimal getValue() {
    final String line = getLineFromFile();
    final List<BigDecimal> bigDecimals = mapStringToListOfBigDecimals(line);

    return bigDecimals.get(RANDOM.nextInt(bigDecimals.size()));
  }

  /**
   * Parse line from file in resources to get String line from it.
   */
  private String getLineFromFile() {
    final ClassLoader classLoader = getClass().getClassLoader();
    final URL resource = classLoader.getResource(MARGE_FILE);
    File file = new File("");
    if (nonNull(resource)) {
      file = new File(resource.getFile());
    }
    try(Scanner scanner = new Scanner(file)) {
      return scanner.nextLine();
    } catch (FileNotFoundException exception) {
      log.debug("FileConnector.getLineFromFile() | Exception: ", exception);
    }
    return "";
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
