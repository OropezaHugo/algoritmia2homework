package org.example;

public class Main {
  public static void main(String[] args) {
    String filePath1 = "app/src/main/resources/eg1-1.txt";
    String fileContent1 = Reader.readFileToString(filePath1);

    String filePath2 = "app/src/main/resources/eg1-2.txt";
    String fileContent2 = Reader.readFileToString(filePath2);

    SimilarityCalculator similarityCalculator = new SimilarityCalculator(fileContent1, fileContent2);

    System.out.println(similarityCalculator.getTextSimilarity());
    System.out.println(similarityCalculator.getMisspelledWords());
  }
}