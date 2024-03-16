package org.example;

import java.util.Comparator;

public class RelationEdgeComparator  implements Comparator<RelationEdge> {
  @Override
  public int compare(RelationEdge o1, RelationEdge o2) {
    return Integer.compare(o2.getRelationStrength(), o1.getRelationStrength());
  }
}
