package com.code.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.code.challenge.model.Person;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObtainMostCommonServiceTest {

  private List<Person> people;

  @BeforeEach
  void setUp() {
    people = getPeople.get();
  }

  private static final Supplier<List<Person>> getPeople = () ->
      List.of(
          Person.builder().lastName("Smith").firstName("Joan").build(),
          Person.builder().lastName("Smith").firstName("Sam").build(),
          Person.builder().lastName("Thomas").firstName("Joan").build(),
          Person.builder().lastName("Upton").firstName("Joan").build(),
          Person.builder().lastName("Cartman").firstName("Eric").build()
      );

  @Test
  void getMostCommonLastNamesSuccess() {
    // Arrange
    // Act
    Stream<Entry<String, Long>> result = ObtainMostCommonService.getMostCommon.apply
        (people, Person::getLastName);

    // Assert
    assertThat(result)
        .isNotNull()
        .isNotEmpty()
        .hasSize(4)
        .contains(new AbstractMap.SimpleEntry<>("Smith", 2L))
        .doesNotContain(new AbstractMap.SimpleEntry<>("Joan", 3L));
  }

  @Test
  void getMostCommonFirstNamesSuccess() {
    // Arrange
    // Act
    Stream<Entry<String, Long>> result = ObtainMostCommonService.getMostCommon.apply
        (people, Person::getFirstName);

    // Assert
    assertThat(result)
        .isNotNull()
        .isNotEmpty()
        .hasSize(3)
        .contains(new AbstractMap.SimpleEntry<>("Joan", 3L))
        .doesNotContain(new AbstractMap.SimpleEntry<>("Smith", 2L));
  }

  @Test
  void getMostCommonLastNamesLimitedSuccess() {
    // Arrange
    int limit = 2;
    Stream<Entry<String, Long>> mostCommonStream = ObtainMostCommonService.getMostCommon.apply
        (people, Person::getLastName);

    // Act
    LinkedHashMap<String, Long> result = ObtainMostCommonService.getMostCommonLimited.apply(
        mostCommonStream, limit);

    // Assert
    assertThat(result)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .contains(new AbstractMap.SimpleEntry<>("Smith", 2L))
        .contains(new AbstractMap.SimpleEntry<>("Thomas", 1L))
        .doesNotContain(new AbstractMap.SimpleEntry<>("Cartman", 1L));
  }

  @Test
  void getMostCommonFirstNamesLimitedSuccess() {
    // Arrange
    int limit = 2;
    Stream<Entry<String, Long>> mostCommonStream = ObtainMostCommonService.getMostCommon.apply
        (people, Person::getFirstName);

    // Act
    LinkedHashMap<String, Long> result = ObtainMostCommonService.getMostCommonLimited.apply(
        mostCommonStream, limit);

    // Assert
    assertThat(result)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2)
        .contains(new AbstractMap.SimpleEntry<>("Joan", 3L))
        .doesNotContain(new AbstractMap.SimpleEntry<>("Cartman", 1L));
  }
}