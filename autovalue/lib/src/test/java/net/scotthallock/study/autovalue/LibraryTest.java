package net.scotthallock.study.autovalue;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class LibraryTest {
  @Test
  public void someLibraryMethodReturnsTrue() {
    Library classUnderTest = new Library();
    assertThat(classUnderTest.someLibraryMethod()).isTrue();
  }
}
