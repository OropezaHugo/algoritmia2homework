package org.example;

public class RelationEdge{

  private final String guestName1;
  private final String guestName2;
  private final int relationStrength;


  public RelationEdge(String guestName1, String guestName2, int relationStrength) {
    this.guestName1 = guestName1;
    this.guestName2 = guestName2;
    this.relationStrength = relationStrength;
  }

  public String getGuestName1() {
    return guestName1;
  }

  public String getGuestName2() {
    return guestName2;
  }

  public int getRelationStrength() {
    return relationStrength;
  }


}
