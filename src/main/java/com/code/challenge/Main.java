package com.code.challenge;

import static com.code.challenge.model.Constant.*;
import static java.lang.System.*;

import com.code.challenge.model.Person;
import com.code.challenge.service.FilterDuplicatedPeopleService;
import com.code.challenge.service.MatcherDataService;
import com.code.challenge.service.ModifyPeopleNameService;
import com.code.challenge.service.ObtainMostCommonService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

  private static PrintStream fileOut;

  public static void main(String[] args) {

    ClassLoader classLoader = Main.class.getClassLoader();

    try (Stream<String> lines = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
        classLoader.getResourceAsStream(FILE_NAME_INPUT)), StandardCharsets.UTF_8)).lines()) {

      /*
       * Get the people's names in a List<Person>
       */
      List<Person> people = MatcherDataService.getPeople(lines);

      Supplier<Stream<Entry<String, Long>>> mostCommonLastNameStream = () -> ObtainMostCommonService.getMostCommon.apply(people, Person::getLastName);
      Supplier<Stream<Entry<String, Long>>> mostCommonFirstNameStream = () -> ObtainMostCommonService.getMostCommon.apply(people, Person::getFirstName);

      /*
       * 1. The names' cardinality for full, last, and first names
       */
      print(FORMAT_CARDINALITY,
          people.stream().distinct().count(),
          mostCommonLastNameStream.get().count(),
          mostCommonFirstNameStream.get().count()
      );

      /*
       * 2. The 10 most common last names
       */
      print(FORMAT_TOP_TEN_LAST_NAMES,
          ObtainMostCommonService.getMostCommonLimited.apply(mostCommonLastNameStream.get(),
              LIMIT_TOP)
      );

      /*
       * 3. The 10 most common first names
       */
      print(FORMAT_TOP_TEN_FIRST_NAMES,
          ObtainMostCommonService.getMostCommonLimited.apply(mostCommonFirstNameStream.get(),
              LIMIT_TOP)
      );

      /*
       * 4. List of Modified Names
       */
      List<Person> peopleFilteredByFullName = FilterDuplicatedPeopleService.filterByFullName.apply(
          people, LIMIT_MODIFIED_NAME);

      /*
       * 4.1 Original list names
       */
      print(MESSAGE_MODIFIED_ORIGINAL_LIST);

      peopleFilteredByFullName.forEach(person -> print(FORMAT_NAME, person.toString()));

      /*
       * 4.2 Modified list names
       */
      print(MESSAGE_MODIFIED_LIST_NAMES);

      ModifyPeopleNameService.getModifiedList(peopleFilteredByFullName)
          .forEach(person -> print(FORMAT_NAME, person.toString()));

    }
  }

  static {
    try {
      fileOut = new PrintStream(FILE_NAME_OUTPUT);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void print(String format, Object ...message) {
    out.printf(format, message);
    fileOut.printf(format, message);
  }

}
