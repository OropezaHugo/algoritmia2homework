package org.example;

import java.util.*;

public class GraphOperations {

  private final ArrayList<RelationEdge> graphEdges;
  private final int minimumRelationWeight;
  private int groupQuantity;

  public GraphOperations(ArrayList<RelationEdge> graphEdges, int minimumRelationWeightOrGroupQuantity) {
    this.graphEdges = graphEdges;
    this.minimumRelationWeight = minimumRelationWeightOrGroupQuantity;
    this.groupQuantity = 0;
  }

  public void setGroupQuantity(int groupQuantity) {
    this.groupQuantity = groupQuantity;
  }

  private HashSet<String> getNodes(ArrayList<RelationEdge> edges) {
    HashSet<String> nodes = new HashSet<>();
    for (RelationEdge edge : edges) {
      nodes.add(edge.getGuestName1());
      nodes.add(edge.getGuestName2());
    }
    return nodes;
  }

  private ArrayList<ArrayList<RelationEdge>> MaximumSpanningTree() {
    ArrayList<ArrayList<RelationEdge>> groupsEdges = new ArrayList<>();
    ArrayList<RelationEdge> myGraphEdges = getMSTPrim();
    myGraphEdges.sort(new RelationEdgeComparator());
    int edgesQuantity = 1;
    int index = 1;
    HashSet<String> usedNodes = new HashSet<>();
    if (getNodes(myGraphEdges).size() == groupQuantity) {
      return groupsEdges;
    }
    groupsEdges.add(new ArrayList<RelationEdge>());
    groupsEdges.get(0).add(myGraphEdges.get(0));
    usedNodes.add(myGraphEdges.get(0).getGuestName1());
    usedNodes.add(myGraphEdges.get(0).getGuestName2());

    while (index < myGraphEdges.size() && (getNodes(myGraphEdges).size() - usedNodes.size()) > (groupQuantity - groupsEdges.size())) {
      RelationEdge actualEdge = myGraphEdges.get(index);
      for (ArrayList<RelationEdge> groupsEdge : groupsEdges) {
        if (getNodes(groupsEdge).contains(actualEdge.getGuestName1()) || getNodes(groupsEdge).contains(actualEdge.getGuestName2())) {
          groupsEdge.add(actualEdge);
          usedNodes.add(actualEdge.getGuestName1());
          usedNodes.add(actualEdge.getGuestName2());
          actualEdge = null;
          break;
        }
      }
      if (actualEdge != null) {
        groupsEdges.add(new ArrayList<RelationEdge>());
        groupsEdges.get(groupsEdges.size() - 1).add(myGraphEdges.get(index));
        usedNodes.add(myGraphEdges.get(index).getGuestName1());
        usedNodes.add(myGraphEdges.get(index).getGuestName2());
      }
      index += 1;
    }
    return groupsEdges;
  }

  private ArrayList<RelationEdge> getMSTPrim() {
    HashSet<RelationEdge> MSTEdges = new HashSet<RelationEdge>();
    HashSet<String> MSTNodes = new HashSet<>();
    ArrayList<RelationEdge> myGraphEdges = new ArrayList<>(this.graphEdges);
    myGraphEdges.sort(new RelationEdgeComparator());
    MSTEdges.add(myGraphEdges.get(0));
    MSTNodes.add(myGraphEdges.get(0).getGuestName1());
    MSTNodes.add(myGraphEdges.get(0).getGuestName2());
    myGraphEdges.remove(0);
    boolean myBool = true;
    while (myBool) {
      HashSet<RelationEdge> possibleEdges = new HashSet<>();
      for (String node : MSTNodes) {
        possibleEdges.addAll(getNodeEdges(node, myGraphEdges));
      }

      RelationEdge actualMaxEdge = getMaxWeightEdge(possibleEdges);
      if (actualMaxEdge != null && actualMaxEdge.getRelationStrength() > this.minimumRelationWeight && !(MSTNodes.contains(actualMaxEdge.getGuestName2()) && MSTNodes.contains(actualMaxEdge.getGuestName1()))) {
        MSTNodes.add(actualMaxEdge.getGuestName1());
        MSTNodes.add(actualMaxEdge.getGuestName2());
        MSTEdges.add(actualMaxEdge);
        myGraphEdges.remove(actualMaxEdge);
      } else {
        myBool = false;
      }
    }
    return new ArrayList<RelationEdge>(MSTEdges);
  }

