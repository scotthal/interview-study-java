package net.scotthallock.study.graphnode;

import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

  public GraphNode deepCopy() {
    Map<Integer, GraphNode> created = new HashMap<Integer, GraphNode>();

    return deepCopyRecurse(this, created);
  }

  private GraphNode deepCopyRecurse(GraphNode node, Map<Integer, GraphNode> created) {
    if (created.containsKey(node.value)) {
      return created.get(node.value);
    }

    GraphNode copy = new GraphNode(node.value);
    created.put(copy.value, copy);
    for (GraphNode neighbor : node.neighbors) {
      GraphNode copyNeighbor = deepCopyRecurse(neighbor, created);
      copy.addNeighbor(copyNeighbor);
    }
    return copy;
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
