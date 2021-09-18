package com.code.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.code.challenge.model.Person;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterDuplicatedPeopleServiceTest {

  private List<Person> people;

  @BeforeEach
  void setUp() {
    people = getPeople.get();
  }

  private static final Supplier<List<Person>> getPeople = () ->
      List.of(
          Person.builder().lastName("Smith").firstName("Joan").build(),
          Person.builder().lastName("Smith").firstName("John").build(),
          Person.builder().lastName("Smith").firstName("Sam").build(),
          Person.builder().lastName("Thomas").firstName("Joan").build(),
          Person.builder().lastName("Upton").firstName("Joan").build(),
          Person.builder().lastName("Upton").firstName("Tom").build(),
          Person.builder().lastName("Vasquez").firstName("Cesar").build()
      );

  @Test
  void filterByFullNameSuccess() {
    // Arrange
    int limit = 25;
    // Act
    List<Person> result = FilterDuplicatedPeopleService.filterByFullName.apply(people, limit);

    // Assert
    assertThat(result)
        .isNotNull()
        .isNotEmpty()
        .hasSize(3)
        .containsExactly(
            Person.builder().lastName("Smith").firstName("Joan").build(),
            Person.builder().lastName("Upton").firstName("Tom").build(),
            Person.builder().lastName("Vasquez").firstName("Cesar").build()
            )
        .doesNotContain(
            Person.builder().lastName("Smith").firstName("John").build(),
            Person.builder().lastName("Smith").firstName("Sam").build(),
            Person.builder().lastName("Thomas").firstName("Joan").build(),
            Person.builder().lastName("Upton").firstName("Joan").build()
        );
  }
}