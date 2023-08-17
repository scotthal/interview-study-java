package net.scotthallock.study.graphnode;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class GraphNodeTest {
  public static final int GRAPH_NODE_VALUE_0 = 42;

  @Test
  public void constructionWorks() {
    GraphNode node = new GraphNode(GRAPH_NODE_VALUE_0);

    assertThat(node.value()).isEqualTo(GRAPH_NODE_VALUE_0);
  }

}
