package com.code.challenge.service;

import com.code.challenge.model.Person;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatcherDataService {

  /**
   * 1st Capturing Group for the Last Name                               ([A-Za-z]+)
   * Matches the character ',' and matches any whitespace character      ,\s
   * 2nd Capturing Group for the First Name                              ([A-Za-z]+)
   * Matches any whitespace character                                    \s
   */
  private static final String REGEX_EXPRESSION = "^([A-Za-z]+),\\s+([A-Za-z]+)\\s";

  /**
   * Matching the lines with the regex expression and get a list of people.
   * @param line Every line from the file
   * @return List<Person>
   */
  public static List<Person> getPeople(Stream<String> line) {

    Pattern pattern = Pattern.compile(REGEX_EXPRESSION);

    return line
        .map(pattern::matcher)
        .filter(Matcher::find)
        .map(matcher -> Person.builder()
            .lastName(matcher.group(1))
            .firstName(matcher.group(2))
            .build())
        .collect(Collectors.toList());
  }

}
