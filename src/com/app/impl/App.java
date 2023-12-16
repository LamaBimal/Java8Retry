package com.app.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  This App Class demonstrates the retry implemenation in Java 8.
 *
 */
public class App {

    static String directoryPath = "/Test";

    static RetryLogic retryLogic = new RetryLogic(5, 2000);
    public static void main(String []args) throws IOException {
        dirCreation();
    }

    // function to create a directory.
    static void dirCreation() throws IOException {
        try {

            Files.createDirectories(Paths.get(directoryPath));

        } catch (IOException ex){

          System.out.println("** IO Exception ** "+ex.getLocalizedMessage());
          retryLogic.retryImpl(() -> {
              try {
                  dirCreation();
              }finally {

              }
              return null;
          });

        } catch (Exception ex){
            System.out.println(" ** Exception in code execution ** "+ ex.getLocalizedMessage());
        }
    }
}
