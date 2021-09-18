package com.code.challenge.service;

import com.code.challenge.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModifyPeopleNameService {

  /**
   * Compute a new list which does not share any full names with the original list.
   *
   * @param people List<Person>
   * @return List<Person>
   */
  public static List<Person> getModifiedList(List<Person> people) {
    List<String> lastNameList = mapPersonToStringAttribute.apply(people, Person::getLastName);
    List<String> firstNameList = mapPersonToStringAttribute.apply(people, Person::getFirstName);

    Collections.rotate(firstNameList, 1);

    List<Person> peopleNamesModified = new ArrayList<>();

    IntStream.range(0, Math.min(lastNameList.size(), firstNameList.size()))
        .boxed()
        .forEach(index -> {
          Person newModifiedPerson = Person.builder()
              .lastName(lastNameList.get(index))
              .firstName(firstNameList.get(index))
              .build();
          peopleNamesModified.add(newModifiedPerson);
        });

    return peopleNamesModified;
  }

  /**
   * Map Person list and collect a list of string base on a person attribute
   *
   * @param people List<Person>
   * @param personAttributeFunction Function<Person, String>  person -> person.getAttributeName()
   * @return List<String>
   */
  private static final BiFunction<List<Person>, Function<Person, String>, List<String>> mapPersonToStringAttribute = (people, personAttributeFunction) ->
      people.stream()
          .map(personAttributeFunction)
          .collect(Collectors.toList());

}
