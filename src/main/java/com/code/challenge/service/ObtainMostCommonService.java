package com.code.challenge.service;

import com.code.challenge.model.Person;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObtainMostCommonService {

  /**
   * Function for to get a stream with the most common names from a list of people Map the person to
   * a string Collect the strings grouping by the name (last name or firstname) and counting the
   * repeated attributes Sort the Map Entry values in descending order.
   *
   * @param people List<Person>
   * @param personAttribute Function for map the person to string: person -> person.getAttribute()
   * @return A Stream of map entry (key-value pair), with key (last name or first name) and value (a
   * long with the names' quantity repetitions)
   */
  public static final BiFunction<List<Person>, Function<Person, String>, Stream<Entry<String, Long>>> getMostCommon = (people, personAttributeFunction) ->
      people.stream()
          .map(personAttributeFunction)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet().stream()
          .sorted(Entry.comparingByValue(Comparator.reverseOrder()));


  /**
   * Function for to get the most common first names or last names limited by a number.
   *
   * @param mostCommonStream A Stream of map entry (key-value pair)
   * @param limit Function for map the person to string: person -> person.getAttribute()
   * @return A LinkedHashMap limited with the most common first names or last names
   */
  public static final BiFunction<Stream<Entry<String, Long>>, Integer, LinkedHashMap<String, Long>> getMostCommonLimited = (mostCommonStream, limit) ->
      mostCommonStream
          .limit(limit)
          .collect(
              Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

}
