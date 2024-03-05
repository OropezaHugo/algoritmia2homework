package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LongestCommonSequence {

  static ArrayList<String> longestCommonSequence(ArrayList<String> X, ArrayList<String> Y, int m, int n, ArrayList<String>[][] dp) {
    if (m == 0 || n == 0) {
      return new ArrayList<>();
    }

    if (dp[m][n] != null)
      return removeDuplicates(dp[m][n]);

    if (EditDistanceCalculator.getWordsSimilarityPercentage(X.get(m - 1), Y.get(n - 1)) >= 55.0) {
      ArrayList<String> lcs = longestCommonSequence(X, Y, m - 1, n - 1, dp);
      lcs.add(X.get(m - 1));
      dp[m][n] = lcs;
      return removeDuplicates(lcs);
    }


    ArrayList<String> lcs1 = longestCommonSequence(X, Y, m, n - 1, dp);
    ArrayList<String> lcs2 = longestCommonSequence(X, Y, m - 1, n, dp);
    dp[m][n] = (lcs1.size() > lcs2.size()) ? lcs1 : lcs2;
    return removeDuplicates(dp[m][n]);
  }

  public static ArrayList<String> removeDuplicates(ArrayList<String> myList) {
    Set<String> group = new HashSet<>(myList);
    return new ArrayList<>(group);
  }
}
