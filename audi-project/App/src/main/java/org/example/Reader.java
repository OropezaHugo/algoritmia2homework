package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

  public static ArrayList<RelationEdge> readFileToString(String filePath) {
    BufferedReader reader = null;
    ArrayList<RelationEdge> myEdges = new ArrayList<>();
    try {
      File file = new File(filePath);
      FileReader fileReader = new FileReader(file);
      reader = new BufferedReader(fileReader);
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.split(" ").length > 1) {
          RelationEdge relationEdge = new RelationEdge((line.split(" "))[0], (line.split(" "))[1], Integer.parseInt((line.split(" "))[2]) );
          myEdges.add(relationEdge);
        }

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

    return myEdges;
  }
}