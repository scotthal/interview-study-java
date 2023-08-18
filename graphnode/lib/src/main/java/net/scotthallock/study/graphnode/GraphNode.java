package net.scotthallock.study.graphnode;

import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Set;

public class GraphNode {
  private int value;
  private Set<GraphNode> neighbors;

  public GraphNode(int value) {
    this.value = value;
    this.neighbors = new HashSet<GraphNode>();
  }

  public int value() {
    return value;
  }

  public ImmutableSet<GraphNode> neighbors() {
    return ImmutableSet.copyOf(neighbors);
  }

  public void addNeighbor(GraphNode neighbor) {
    this.neighbors.add(neighbor);
    neighbor.neighbors.add(this);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GraphNode)) {
      return false;
    }

    GraphNode other = (GraphNode) o;

    if ((this.value == other.value) && (this.neighbors.equals(other.neighbors))) {
      return true;
    }

    return false;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(value);
    for (GraphNode neighbor : neighbors) {
      result = 31 * Integer.hashCode(neighbor.value);
    }
    return result;
  }
}
