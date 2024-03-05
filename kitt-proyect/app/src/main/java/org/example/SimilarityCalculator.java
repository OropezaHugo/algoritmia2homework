package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class SimilarityCalculator {
  private final ArrayList<String> misspelledWords;
  private final ArrayList<String> sentensesText1;
  private final ArrayList<String> sentensesText2;
  private final ArrayList<String> wordsText1;
  private final ArrayList<String> wordsText2;
  private double textSimilarity;
  public SimilarityCalculator(String text1, String text2) {

    this.sentensesText1 = new ArrayList<>(Arrays.asList(text1.split("\\.")));
    this.sentensesText2 = new ArrayList<>(Arrays.asList(text2.split("\\.")));
    this.sentensesText1.remove(this.sentensesText1.size() - 1);
    this.sentensesText2.remove(this.sentensesText2.size() - 1);
    this.wordsText1 = new ArrayList<>(Arrays.asList(text1.replaceAll("\\.", "").split(" ")));
    this.wordsText2 = new ArrayList<>(Arrays.asList(text2.replaceAll("\\.", "").split(" ")));
    this.misspelledWords = new ArrayList<>();
    calculateTextsSimilarity();

  }

  private void calculateTextsSimilarity() {
    double similarityoftexts;
    ArrayList<String> lcsequence = new ArrayList<>();
    ArrayList<String> sentenseAsList;
    ArrayList<String> sentense2AsList;
    for (String sentense: sentensesText1) {
      for (String sentense2: sentensesText2) {
        sentenseAsList = new ArrayList<>(Arrays.asList(sentense.split(" ")));
        sentense2AsList = new ArrayList<>(Arrays.asList(sentense2.split(" ")));
        ArrayList<String>[][] myArraylist= new ArrayList[sentenseAsList.size() + 1][sentense2AsList.size() + 1];
        ArrayList<String> sequence =  LongestCommonSequence.longestCommonSequence(sentenseAsList, sentense2AsList, sentenseAsList.size(), sentense2AsList.size(), myArraylist);
        if (sequence.size() > lcsequence.size()) {
          lcsequence = sequence;
        }
      }
    }
    for (String word: lcsequence) {
      double similarity = EditDistanceCalculator.getWordsSimilarityPercentage(mostSimilarWord(word, wordsText2), word);
      if (similarity > 55.0 && similarity < 100.0) {
        misspelledWords.add(word);
        misspelledWords.add(mostSimilarWord(word, wordsText2));
      }
    }
    similarityoftexts = lcsequence.size();
    textSimilarity = (similarityoftexts / wordsText1.size()) * 100;
  }




  private String mostSimilarWord(String word1, ArrayList<String> wordlist) {
    double similarity = 0;
    String mosSimilarWord = "";
    for (String wordToCompare: wordlist) {
      if (EditDistanceCalculator.getWordsSimilarityPercentage(word1, wordToCompare) > similarity) {
        similarity = EditDistanceCalculator.getWordsSimilarityPercentage(word1, wordToCompare);
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

    return String.valueOf(textSimilarity).substring(0,Math.min(String.valueOf(textSimilarity).length(), 5)) + "%";
  }

  public ArrayList<String> getSentensesText1() {
    return sentensesText1;
  }

  public ArrayList<String> getSentensesText2() {
    return sentensesText2;
  }
}
