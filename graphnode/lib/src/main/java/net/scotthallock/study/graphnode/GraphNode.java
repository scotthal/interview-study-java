package net.scotthallock.study.graphnode;

public class GraphNode {
  private int value;

  public GraphNode(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GraphNode)) {
      return false;
    }

    GraphNode other = (GraphNode) o;

    if (this.value == other.value) {
      return true;
    }

    return false;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(value);
    return result;
  }
}
