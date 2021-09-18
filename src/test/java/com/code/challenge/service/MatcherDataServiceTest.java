package com.code.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.code.challenge.model.Person;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatcherDataServiceTest {

  private List<String> lines;

  @BeforeEach
  void setUp() {
    lines = getContentFile.get();
  }

  private static final Supplier<List<String>> getContentFile = () ->
      List.of(
          "Smith, Joan -- corporis",
          "    Totam eos ut omnis et nemo dolore.",
          "Smith, Sam -- ut",
          "    Ut dolorem est voluptate fugit qui vitae.",
          "Thomas, Joan -- modi",
          "    Nesciunt magni suscipit maxime quaerat sint hic voluptate.",
          "Upton, Joan -- veritatis",
          "    Sed ut impedit harum.",
          "Cartman, Eric -- tenetur",
          "    Quaerat sint hic voluptate."
      );

  @Test
  void getPeopleSuccess() {
    // Arrange
    // Act
    List<Person> people = MatcherDataService.getPeople(lines.stream());

    // Assert
    assertThat(people)
        .isNotNull()
        .isNotEmpty()
        .hasSize(5);
  }

  @Test
  void getPeopleAndValidateContentSuccess() {
    // Arrange
    // Act
    List<Person> people = MatcherDataService.getPeople(lines.stream());

    // Assert
    assertThat(people)
        .containsExactly(
            Person.builder().lastName("Smith").firstName("Joan").build(),
            Person.builder().lastName("Smith").firstName("Sam").build(),
            Person.builder().lastName("Thomas").firstName("Joan").build(),
            Person.builder().lastName("Upton").firstName("Joan").build(),
            Person.builder().lastName("Cartman").firstName("Eric").build()
        )
        .doesNotContain(
            Person.builder().lastName("Totam").firstName("eos").build()
        );
  }
}