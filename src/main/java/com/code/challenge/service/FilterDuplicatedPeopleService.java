package com.code.challenge.service;

import com.code.challenge.model.Person;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterDuplicatedPeopleService {

  /**
   * Filter the list of Person by distinct First Name and Last Name
   *
   * @param people List<Person> for to be filtered
   * @param limit Integer for to limit the List<Person>
   * @return List<Person>
   */
  public static final BiFunction<List<Person>, Integer, List<Person>> filterByFullName = (people, limit) ->
      people.stream()
          .filter(distinctByKey(Person::getFirstName))
          .filter(distinctByKey(Person::getLastName))
          .limit(limit)
          .collect(Collectors.toList());

  /**
   * Stateful filter that maintains state about what it's seen previously.
   *
   * @param keyExtractor Function<Person, String>  person -> person.getAttributeName()
   * @param <T>          String
   * @return Predicate<String>, returns whether the given element was seen for the first time.
   */
  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    final Set<Object> seen = new HashSet<>();
    return t -> seen.add(keyExtractor.apply(t));
  }

}
