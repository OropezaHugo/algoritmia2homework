package org.example;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String entry = scanner.nextLine();
    int numberX = Integer.parseInt(entry.substring(4));
    //GraphOperations graphOperations = new GraphOperations(Reader.readFileToString("App/src/main/resources/eg1file.txt"), numberX);
    //GraphOperations graphOperations = new GraphOperations(Reader.readFileToString("App/src/main/resources/eg2file.txt"), numberX);
    GraphOperations graphOperations = new GraphOperations(Reader.readFileToString("App/src/main/resources/eg3file.txt"), numberX);
    System.out.println(graphOperations.getMSTPrimResponse());

    String entry2 = scanner.nextLine();
    int numberK = Integer.parseInt(entry2.substring(4));
    graphOperations.setGroupQuantity(numberK);
    System.out.println(graphOperations.getKCaseResponse());
  }

}