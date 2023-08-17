package net.scotthallock.study.autovalue;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class GraphNode {
  static GraphNode create(int value) {
    return new AutoValue_GraphNode(value);
  }

  abstract int value();
}
