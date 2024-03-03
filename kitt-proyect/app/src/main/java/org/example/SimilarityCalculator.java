package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class SimilarityCalculator {
  private final ArrayList<String> misspelledWords;
  private final ArrayList<String> wordsText1;
  private final ArrayList<String> wordsText2;
  private double textSimilarity;
  public SimilarityCalculator(String text1, String text2) {
    this.wordsText1 = new ArrayList<>(Arrays.asList(text1.replaceAll("\\.", "").split(" ")));
    this.wordsText2 = new ArrayList<>(Arrays.asList(text2.replaceAll("\\.", "").split(" ")));
    this.misspelledWords = new ArrayList<>();
    calculateTextsSimilarity();

  }

  private void calculateTextsSimilarity() {
    double similarityoftexts = 0.0;
    String mostSimilarWord = "";
    double similarityOfWords = 0.0;
    for (String word1: wordsText1) {
      mostSimilarWord = mostSimilarWord(word1, wordsText2);
      similarityOfWords = getWordsSimilarityPercentage(word1, mostSimilarWord);
      if (similarityOfWords > 55.0 && similarityOfWords < 100.0) {
        misspelledWords.add(word1);
        misspelledWords.add(mostSimilarWord);
        wordsText2.remove(mostSimilarWord);
        similarityoftexts += 1.0;
      }
      if (similarityOfWords == 100.0) {
        wordsText2.remove(mostSimilarWord);
        similarityoftexts += 1.0;
      }
    }
    textSimilarity = (similarityoftexts / wordsText1.size()) * 100;
  }


  private double getWordsSimilarityPercentage(String word1, String word2) {
    int minSimilarity = Math.max(word1.length(), word2.length());
    int differences = EditDistanceCalculator.editDist(word1, word2, word1.length(), word2.length());
    return 100 - ((double) differences / minSimilarity) * 100;

  }

  private String mostSimilarWord(String word1, ArrayList<String> wordlist) {
    double similarity = 0;
    String mosSimilarWord = "";
    for (String wordToCompare: wordlist) {
      if (getWordsSimilarityPercentage(word1, wordToCompare) > similarity) {
        similarity = getWordsSimilarityPercentage(word1, wordToCompare);
        mosSimilarWord = wordToCompare;
      }
    }
    return mosSimilarWord;
  }
  public String getMisspelledWords() {
    StringBuilder response = new StringBuilder();
    for (int i = 0; i < (misspelledWords.size()); i+=2) {
      if (i == 0) {
        response.append(misspelledWords.get(i)).append(" - ").append(misspelledWords.get(i + 1));
      } else {
        response.append(", ").append(misspelledWords.get(i)).append(" - ").append(misspelledWords.get(i + 1));
      }
    }
    return response.toString();
  }

  public String getTextSimilarity() {

    return String.valueOf(textSimilarity).substring(0, 5) + "%";
  }
}
