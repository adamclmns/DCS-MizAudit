package com.adamclmns.mde;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/** The type Main. */
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {

    // Starting point for the app.
    logger.info("Starting Up");
    UserInterface userInterface = new UserInterface();

    userInterface.main();
    logger.info("Shutting Down");
  }


}
