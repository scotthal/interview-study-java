package net.scotthallock.study.graphnode;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class GraphNodeTest {
  public static final int GRAPH_NODE_VALUE_0 = 42;
  public static final int GRAPH_NODE_VALUE_1 = 47;
  public static final int GRAPH_NODE_VALUE_2 = 12;
  public static final int GRAPH_NODE_VALUE_3 = 7;

  @Test
  public void constructionWorks() {
    GraphNode node = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node.value()).isEqualTo(GRAPH_NODE_VALUE_0);
  }

  @Test
  public void reflexiveEquality() {
    GraphNode node = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node).isEqualTo(node);
  }

  @Test
  public void equalValuesAreEqual() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node0).isEqualTo(node1);
  }

  @Test
  public void transitiveEquality() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node0).isEqualTo(node1);
    assertThat(node1).isEqualTo(node0);
  }

  @Test
  public void unequalValuesAreUnequal() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);

    assertThat(node0).isNotEqualTo(node1);
  }

  @Test
  public void equalValuesEqualHashcodes() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node0).isEqualTo(node1);
    assertThat(node0.hashCode()).isEqualTo(node1.hashCode());
  }

  @Test
  public void unequalValuesUnequalHashCodes() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);

    assertThat(node0).isNotEqualTo(node1);
    assertThat(node0.hashCode()).isNotEqualTo(node1.hashCode());
  }

  @Test
  public void initialNodeHasNoNeighbors() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node0.neighbors()).isEmpty();
  }

  @Test
  public void addingANeighborInsertsBothEdges() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);

    assertThat(node0.neighbors()).doesNotContain(node1);
    assertThat(node1.neighbors()).doesNotContain(node0);

    node0.addNeighbor(node1);

    assertThat(node0.neighbors()).contains(node1);
    assertThat(node1.neighbors()).contains(node0);
  }

  @Test
  public void addingMultipleNeighborsToASingleNodeWorks() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);
    GraphNode node2 = new GraphNode(GRAPH_NODE_VALUE_2);

    node0.addNeighbor(node1);
    node0.addNeighbor(node2);

    ImmutableSet<GraphNode> neighbors = node0.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);
    assertThat(neighbors).contains(node1);
    assertThat(neighbors).contains(node2);

    neighbors = node1.neighbors();
    assertThat(neighbors.size()).isEqualTo(1);
    assertThat(neighbors).contains(node0);

    neighbors = node2.neighbors();
    assertThat(neighbors.size()).isEqualTo(1);
    assertThat(neighbors).contains(node0);
  }

  @Test
  public void letsBuildADiamond() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);
    GraphNode node2 = new GraphNode(GRAPH_NODE_VALUE_2);
    GraphNode node3 = new GraphNode(GRAPH_NODE_VALUE_3);

    node0.addNeighbor(node1);
    node0.addNeighbor(node2);

    node3.addNeighbor(node1);
    node3.addNeighbor(node2);

    ImmutableSet<GraphNode> neighbors = node0.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);
    assertThat(neighbors).contains(node1);
    assertThat(neighbors).contains(node2);

    neighbors = node1.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);
    assertThat(neighbors).contains(node0);
    assertThat(neighbors).contains(node3);

    neighbors = node2.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);
    assertThat(neighbors).contains(node0);
    assertThat(neighbors).contains(node3);

    neighbors = node3.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);
    assertThat(neighbors).contains(node1);
    assertThat(neighbors).contains(node2);
  }

  @Test
  public void letsCopyADiamond() {
    GraphNode node0 = new GraphNode(GRAPH_NODE_VALUE_0);
    GraphNode node1 = new GraphNode(GRAPH_NODE_VALUE_1);
    GraphNode node2 = new GraphNode(GRAPH_NODE_VALUE_2);
    GraphNode node3 = new GraphNode(GRAPH_NODE_VALUE_3);

    node0.addNeighbor(node1);
    node0.addNeighbor(node2);

    node3.addNeighbor(node1);
    node3.addNeighbor(node2);

    GraphNode node0Copy = node0.deepCopy();
    assertThat(node0Copy == node0).isFalse();
    assertThat(node0Copy).isNotEqualTo(node0);
    assertThat(node0Copy.value()).isEqualTo(node0.value());

    ImmutableSet<GraphNode> neighbors = node0Copy.neighbors();
    assertThat(neighbors.size()).isEqualTo(2);

    Set<Integer> neighborValues = new HashSet<Integer>();
    GraphNode node1Copy = null;
    for (GraphNode neighbor : neighbors) {
      if (neighbor.value() == GRAPH_NODE_VALUE_1) {
        node1Copy = neighbor;
      }
      neighborValues.add(neighbor.value());
    }
    assertThat(neighborValues.size()).isEqualTo(2);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_1);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_2);
    assertThat(node1Copy).isNotNull();
    assertThat(node1Copy).isNotEqualTo(node1);

    neighborValues.clear();
    neighbors = node1Copy.neighbors();
    GraphNode node3Copy = null;
    for (GraphNode neighbor : neighbors) {
      if (neighbor.value() == GRAPH_NODE_VALUE_3) {
        node3Copy = neighbor;
      }
      neighborValues.add(neighbor.value());
    }
    assertThat(neighborValues.size()).isEqualTo(2);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_0);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_3);
    assertThat(node3Copy).isNotNull();
    assertThat(node3Copy).isNotEqualTo(node3);

    neighborValues.clear();
    neighbors = node3Copy.neighbors();
    GraphNode node2Copy = null;
    for (GraphNode neighbor : neighbors) {
      if (neighbor.value() == GRAPH_NODE_VALUE_2) {
        node2Copy = neighbor;
      }
      neighborValues.add(neighbor.value());
    }
    assertThat(neighborValues.size()).isEqualTo(2);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_1);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_2);
    assertThat(node2Copy).isNotNull();
    assertThat(node2Copy).isNotEqualTo(node2);

    neighborValues.clear();
    neighbors = node2Copy.neighbors();
    for (GraphNode neighbor : neighbors) {
      neighborValues.add(neighbor.value());
    }
    assertThat(neighborValues.size()).isEqualTo(2);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_0);
    assertThat(neighborValues).contains(GRAPH_NODE_VALUE_3);
  }
}
