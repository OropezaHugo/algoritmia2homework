package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class ThemePArtyCoco {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    ArrayList<Integer> arrayList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      arrayList.add(scanner.nextInt());
    }
    System.out.println(happiestRow(arrayList, arrayList.size()) - 1);
  }

  ///////////////////Coco theme party problem solution /////////////////////////
  public static ArrayList<Integer> removeAtIndex(ArrayList<Integer> list, int indexToRemove) {
    ArrayList<Integer> modifiedList = new ArrayList<>(list);
    modifiedList.remove(indexToRemove);
    return modifiedList;
  }

  public static int happiestRow(ArrayList<Integer> guestList, int index) {
    if (guestList.isEmpty()) {
      return 0;
    }

    int maxIndex = 0;
    int sharedHappiness = 0;
    for (int i = 0; i < guestList.size(); i++) {
      if (Math.abs(guestList.get(index) - guestList.get(i)) >= sharedHappiness) {
        sharedHappiness = Math.abs(guestList.get(index) - guestList.get(i));
        maxIndex = i;
      }
    }
    ArrayList<Integer> path1 = removeAtIndex(guestList, index);
    ArrayList<Integer> path2 = removeAtIndex(guestList, maxIndex);
    return Math.max(sharedHappiness + happiestRow(path1, path1.size() - 1), sharedHappiness + happiestRow(path2, path2.size() - 1));

  }
// the greedy choice here is who goes next to who
// the optimal substructure here is maximize the guest's happiness
// it is a greedy algorithm because the solution it gives is not always the optimal
// O(n^2)


}
