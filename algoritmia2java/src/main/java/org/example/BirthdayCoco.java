package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BirthdayCoco {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    ArrayList<Integer> arrayList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      arrayList.add(scanner.nextInt());
    }
    System.out.println(cocoBirthDayProblem(arrayList));//try birthday solution
  }
///////////////////Coco birthday problem solution /////////////////////////
  public static int cocoBirthDayProblem(ArrayList<Integer> guestList) {
    Collections.sort(guestList);
    int index = 0;
    while (index < guestList.size() && index < guestList.get(index)){
      index += 1;
    }
    return index;
  }
// the greedy choice here is who goes to the head of the queue
// the optimal substructure here is the sorting of the guests
// it is a greedy algorithm because the solution it gives is not always the optimal
// O(n)



}