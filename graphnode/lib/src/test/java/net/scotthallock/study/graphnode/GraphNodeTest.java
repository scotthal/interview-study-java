package net.scotthallock.study.graphnode;

import static com.google.common.truth.Truth.assertThat;

import java.beans.Transient;

import org.junit.Test;

public class GraphNodeTest {
  public static final int GRAPH_NODE_VALUE_0 = 42;
  public static final int GRAPH_NODE_VALUE_1 = 47;

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

}
