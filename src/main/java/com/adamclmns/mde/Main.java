package com.adamclmns.mde;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/** The type Main. */
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  /**
   * Main.
   *
   * @param args the args
   */
  private static String PROPERTIES_FILE = "src/main/resources/config.properties";

  public static void main(String[] args) {

    Properties props = readProperties();
    // Starting point for the app.
    logger.info("Starting Up");
    UserInterface userInterface = new UserInterface();

    userInterface.main(props);
    logger.info("Shutting Down");
  }

  @SneakyThrows
  private static Properties readProperties(){
    Properties props = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("config.properties");
    props.load(stream);
    // TODO: allow overriding with external file or command line args
    return props;
  }

}