  private RelationEdge getMaxWeightEdge(HashSet<RelationEdge> edgeHashSet) {
    if (edgeHashSet.isEmpty()) {
      return null;
    }
    RelationEdge maxEdge = new RelationEdge("", "", -1);
    for (RelationEdge edge : edgeHashSet) {
      if (edge.getRelationStrength() > maxEdge.getRelationStrength()) {
        maxEdge = edge;
      }
    }
    return maxEdge;
  }

  private ArrayList<RelationEdge> getNodeEdges(String node, ArrayList<RelationEdge> edges) {
    ArrayList<RelationEdge> nodeEdges = new ArrayList<>();
    for (RelationEdge edge : edges) {
      if (Objects.equals(edge.getGuestName2(), node) || Objects.equals(edge.getGuestName1(), node)) {
        nodeEdges.add(edge);
      }
    }
    return nodeEdges;
  }

  public String getKCaseResponse() {
    ArrayList<ArrayList<RelationEdge>> groups = MaximumSpanningTree();
    HashSet<String> KCaseNodes = new HashSet<>();
    ArrayList<RelationEdge> MSTEdges = getMSTPrim();
    StringBuilder result = new StringBuilder();
    for (ArrayList<RelationEdge> group : groups) {
      HashSet<String> groupNodes = new HashSet<>();
      for (RelationEdge edge : group) {
        groupNodes.add(edge.getGuestName1());
        groupNodes.add(edge.getGuestName2());
        KCaseNodes.add(edge.getGuestName1());
        KCaseNodes.add(edge.getGuestName2());
      }
      for (String node : groupNodes) {
        result.append(node).append(" ");
      }
      result.append('\n');

    }
    HashSet<String> graphNodes = getNodes(MSTEdges);
    for (String node : graphNodes) {
      if (!KCaseNodes.contains(node)) {
        result.append(node);
        result.append('\n');
      }
    }
    result.append(getStrongestFriendlyGroupString(groups));
    result.append('\n');
    result.append(getLeastFriendlyGroupString(groups));
    return result.toString();
  }

  private String getLeastFriendlyGroupString(ArrayList<ArrayList<RelationEdge>> groupsEdges) {
    if (groupsEdges.isEmpty()) {
      return "Group with the least friendly relationship: None";
    }
    ArrayList<RelationEdge> leastFriendlyGroup = new ArrayList<>();
    RelationEdge minorEdge = groupsEdges.get(0).get(0);
    for (ArrayList<RelationEdge> group: groupsEdges) {
      for (RelationEdge edge: group) {
        if (edge.getRelationStrength() < minorEdge.getRelationStrength()) {
          minorEdge = edge;
          leastFriendlyGroup = group;
        }
      }
    }
    StringBuilder stringResult = new StringBuilder("Group with the least friendly relationship: ");
    HashSet<String> leastFriendlyNodes = getNodes(leastFriendlyGroup);
    for (String node: leastFriendlyNodes) {
      stringResult.append(node).append(" ");
    }
    return stringResult.toString();
  }

  private String getStrongestFriendlyGroupString(ArrayList<ArrayList<RelationEdge>> groupsEdges) {
    if (groupsEdges.isEmpty()) {
      return "Group with the strongest friendly relationship: None";
    }
    ArrayList<RelationEdge> strongestFriendlyGroup = groupsEdges.get(0);
    RelationEdge majorEdge = groupsEdges.get(0).get(0);
    for (ArrayList<RelationEdge> group: groupsEdges) {
      for (RelationEdge edge: group) {
        if (edge.getRelationStrength() > majorEdge.getRelationStrength()) {
          majorEdge = edge;
          strongestFriendlyGroup = group;
        }
      }
    }
    StringBuilder stringResult = new StringBuilder("Group with the strongest friendly relationship: ");
    HashSet<String> leastFriendlyNodes = getNodes(strongestFriendlyGroup);
    for (String node: leastFriendlyNodes) {
      stringResult.append(node).append(" ");
    }
    return stringResult.toString();
  }

  public String getMSTPrimResponse() {
    ArrayList<RelationEdge> maxSpanningTree = getMSTPrim();
    HashSet<String> nodes = getNodes(maxSpanningTree);
    StringBuilder stringBuilder = new StringBuilder();
    for (String node: nodes) {
      stringBuilder.append(node).append(" ");
    }
    stringBuilder.append('\n');
    return stringBuilder.toString();
  }
}
