package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

  public static String readFileToString(String filePath) {
    StringBuilder content = new StringBuilder();
    BufferedReader reader = null;

    try {
      File file = new File(filePath);
      FileReader fileReader = new FileReader(file);
      reader = new BufferedReader(fileReader);
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line);
        content.append(" ");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return content.toString();
  }
}